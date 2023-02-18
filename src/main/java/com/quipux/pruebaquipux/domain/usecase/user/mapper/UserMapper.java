package com.quipux.pruebaquipux.domain.usecase.user.mapper;

import com.quipux.pruebaquipux.domain.entities.User;
import com.quipux.pruebaquipux.infraestructure.entrypoint.user.in.NewUserRequest;
import com.quipux.pruebaquipux.infraestructure.entrypoint.user.out.RegisterResponse;
import com.quipux.pruebaquipux.infraestructure.entrypoint.user.out.UserResponse;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

  public User requestToEntity(NewUserRequest request);

  public RegisterResponse entityToRegisterResponse(User user);

  public UserResponse entityToResponse(User user);

}
