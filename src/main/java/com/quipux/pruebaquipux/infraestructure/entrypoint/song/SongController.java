package com.quipux.pruebaquipux.infraestructure.entrypoint.song;


import com.quipux.pruebaquipux.domain.usecase.song.SongService;
import com.quipux.pruebaquipux.infraestructure.entrypoint.song.in.NewSongRequest;
import com.quipux.pruebaquipux.infraestructure.entrypoint.song.out.NewSongResponse;
import com.quipux.pruebaquipux.infraestructure.entrypoint.song.out.SongDeletedResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class SongController {

  private final SongService songService;

  @PostMapping
  public ResponseEntity<NewSongResponse> createSong(@Valid @RequestBody NewSongRequest request){

    NewSongResponse response = songService.saveNewSong(request);
    final long id = response.getId();
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    response.setLocation(location);

    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @DeleteMapping
  public ResponseEntity<SongDeletedResponse> deleteSong(@NotNull @PathVariable Long id){

    SongDeletedResponse response = songService.deleteSong(id);

    return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
  }




}
