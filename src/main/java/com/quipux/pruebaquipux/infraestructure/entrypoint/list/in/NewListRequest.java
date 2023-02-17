package com.quipux.pruebaquipux.infraestructure.entrypoint.list.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.quipux.pruebaquipux.domain.entities.Song;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.List;

@Builder
public class NewListRequest {


  @NotBlank(message = "El nombre no debe estar vacio")
  @JsonProperty("nombre")
  private String nombreLista;

  @JsonProperty("descripcion")
  @NotBlank(message = "Agrege una descripcion para su lista, ej musica para la lloracion")
  private String descripcion;

  @JsonProperty("canciones")
  private List<Song> songs;

}
