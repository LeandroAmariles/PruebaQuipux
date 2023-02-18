package com.quipux.pruebaquipux.domain.usecase.list.mapper;


import com.quipux.pruebaquipux.domain.entities.Lista;
import com.quipux.pruebaquipux.infraestructure.entrypoint.list.in.NewListRequest;
import com.quipux.pruebaquipux.infraestructure.entrypoint.list.out.NewListResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ListMapper {

  @Mapping(target = "canciones", source = "songs")
  @Mapping(target = "nombre", source = "nombreLista")
  public Lista requestToEntity(NewListRequest request);

  @Mapping(target = "nombreLista", source = "nombre")
  @Mapping(target = "songs", source = "canciones")
  public NewListResponse entityToResponse(Lista lista);
}
