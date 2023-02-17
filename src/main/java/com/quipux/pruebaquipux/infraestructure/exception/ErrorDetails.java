package com.quipux.pruebaquipux.infraestructure.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class ErrorDetails {

  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private Date timestamp;
  private String message;
  private String details;
}
