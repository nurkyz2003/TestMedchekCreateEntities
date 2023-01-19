package com.peaksoft.testmedchek.dto.request;

import com.peaksoft.testmedchek.validation.password.PasswordValid;
import com.peaksoft.testmedchek.validation.phoneNumber.PhoneValid;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class RegisterRequest {

  @NotNull(message = "First name should be not null")
  @Size(min = 2,max = 25)
  private String firstname;

  @Size(min = 2,max = 25)
  @NotNull(message = "Last name should be not null")
  private String lastname;

  @Email(message = "Email should be valid")
  @NotNull(message = "Email should be not null")
  @NotBlank(message = "Email should not be empty")
  private String email;

  @PasswordValid
  private String password;

  @PhoneValid
  private String phoneNumber;
}
