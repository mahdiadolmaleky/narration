package com.hit.narration.service.impl;

import com.hit.narration.domain.User;
import com.hit.narration.repository.RoleRepository;
import com.hit.narration.repository.UserRepository;
import com.hit.narration.security.SecurityUtils;
import com.hit.narration.service.UserService;
import com.hit.narration.service.dto.UserDTO;
import com.hit.narration.service.mapper.UserMapper;
import com.hit.narration.web.rest.errors.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private static final String ENTITY_NAME = "User";

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public UserDTO registerUser(UserDTO userDTO, String password) {
        userDTO.getEmail().toLowerCase();
        userRepository.findOneByEmail(userDTO.getEmail().toLowerCase()).ifPresent(existingUser -> {
            boolean removed = removeNonActivatedUser(existingUser);
            if (!removed) {
                throw new UsernameAlreadyUsedException();
            }
        });
        User newUser = userMapper.toEntityForRegister(userDTO);
        String encryptedPassword = passwordEncoder.encode(password);
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        // new user is not active
        newUser.setEnabled(false);
        // new user gets registration key
        newUser = userRepository.save(newUser);
        log.debug("Created Information for User: {}", newUser);
        return userMapper.toDto(newUser);
    }

    private boolean removeNonActivatedUser(User existingUser) {
        if (existingUser.getEnabled()) {
            return false;
        }
        userRepository.delete(existingUser);
        userRepository.flush();
        return true;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if (userDTO.getEmail() != null) {
            userDTO.getEmail().toLowerCase();
        }
        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        log.debug("Created Information for User: {}", user);
        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public UserDTO updateUser(UserDTO userDTO) {

        User user = userRepository.getById(userDTO.getId());
        if (userDTO.getEmail() != null) {
            user.setEmail(userDTO.getEmail().toLowerCase());
        }
        if (userDTO.getPhoneNumber() != null) {
            user.setPhoneNumber(userDTO.getPhoneNumber());
        }
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        if (!StringUtils.isBlank(userDTO.getPassword())) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        log.debug("Changed Information for User: {}", user);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

//    @Override
//    @Transactional
//    public void updateUser(String firstName, String lastName, String email) {
//        SecurityUtils.getCurrentUserLogin()
//                .flatMap(userRepository::findOneByEmail)
//                .ifPresent(user -> {
//                    user.setFirstName(firstName);
//                    user.setLastName(lastName);
//                    if (email != null) {
//                        user.setEmail(email.toLowerCase());
//                    }
//                    //this.clearUserCaches(user);
//                    log.debug("Changed Information for User: {}", user);
//                });
//    }

    @Override
    @Transactional
    public void activationAccount(String email, String token) {
        userRepository.findByEmailAndToken(email, token).ifPresentOrElse(
                user -> {
                    Duration duration = Duration.between(Instant.now(), user.getTokenCreatedDate());
                    if (duration.toMinutes() < 30 && user.getTokenExpired().equals(false)) {
                        user.setEmailConfirmed(true);
                        user.setTokenExpired(true);
                        userRepository.save(user);
                        log.debug("user account activated", user);
                    } else {
                        user.setTokenExpired(true);
                        throw new BadRequestAlertException("Activation code has expired", ENTITY_NAME, ErrorConstants.ActivationCodeHasExpired);
                    }
                }, () ->
                {
                    throw new UserNotFoundException();
                }
        );
    }

    @Override
    @Transactional
    public boolean checkResetPasswordToken(String email, String token) {
        Optional<User> user = userRepository.findByEmailAndToken(email, token);
        if (user.isPresent()) {
            Duration duration = Duration.between(Instant.now(), user.get().getTokenCreatedDate());
            if (duration.toMinutes() < 10 && user.get().equals(false)) {
                user.get().setTokenExpired(true);
                userRepository.save(user.get());
                return true;
            } else {
                user.get().setTokenExpired(true);
                userRepository.save(user.get());
                throw new BadRequestAlertException("Activation code has expired", ENTITY_NAME, ErrorConstants.ActivationCodeHasExpired);
            }
        } else throw new UserNotFoundException();
    }

    @Override
    @Transactional
    public void changePassword(String currentClearTextPassword, String newPassword) {
        SecurityUtils.getCurrentUserLogin()
                .flatMap(userRepository::findOneByEmailIgnoreCase)
                .ifPresent(user -> {
                    String currentEncryptedPassword = user.getPassword();
                    if (!passwordEncoder.matches(currentClearTextPassword, currentEncryptedPassword)) {
                        throw new InvalidPasswordException();
                    }
                    String encryptedPassword = passwordEncoder.encode(newPassword);
                    user.setPassword(encryptedPassword);
                    log.debug("Changed password for User: {}", user);
                });
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDTO> findOne(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDTO> findUserByEmail(String email) {
        return userRepository.findOneByEmailIgnoreCase(email).map(userMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserDTO> findAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::toDto);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

//    @Override
//    @Transactional(readOnly = true)
//    public Page<UserDTO> findUsersByRoleId(Long roleId, Pageable pageable) {
//        return userRepository.findByRoleId(roleId, pageable).map(userMapper::toDto);
//    }

    @Override
    public void delete(Long id) {
        userRepository.findById(id).ifPresent(user -> {
            userRepository.delete(user);
            log.debug("Deleted User: {}", user);
        });
    }


    public void updateLastLogin(String email) {
        userRepository.findOneByEmailIgnoreCase(email).ifPresentOrElse(e -> {
            e.setLastLogin(Instant.now());
            userRepository.save(e);
        }, () -> {
            throw new UserNotFoundException();
        });
    }

    @Override
    public void setToken(String token, String email) {
        userRepository.findOneByEmailIgnoreCase(email).ifPresentOrElse(e -> {
            e.setToken(token);
            e.setTokenCreatedDate(Instant.now());
            e.setTokenExpired(false);
            userRepository.save(e);
        }, () -> {
            throw new UserNotFoundException();
        });
    }


//    @Transactional(readOnly = true)
//    public Long getCurrentUserId() {
//        HitEnhancedUser principal = (HitEnhancedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return principal.getUserId();
//    }
//
//    @Transactional(readOnly = true)
//    public Optional<UserDTO> getUserWithRoleByEmail(String email) {
//        return userRepository.findOneWithRoleByEmail(email).map(userMapper::toDto);
//    }
//
//    @Transactional(readOnly = true)
//    public Optional<UserDTO> getCurrentUserWithRole() {
//        return SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneWithRoleByEmail).map(userMapper::toDto);
//    }
//
//    private void clearUserCaches(User user) {
//        Objects.requireNonNull(cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE)).evict(user.getEmail());
//        if (user.getEmail() != null) {
//            Objects.requireNonNull(cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE)).evict(user.getEmail());
//        }
//    }


}
