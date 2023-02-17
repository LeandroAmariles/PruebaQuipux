package com.quipux.pruebaquipux.infraestructure.repository;


import com.quipux.pruebaquipux.domain.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {

  public Boolean existsByTitulo(String titulo);

}
