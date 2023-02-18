package com.quipux.pruebaquipux.domain.usecase.list;

import com.quipux.pruebaquipux.domain.entities.Lista;
import com.quipux.pruebaquipux.domain.usecase.list.mapper.ListMapper;
import com.quipux.pruebaquipux.infraestructure.entrypoint.list.in.NewListRequest;
import com.quipux.pruebaquipux.infraestructure.entrypoint.list.out.AllListsResponse;
import com.quipux.pruebaquipux.infraestructure.entrypoint.list.out.ListDeleteResponse;
import com.quipux.pruebaquipux.infraestructure.entrypoint.list.out.NewListResponse;
import com.quipux.pruebaquipux.infraestructure.exception.ConflictException;
import com.quipux.pruebaquipux.infraestructure.exception.CreateException;
import com.quipux.pruebaquipux.infraestructure.repository.ListRepository;
import com.quipux.pruebaquipux.infraestructure.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListServiceImpl implements ListService{

  private final ListRepository listRepository;

  private final SongRepository songRepository;

  private final ListMapper listMapper;


  @Override
  @Transactional
  public NewListResponse createAList(NewListRequest request) {
    if(!canCreate(request.getNombreLista())){
      throw new CreateException("No se puede crear la lista de reproduccion");
    }
    Lista lista = listMapper.requestToEntity(request);
    lista.getCanciones().stream().map((song ->
        songRepository.save(song)
    ));
    listRepository.save(lista);



    return listMapper.entityToResponse(lista);
  }

  @Override
  @Transactional
  public AllListsResponse getAllList() {
    List<Lista> listas = listRepository.findAll();

    List<NewListResponse> responseList = listas.stream().map((list)-> listMapper.entityToResponse(list))
        .collect(Collectors.toList());

    return AllListsResponse.builder().listas(responseList).build();
  }

  @Override
  @Transactional(readOnly = true)
  public NewListResponse getListByNAme(String name) {
    if(!listRepository.existsByNombre(name)){
      throw new ConflictException(String.format("no se encontro la lista con nombre %s",name));
    }
    NewListResponse listResponse = listMapper.entityToResponse(listRepository.findByNombre(name));

    return listResponse;
  }

  @Override
  @Transactional
  public ListDeleteResponse deleteAlistByName(String name) {
    if(!listRepository.existsByNombre(name)){
      throw new ConflictException(String.format("no se encontro la lista con nombre %s",name));
    }
    Lista list = listRepository.findByNombre(name);

    listRepository.deleteById(list.getId());

    return ListDeleteResponse.builder().message(" La lista fue eliminada exitosamente").build();

  }


  private Boolean canCreate(String nombre){
    if(listRepository.existsByNombre(nombre)){
      throw new ConflictException(String.format(
          "La lista de nombre %s no se puede crear porque ya existe una con ese nombre",nombre
      ));
    }
    return true;
  }
}
