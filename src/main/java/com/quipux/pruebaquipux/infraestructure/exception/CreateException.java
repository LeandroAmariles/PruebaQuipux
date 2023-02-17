package com.quipux.pruebaquipux.infraestructure.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateException extends RuntimeException{

  private static final long serialVersionUID = 1L;

  private final String conflictMessage;

}
