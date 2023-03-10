package com.quipux.pruebaquipux.infraestructure.entrypoint.list.out;

import com.quipux.pruebaquipux.domain.entities.Song;
import lombok.Getter;
import lombok.Setter;

import java.net.URI;
import java.util.List;

@Getter
@Setter
public class NewListResponse {


  private Long id;

  private String nombreLista;

  private String descripcion;

  private List<Song> songs;

  private URI location;
}
