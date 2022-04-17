package com.hit.narration.repository;

import com.hit.narration.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@SuppressWarnings("unused")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByEmail(String email);

    Optional<User> findOneByEmailIgnoreCase(String email);

    Boolean existsByEmail(String email);

    Optional<User> findOneWithRoleByEmail(String email);

//    Page<User> findByRole(Long roleId,Pageable pageable);

    Optional<User> findOneByPhoneNumber(String phoneNumber);

    List<User> findByIdIn(Set<Long> usersIds);

    Optional<User> findByEmailAndToken(String email,String token);
}
