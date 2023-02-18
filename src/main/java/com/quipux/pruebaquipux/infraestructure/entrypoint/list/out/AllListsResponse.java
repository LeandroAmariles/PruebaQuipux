package com.quipux.pruebaquipux.infraestructure.entrypoint.list.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Builder
@Setter
@Getter
public class AllListsResponse {

  private List<NewListResponse> listas;
}
