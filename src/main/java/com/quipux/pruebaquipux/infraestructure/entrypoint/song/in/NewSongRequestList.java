package com.quipux.pruebaquipux.infraestructure.entrypoint.song.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Builder
@Getter
@Setter
public class NewSongRequestList {

  @NotBlank
  private String titulo;

  @NotBlank
  private String artista;

  @NotBlank
  private String album;

  @NotBlank
  private String anno;

  @NotBlank
  private String genero;
}
