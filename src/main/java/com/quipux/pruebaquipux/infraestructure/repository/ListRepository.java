package com.quipux.pruebaquipux.infraestructure.repository;

import com.quipux.pruebaquipux.domain.entities.Lista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepository extends JpaRepository<Lista, Long> {

  public Boolean existsByNombre(String nombre);

  public Lista findByNombre(String nombre);

  public Lista deleteByNombre(String nombre);
}

