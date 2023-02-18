package com.quipux.pruebaquipux.infraestructure.entrypoint.list;

import com.quipux.pruebaquipux.domain.usecase.list.ListService;
import com.quipux.pruebaquipux.infraestructure.entrypoint.list.in.NewListRequest;
import com.quipux.pruebaquipux.infraestructure.entrypoint.list.out.AllListsResponse;
import com.quipux.pruebaquipux.infraestructure.entrypoint.list.out.ListDeleteResponse;
import com.quipux.pruebaquipux.infraestructure.entrypoint.list.out.NewListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/lists")
@RequiredArgsConstructor
public class ListController {

  private final ListService listService;


  @PostMapping
  public ResponseEntity<NewListResponse> createList(@NotNull @RequestBody NewListRequest request){
    NewListResponse list = listService.createAList(request);
    return new ResponseEntity<>(list, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<AllListsResponse> getAllList(){
    return new ResponseEntity<>(listService.getAllList(), HttpStatus.OK);
  }

  @GetMapping("/{listName}")
  public ResponseEntity<NewListResponse> getAList(@NotBlank @PathVariable String listName){
    return new ResponseEntity<>(listService.getListByNAme(listName), HttpStatus.OK);
  }

  @DeleteMapping("/{listName}")
  public ResponseEntity<ListDeleteResponse> deleteAList(@NotBlank @PathVariable String listName){
    return new ResponseEntity<>(listService.deleteAlistByName(listName), HttpStatus.NO_CONTENT);
  }

}
