package com.quipux.pruebaquipux.infraestructure.entrypoint.user;

import com.quipux.pruebaquipux.application.config.security.JwtUtils;
import com.quipux.pruebaquipux.domain.entities.User;
import com.quipux.pruebaquipux.domain.usecase.user.UserService;
import com.quipux.pruebaquipux.domain.usecase.user.mapper.UserMapper;
import com.quipux.pruebaquipux.infraestructure.entrypoint.user.in.LoginRequest;
import com.quipux.pruebaquipux.infraestructure.entrypoint.user.in.NewUserRequest;
import com.quipux.pruebaquipux.infraestructure.entrypoint.user.out.AuthenticationResponse;
import com.quipux.pruebaquipux.infraestructure.entrypoint.user.out.RegisterResponse;
import com.quipux.pruebaquipux.infraestructure.entrypoint.user.out.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthenticationManager authenticationManager;

  private final JwtUtils jwtUtils;

  private final UserService userService;

  private final UserMapper mapper;

  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  public AuthenticationResponse login(@Valid @RequestBody LoginRequest request) {
    return createJwtToken(request.getUserName(), request.getPassword());
  }

  @PostMapping("/register")
  public ResponseEntity<RegisterResponse> register(@Valid @RequestBody NewUserRequest userRequest) {
    RegisterResponse response = userService.createEntity(userRequest);
    AuthenticationResponse auth = createJwtToken(userRequest.getEmail(), userRequest.getPassword());
    response.setAuth(auth);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  private AuthenticationResponse createJwtToken(String username, String password) {
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        username, password));
    if (authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails user1) {
      String jwt = jwtUtils.generateToken(user1);
      Date expiration = jwtUtils.extractExpiration(jwt);
      return AuthenticationResponse.builder().token(jwt).expirationDate(expiration).build();
    }
    throw new AccessDeniedException("error in the authentication process");
  }

  @GetMapping("/me")
  public ResponseEntity<UserResponse> getUserInformation(@AuthenticationPrincipal User user) {
    UserResponse userResponse = mapper.entityToResponse(user);
    return new ResponseEntity<>(userResponse, HttpStatus.OK);
  }

}
