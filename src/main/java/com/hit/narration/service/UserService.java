package com.hit.narration.service;

import com.hit.narration.service.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO);

//    void updateUser(String firstName, String lastName, String email);
//
    void changePassword(String currentClearTextPassword, String newPassword);

    void activationAccount(String email, String token);

    boolean checkResetPasswordToken(String email, String token);

    Page<UserDTO> findAll(Pageable pageable);

//    Page<UserDTO> findUsersByRoleId(Long roleId, Pageable pageable);

    Optional<UserDTO> findUserByEmail(String email);

    Boolean existsByEmail(String email);

    Optional<UserDTO> findOne(Long id);

    void delete(Long id);

    void setToken(String token, String email);

    void updateLastLogin(String email);
}
