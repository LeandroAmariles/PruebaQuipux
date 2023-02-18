package com.quipux.pruebaquipux.domain.usecase.user;

import com.quipux.pruebaquipux.domain.entities.User;
import com.quipux.pruebaquipux.infraestructure.entrypoint.user.in.NewUserRequest;
import com.quipux.pruebaquipux.infraestructure.entrypoint.user.out.RegisterResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

  RegisterResponse createEntity(NewUserRequest user);

  void deleteById(Long id);

  List<User> getList();

}
