package com.quipux.pruebaquipux.domain.usecase.list;

import com.quipux.pruebaquipux.domain.entities.Lista;
import com.quipux.pruebaquipux.infraestructure.entrypoint.list.in.NewListRequest;
import com.quipux.pruebaquipux.infraestructure.entrypoint.list.out.AllListsResponse;
import com.quipux.pruebaquipux.infraestructure.entrypoint.list.out.ListDeleteResponse;
import com.quipux.pruebaquipux.infraestructure.entrypoint.list.out.NewListResponse;

import java.util.List;

public interface ListService {

  public NewListResponse createAList(NewListRequest request);

  public AllListsResponse getAllList();

  public NewListResponse getListByNAme(String name);

  public ListDeleteResponse deleteAlistByName(String name);


}
