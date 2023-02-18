package com.quipux.pruebaquipux.infraestructure.entrypoint.song;


import com.quipux.pruebaquipux.domain.usecase.song.SongService;
import com.quipux.pruebaquipux.infraestructure.entrypoint.song.in.NewSongRequest;
import com.quipux.pruebaquipux.infraestructure.entrypoint.song.out.NewSongResponse;
import com.quipux.pruebaquipux.infraestructure.entrypoint.song.out.SongDeletedResponse;
import com.quipux.pruebaquipux.infraestructure.exception.ErrorDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

@RestController
@RequestMapping
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Validated
public class SongController {

  private final SongService songService;

  @Operation(summary = "Guarda una cancion", description = "Guarda una cancion pero debe ligarse a una playlist por el id" +
      "de la playlist o no se guardara", responses = {
      @ApiResponse(responseCode = "201", description = "Created"),
      @ApiResponse(responseCode = "404", description = "Not Found",
          content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              array = @ArraySchema(schema = @Schema(implementation = ErrorDetails.class)))}),
      @ApiResponse(responseCode = "400", description = "Bad Request",
          content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              array = @ArraySchema(schema = @Schema(implementation = ErrorDetails.class)))}),
      @ApiResponse(responseCode = "409", description = "Conflict",
          content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ErrorDetails.class))}),
      @ApiResponse(responseCode = "403", description = "Forbidden",
          content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ErrorDetails.class))})})
  @PostMapping
  public ResponseEntity<NewSongResponse> createSong(@Valid @RequestBody NewSongRequest request){

    NewSongResponse response = songService.saveNewSong(request);
    final long id = response.getId();
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    response.setLocation(location);

    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @Operation(summary = "Elimina una cancion", description = "Elimina una cancion de acuerdo al id ingresado", responses = {
      @ApiResponse(responseCode = "200", description = "Ok"),
      @ApiResponse(responseCode = "404", description = "Not Found",
          content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              array = @ArraySchema(schema = @Schema(implementation = ErrorDetails.class)))}),
      @ApiResponse(responseCode = "400", description = "Bad Request",
          content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              array = @ArraySchema(schema = @Schema(implementation = ErrorDetails.class)))}),
      @ApiResponse(responseCode = "409", description = "Conflict",
          content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ErrorDetails.class))}),
      @ApiResponse(responseCode = "403", description = "Forbidden",
          content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ErrorDetails.class))})})
  @DeleteMapping("/{id}")
  public ResponseEntity<SongDeletedResponse> deleteSong(@NotNull @PathVariable Long id){

    SongDeletedResponse response = songService.deleteSong(id);

    return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
  }




}
