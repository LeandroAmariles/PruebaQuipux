package com.quipux.pruebaquipux.domain.usecase.song;

import com.quipux.pruebaquipux.domain.entities.Lista;
import com.quipux.pruebaquipux.domain.entities.Song;
import com.quipux.pruebaquipux.domain.usecase.song.mapper.SongMapper;
import com.quipux.pruebaquipux.infraestructure.entrypoint.song.in.NewSongRequest;
import com.quipux.pruebaquipux.infraestructure.entrypoint.song.out.NewSongResponse;
import com.quipux.pruebaquipux.infraestructure.entrypoint.song.out.SongDeletedResponse;
import com.quipux.pruebaquipux.infraestructure.exception.ConflictException;
import com.quipux.pruebaquipux.infraestructure.exception.CreateException;
import com.quipux.pruebaquipux.infraestructure.repository.ListRepository;
import com.quipux.pruebaquipux.infraestructure.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService{


  private final SongRepository songRepository;

  private final ListRepository listRepository;

  private final SongMapper songMapper;


  @Override
  public NewSongResponse saveNewSong(NewSongRequest request) {
    if (!canCreate(request.getTitulo(), request.getIdLista())){
      throw new CreateException("No se puede guardar la cancion");
    }
    Song song = songMapper.requestToEntity(request);
    Song song1 = songRepository.save(song);
    Lista lista = listRepository.findById(request.getIdLista()).get();
    lista.getCanciones().add(song);
    return NewSongResponse.builder().artista(song.getArtista())
        .titulo(song.getTitulo())
        .id(song1.getId())
        .anno(song.getAnno())
        .build();
  }

  @Override
  public SongDeletedResponse deleteSong(Long id) {
    songRepository.findById(id).ifPresent(songRepository::delete);
    return SongDeletedResponse.builder().message(String.format("La cancion con  id %s fue borrada ", id)).build();
  }

  private Boolean canCreate(String nombre, Long id){
    if(songRepository.existsByTitulo(nombre)){
      throw new ConflictException(
          String.format("baia baia ya hay una cancion con el titulo %s no se ha podido guardar",nombre));
    }
    if(!listRepository.existsById(id)){
      throw new ConflictException(
          String.format("No se encontro la lista con el id %s la cancion no puede ser guardada",id));
    }
    return true;
  }
}
