package com.quipux.pruebaquipux.infraestructure.repository;

import com.quipux.pruebaquipux.domain.entities.Lista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepository extends JpaRepository<Lista, Long> {
}

