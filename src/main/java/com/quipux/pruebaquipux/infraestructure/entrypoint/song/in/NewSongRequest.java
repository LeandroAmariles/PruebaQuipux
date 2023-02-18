package com.quipux.pruebaquipux.infraestructure.entrypoint.song.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
public class NewSongRequest {

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

  @NotNull(message = "La cancion debe asociarse a una lista, ingrese el id de la lista plsss")
  @JsonProperty("idListaDeReproduccion")
  private Long idLista;

  @Override
  public String toString(){
    return "Cancion de titulo "+titulo+" del artista "+ artista;
  }
}
