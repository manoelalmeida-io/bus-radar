package com.bustr.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PrimaryKeyConstraintException extends RuntimeException {

  public PrimaryKeyConstraintException() {
    super("Insert a valid primary key");
  }
}
