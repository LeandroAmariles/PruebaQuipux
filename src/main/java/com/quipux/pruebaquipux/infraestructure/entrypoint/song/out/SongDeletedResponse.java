package com.quipux.pruebaquipux.infraestructure.entrypoint.song.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SongDeletedResponse {

  private String message;
}
