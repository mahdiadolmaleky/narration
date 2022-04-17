package com.hit.narration.web.rest;

import com.hit.narration.security.JwtUtils;
import com.hit.narration.security.UserDetailsImpl;
import com.hit.narration.service.UserService;
import com.hit.narration.service.dto.LoginRequestDTO;
import com.hit.narration.service.dto.UserDTO;
import com.hit.narration.service.impl.MailService;
import com.hit.narration.web.rest.errors.BadRequestAlertException;
import com.hit.narration.web.rest.errors.LoginAlreadyUsedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JwtUtils jwtUtils;

    private final MailService mailService;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtUtils jwtUtils, MailService mailService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.mailService = mailService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        userService.updateLastLogin(loginRequest.getEmail());
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(userDetails);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userDTO) throws UnsupportedEncodingException, MessagingException, URISyntaxException {
        log.debug("REST request to save User : {}", userDTO);
        if (userDTO.getId() != null)
            throw new BadRequestAlertException("A new user cannot already have an ID", "userManagement", "idexists");
        else if (userService.existsByEmail(userDTO.getEmail()))
            throw new LoginAlreadyUsedException();
        else {
            UserDTO user = userService.createUser(userDTO);
            mailService.sendCreationAccountEmail(user);
            return ResponseEntity.created(new URI("/api/auth/signup/" + user.getEmail())).body(user);
        }
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("You've been signed out!");
    }
}
