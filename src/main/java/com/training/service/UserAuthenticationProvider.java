package com.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationProvider
    extends AbstractUserDetailsAuthenticationProvider {

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  @Autowired
  private PasswordEncoder passwordEncoder;


  @Override
  protected void additionalAuthenticationChecks(UserDetails userDetails,
      UsernamePasswordAuthenticationToken token)
      throws AuthenticationException {

    if (token.getCredentials() == null || userDetails.getPassword() == null) {
      throw new BadCredentialsException("Credentials may not be null.");
    }

    if (!passwordEncoder.matches((String) token.getCredentials(),
        passwordEncoder.encode(userDetails.getPassword()))) {
      throw new BadCredentialsException("Invalid credentials.");
    }
  }

  @Override
  protected UserDetails retrieveUser(String username,
      UsernamePasswordAuthenticationToken token)
      throws AuthenticationException {

    return userDetailsService.loadUserByUsername(username);
  }
}