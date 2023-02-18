package com.quipux.pruebaquipux.infraestructure.repository;

import com.quipux.pruebaquipux.domain.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
