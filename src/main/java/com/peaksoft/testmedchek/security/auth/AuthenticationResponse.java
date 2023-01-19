package com.peaksoft.testmedchek.security.auth;

import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

  private String token;

  private String roleName;

  private String email;

}

