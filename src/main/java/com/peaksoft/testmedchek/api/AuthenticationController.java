package com.peaksoft.testmedchek.api;


import com.peaksoft.testmedchek.dto.request.AuthenticationRequest;
import com.peaksoft.testmedchek.dto.request.RegisterRequest;
import com.peaksoft.testmedchek.dto.response.AuthenticationResponse;
import com.peaksoft.testmedchek.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")
  public AuthenticationResponse register(
          @RequestBody @Valid RegisterRequest request
  ) {
    return service.register(request);
  }

  @PostMapping("/login")
  public AuthenticationResponse authenticate(
          @RequestBody AuthenticationRequest request
  ) {
    return service.authenticate(request);
  }

}