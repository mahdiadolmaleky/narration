package com.hit.narration.web.rest;

import com.hit.narration.domain.User;
import com.hit.narration.exception.ResourceNotFoundException;
import com.hit.narration.repository.UserRepository;
import com.hit.narration.service.impl.MailService;
import com.hit.narration.service.UserService;
import com.hit.narration.service.dto.UserDTO;
import com.hit.narration.web.rest.errors.BadRequestAlertException;
import com.hit.narration.web.rest.errors.LoginAlreadyUsedException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
@Api(value = "", tags = {"کاربران"})
public class UserResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);
    private final UserService userService;
    private final UserRepository userRepository;
    private final MailService mailService;

    public UserResource(UserService userService, UserRepository userRepository, MailService mailService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.mailService = mailService;
    }

    @ApiOperation("ایجاد یک کاربر")
    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) throws URISyntaxException, UnsupportedEncodingException, MessagingException {
        log.debug("REST request to save User : {}", userDTO);
        if (userDTO.getId() != null) {
            throw new BadRequestAlertException("A new user cannot already have an ID", "userManagement", "idexists");
            // Lowercase the user login before comparing with database
        } else if (userRepository.findOneByEmail(userDTO.getEmail().toLowerCase()).isPresent()) {
            throw new LoginAlreadyUsedException();
        } else {
            UserDTO newUser = userService.createUser(userDTO);
            mailService.sendCreationAccountEmail(newUser);
            return ResponseEntity.created(new URI("/api/users/" + newUser.getEmail())).body(newUser);
        }
    }

    @ApiOperation("بروزرسانی یک کاربر")
    @PutMapping("/users")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO) {
        log.debug("REST request to update User : {}", userDTO);
        Optional<User> existingUser = userRepository.findOneByEmail(userDTO.getEmail().toLowerCase());
        if (existingUser.isPresent() && (!existingUser.get().getId().equals(userDTO.getId()))) {
            throw new LoginAlreadyUsedException();
        }
        UserDTO updatedUser = userService.updateUser(userDTO);
        return ResponseEntity.ok().body(updatedUser);
    }

    @ApiOperation("دریافت یک کاربر توسط شناسه")
    @PermitAll
    @GetMapping("/users/{id}")
    public Optional<UserDTO> getUserById(@PathVariable Long id) {
        log.debug("REST request to get User : {}", id);
        return userService.findOne(id);
    }

    @ApiOperation("دریافت یک کاربر توسط ایمیل")
    @PermitAll
    @GetMapping("/users/by-email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) throws ResourceNotFoundException {
        log.debug("REST request to get User : {}", email);
        UserDTO user = userService.findUserByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid email"));
        return ResponseEntity.ok().body(user);
    }

//    @ApiOperation("دریافت کاربران مربوط به یک نقش")
//    @GetMapping("/users/roles/{roleId}")
//    public ResponseEntity<List<UserDTO>> findUsersByRole(@PathVariable Long roleId, Pageable pageable) {
//        log.debug("REST request to get User : {}", roleId);
//        Page<UserDTO> page = userService.findUsersByRoleId(roleId, pageable);
//        return new ResponseEntity<>(page.getContent(), HttpStatus.OK);
//    }

    @ApiOperation("دریافت همه ی کاربران")
    @GetMapping("/users")
    public ResponseEntity<Page<UserDTO>> getAllUsers(Pageable pageable) {
        Page<UserDTO> page = userService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @ApiOperation("حذف یک کاربر")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.debug("REST request to delete User: {}", id);
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
