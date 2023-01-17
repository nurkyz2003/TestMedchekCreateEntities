package com.peaksoft.testmedchek.service;


import com.peaksoft.testmedchek.entities.User;
import com.peaksoft.testmedchek.repository.RoleRepository;
import com.peaksoft.testmedchek.repository.UserRepository;
import com.peaksoft.testmedchek.dto.request.AuthenticationRequest;
import com.peaksoft.testmedchek.dto.response.AuthenticationResponse;
import com.peaksoft.testmedchek.dto.request.RegisterRequest;
import com.peaksoft.testmedchek.security.config.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final RoleRepository roleRepository;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    var user = User.builder()
            .userFirstName(request.getFirstname())
            .userLastName(request.getLastname())
            .userEmail(request.getEmail())
            .userPhoneNumber(request.getPhoneNumber())
            .userPassword(passwordEncoder.encode(request.getPassword()))
            .role(roleRepository.getById(2L))
            .build();
    repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
            .token(jwtToken)
            .roleName(user.getRole().getRoleName())
            .email(user.getUserEmail())
            .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            )
    );
    var user = repository.findByEmail(request.getEmail())
            .orElseThrow();
    String jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
            .token(jwtToken)
            .roleName(user.getRole().getRoleName())
            .email(user.getUserEmail())
            .build();
  }
}