package com.peaksoft.testmedchek.security.auth;


import com.peaksoft.testmedchek.entities.Role;
import com.peaksoft.testmedchek.entities.User;
import com.peaksoft.testmedchek.security.config.JwtService;
import com.peaksoft.testmedchek.security.user.RoleRepository;
import com.peaksoft.testmedchek.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;

  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    var user = User.builder()
        .userFirstName(request.getFirstname())
        .userLastName(request.getLastname())
        .userEmail(request.getEmail())
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
  @PostConstruct
  public void initMethod() {
    repository.findByEmail(admin().getUserEmail())
            .ifPresent(repository::delete);
    repository.save(admin());

    repository.findByEmail(instructor().getUserEmail())
            .ifPresent(repository::delete);
    repository.save(instructor());
  }

  public User admin() {
    Role role = new Role();
    role.setRoleName("Admin");
    User user = new User();
    user.setUserEmail("esen@gmail.com");
    user.setUserPassword("esen");
    user.setUserPassword(passwordEncoder.encode(user.getPassword()));
    user.setUserFirstName("Esen");
    user.setRole(role);
    return user;
  }

  public User instructor() {
    Role role = new Role();
    role.setRoleName("User");
    User user = new User();
    user.setUserEmail("allanov@gmail.com");
    user.setUserPassword("allanov");
    user.setUserPassword(passwordEncoder.encode(user.getPassword()));
    user.setUserFirstName("Muchammed");
    user.setRole(role);
    return user;
  }

}
