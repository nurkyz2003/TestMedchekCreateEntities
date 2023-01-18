package com.peaksoft.testmedchek.security.auth;


import com.peaksoft.testmedchek.security.user.UserRepository;
import lombok.RequiredArgsConstructor;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final UserRepository userRepository;

  private final AuthenticationService service;

  @PostMapping("/register")
  public AuthenticationResponse register(
          @RequestBody @Valid RegisterRequest request
  ) {
    return service.register(request);
  }

  @PostMapping("/login")
  public AuthenticationResponse authenticate(
          @RequestBody AuthenticationRequest request) {


    return service.authenticate(request);
  }

}