package com.quipux.pruebaquipux.domain.usecase.song;

import com.quipux.pruebaquipux.infraestructure.entrypoint.song.in.NewSongRequest;
import com.quipux.pruebaquipux.infraestructure.entrypoint.song.out.NewSongResponse;
import com.quipux.pruebaquipux.infraestructure.entrypoint.song.out.SongDeletedResponse;

public interface SongService {

  public NewSongResponse saveNewSong(NewSongRequest request);

  public SongDeletedResponse deleteSong(Long id);
}
