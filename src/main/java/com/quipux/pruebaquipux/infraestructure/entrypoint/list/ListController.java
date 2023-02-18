package com.quipux.pruebaquipux.infraestructure.entrypoint.list;

import com.quipux.pruebaquipux.domain.usecase.list.ListService;
import com.quipux.pruebaquipux.infraestructure.entrypoint.list.in.NewListRequest;
import com.quipux.pruebaquipux.infraestructure.entrypoint.list.out.AllListsResponse;
import com.quipux.pruebaquipux.infraestructure.entrypoint.list.out.ListDeleteResponse;
import com.quipux.pruebaquipux.infraestructure.entrypoint.list.out.NewListResponse;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.net.URI;

@RestController
@RequestMapping("/lists")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Validated
public class ListController {

  private final ListService listService;

  @Operation(summary = "Crea una nueva lista de musica", description = "Create una nueva lista con las canciones" +
      "que le adjunten ", responses = {
      @ApiResponse(responseCode = "201", description = "Created"),
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
  public ResponseEntity<NewListResponse> createList(@NotNull @RequestBody NewListRequest request){
    NewListResponse list = listService.createAList(request);
    final long id = list.getId();

    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    list.setLocation(location);

    return new ResponseEntity<>(list, HttpStatus.CREATED);
  }
  @Operation(summary = "Retorna todas las listas", description =  "Retorna todas las listas creadas con " +
      "sus respectivas canciones", responses = {
      @ApiResponse(responseCode = "200", description = "Ok"),
      @ApiResponse(responseCode = "400", description = "Bad Request",
          content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              array = @ArraySchema(schema = @Schema(implementation = ErrorDetails.class)))}),
      @ApiResponse(responseCode = "409", description = "Conflict",
          content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ErrorDetails.class))}),
      @ApiResponse(responseCode = "403", description = "Forbidden",
          content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ErrorDetails.class))})})
  @GetMapping
  public ResponseEntity<AllListsResponse> getAllList(){
    return new ResponseEntity<>(listService.getAllList(), HttpStatus.OK);
  }

  @Operation(summary = "Retorna una lista por nombre", description = "Retorna una lista en especifico segun el nombre" +
      "ingresado ", responses = {
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
  @GetMapping("/{listName}")
  public ResponseEntity<NewListResponse> getAList(@NotBlank @PathVariable String listName){
    return new ResponseEntity<>(listService.getListByNAme(listName), HttpStatus.OK);
  }

  @Operation(summary = "Elimina una lista", description = "Elimina una lista de acuerdo al nombre ingresado",
      responses = {
      @ApiResponse(responseCode = "204", description = "Not Content"),
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
  @DeleteMapping("/{listName}")
  public ResponseEntity<ListDeleteResponse> deleteAList(@NotBlank @PathVariable String listName){
    return new ResponseEntity<>(listService.deleteAlistByName(listName), HttpStatus.NO_CONTENT);
  }

}
