package com.hit.narration.service.impl;

import com.hit.narration.domain.User;
import com.hit.narration.repository.UserRepository;
import com.hit.narration.security.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findOneByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));
    return UserDetailsImpl.build(user);
  }
}
