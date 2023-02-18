package com.quipux.pruebaquipux.domain.usecase.user;


import com.quipux.pruebaquipux.domain.entities.User;
import com.quipux.pruebaquipux.domain.usecase.user.mapper.UserMapper;
import com.quipux.pruebaquipux.infraestructure.entrypoint.user.in.NewUserRequest;
import com.quipux.pruebaquipux.infraestructure.entrypoint.user.out.RegisterResponse;
import com.quipux.pruebaquipux.infraestructure.exception.ConflictException;
import com.quipux.pruebaquipux.infraestructure.exception.NotFoundException;
import com.quipux.pruebaquipux.infraestructure.repository.RoleRepository;
import com.quipux.pruebaquipux.infraestructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  @Value("${app.default.role-id}")
  private Long id_rol;

  private final RoleRepository roleRepository;
  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  private final UserMapper userMapper;

  @Override
  @Transactional
  public RegisterResponse createEntity(NewUserRequest user) {
    if (exist(user.getEmail())) {
      throw new ConflictException("There is already an account with that email address: " + user);
    }
    User user1 = userMapper.requestToEntity(user);
    user1.setPassword(passwordEncoder.encode(user1.getPassword()));
    user1.setRole(roleRepository.findById(id_rol).get());
    return userMapper.entityToRegisterResponse(userRepository.save(user1));
  }

  private Boolean exist(String email) {
    return userRepository.existsByEmail(email);
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    userRepository.findById(id).ifPresent(userRepository::delete);
  }

  @Override
  @Transactional(readOnly = true)
  public List<User> getList() {
    return userRepository.findAll();
  }


  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("username %s not found".formatted(email)));
  }

}
