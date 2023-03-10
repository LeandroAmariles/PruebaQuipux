package com.quipux.pruebaquipux.infraestructure.entrypoint.user.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse {

  private Long id;

  @JsonProperty("first_Name")
  private String firstName;

  @JsonProperty("last_Name")
  private String lastName;

  private String email;

  private AuthenticationResponse auth;
}
