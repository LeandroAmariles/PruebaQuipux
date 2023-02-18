package com.quipux.pruebaquipux.domain.usecase.song.mapper;

import com.quipux.pruebaquipux.domain.entities.Song;
import com.quipux.pruebaquipux.infraestructure.entrypoint.song.in.NewSongRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SongMapper {

  public Song requestToEntity(NewSongRequest request);

}
