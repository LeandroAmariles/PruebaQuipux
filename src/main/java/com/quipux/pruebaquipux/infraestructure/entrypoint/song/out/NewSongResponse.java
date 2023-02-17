package com.quipux.pruebaquipux.infraestructure.entrypoint.song.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.net.URI;

@Builder
@Getter
@Setter
public class NewSongResponse {


  private String titulo;

  private String artista;

  private String anno;

  private Long id;

  private URI location;


}
