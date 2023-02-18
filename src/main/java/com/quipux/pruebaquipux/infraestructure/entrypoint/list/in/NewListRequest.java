package com.quipux.pruebaquipux.infraestructure.entrypoint.list.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.quipux.pruebaquipux.infraestructure.entrypoint.song.in.NewSongRequest;
import com.quipux.pruebaquipux.infraestructure.entrypoint.song.in.NewSongRequestList;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Builder
@Getter
@Setter
public class NewListRequest {


  @NotBlank(message = "El nombre no debe estar vacio")
  @JsonProperty("nombre")
  private String nombreLista;

  @JsonProperty("descripcion")
  @NotBlank(message = "Agrege una descripcion para su lista, ej musica para la lloracion")
  private String descripcion;

  @JsonProperty("canciones")
  private List<NewSongRequestList> songs;

}
