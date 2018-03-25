package com.training.service;

import com.training.model.dao.UserRepository;
import com.training.model.entity.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = repository.findByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException("User not found.");
    }

    if (user.getRoles() == null || user.getRoles().isEmpty()) {
      throw new UsernameNotFoundException("User not authorized.");
    }

    Collection<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getCode()))
        .collect(Collectors.toCollection(ArrayList::new));

    return new org.springframework.security.core.userdetails.User(user.getUsername(),
        user.getPassword(), grantedAuthorities);
  }
}
