package com.quipux.pruebaquipux.infraestructure.entrypoint.user.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewUserRequest {

  @NotEmpty
  @JsonProperty("first_name")
  private String firstName;

  @NotEmpty
  @JsonProperty("last_name")
  private String lastName;

  @Email
  private String email;

  @NotEmpty
  private String password;

  @NotEmpty
  private String photo;

}
