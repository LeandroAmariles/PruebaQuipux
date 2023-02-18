package com.quipux.pruebaquipux.infraestructure.entrypoint.user.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

  @NotEmpty
  @JsonProperty("username")
  private String userName;

  @NotEmpty
  @JsonProperty("password")
  private String password;
}
