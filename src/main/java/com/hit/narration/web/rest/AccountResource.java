package com.hit.narration.web.rest;

import com.hit.narration.config.Utility;
import com.hit.narration.service.UserService;
import com.hit.narration.service.dto.PasswordChangeDTO;
import com.hit.narration.service.dto.UserDTO;
import com.hit.narration.service.impl.MailService;
import com.hit.narration.web.rest.errors.InvalidPasswordException;
import com.hit.narration.web.rest.errors.UserNotFoundException;
import com.hit.narration.web.rest.vm.ManagedUserVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@Api(value = "", tags = {"مدیریت حساب کاربری"})
public class AccountResource {

    private static class AccountResourceException extends RuntimeException {
        private AccountResourceException(String message) {
            super(message);
        }
    }

    private final Logger log = LoggerFactory.getLogger(AccountResource.class);

    private final UserService userService;

    private final MailService mailService;

    public AccountResource(UserService userService, MailService mailService) {
        this.userService = userService;
        this.mailService = mailService;
    }

    @ApiOperation("بررسی لاگین بودن کاربر فعلی")
    @GetMapping("/authenticate")
    public String isAuthenticated(HttpServletRequest request) {
        log.debug("REST request to check if the current user is authenticated");
        return request.getRemoteUser();
    }


//    @ApiOperation("ذخیره حساب کاربری")
//    @PostMapping("/account")
//    public void saveAccount(@Valid @RequestBody UserDTO userDTO) {
//        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
//        Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(userDTO.getEmail());
//        if (existingUser.isPresent() && (!existingUser.get().getEmail().equalsIgnoreCase(userLogin))) {
//            throw new EmailAlreadyUsedException();
//        }
//        Optional<User> user = userRepository.findOneByEmail(userLogin);
//        if (!user.isPresent()) {
//            throw new AccountResourceException("User could not be found");
//        }
//        userService.updateUser(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail());
//    }

    @ApiOperation("درخواست لینک فعال سازی حساب کاربری")
    @PostMapping(path = "/account/request-activation-link")
    public ResponseEntity<?> requestPasswordReset(@RequestBody String mail, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
        Optional<UserDTO> user = userService.findUserByEmail(mail);
        if (user.isPresent()) {
            String token = UUID.randomUUID().toString();
            userService.setToken(token, mail);
            String resetPasswordLink = Utility.getSiteURL(request) + "/api/activation-account?email=" + mail + "&token=" + token;
            mailService.sendActivationEmail(user.get(), resetPasswordLink);
        } else {
            throw new UserNotFoundException();
        }
        return ResponseEntity.badRequest().body("Account activation link sent successfully!");
    }

    @ApiOperation("فعال سازی حسای کاربری")
    @GetMapping("/activation-account")
    public ResponseEntity<?> activationAccount(@Param(value = "email") String email, @Param(value = "token") String token) {
        log.debug("REST request to activation account");
        userService.activationAccount(email, token);
        return ResponseEntity.badRequest().body("Account activated successfully!");
    }

    @ApiOperation("درخواست بازیابی رمز ورود")
    @PostMapping("/account/forgot_password")
    public ResponseEntity<?> processForgotPassword(HttpServletRequest request, @RequestParam("email") String userEmail) {

        userService.findUserByEmail(userEmail).ifPresentOrElse(user -> {
            String token = UUID.randomUUID().toString();
            userService.setToken(token, userEmail);
            String resetPasswordLink = Utility.getSiteURL(request) + "/api/reset_password?email=" + userEmail + "&token=" + token;
            try {
                mailService.sendResetPasswordMail(user, resetPasswordLink);
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }, () -> {
            throw new UserNotFoundException();
        });
        return ResponseEntity.badRequest().body("Change password link sent successfully!");
    }

    @GetMapping("/reset_password")
    public boolean confirmResetPassword(@Param(value = "email") String email, @Param(value = "token") String token) {
        return userService.checkResetPasswordToken(email, token);
    }

    @ApiOperation("تغییر رمز ورود")
    @PostMapping(path = "/account/change-password")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangeDTO passwordChangeDto) {
        if (!checkPasswordLength(passwordChangeDto.getNewPassword())) {
            throw new InvalidPasswordException();
        }
        userService.changePassword(passwordChangeDto.getCurrentPassword(), passwordChangeDto.getNewPassword());
        return ResponseEntity.badRequest().body("password changed successfully!");
    }

    private static boolean checkPasswordLength(String password) {
        return !StringUtils.isEmpty(password) &&
                password.length() >= ManagedUserVM.PASSWORD_MIN_LENGTH &&
                password.length() <= ManagedUserVM.PASSWORD_MAX_LENGTH;
    }

}
