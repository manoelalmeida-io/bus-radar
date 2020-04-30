package com.bustr.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException() {
    super("Cannot find resource");
  }

  public ResourceNotFoundException(String code) {
    super("Cannot find resource with code: " + code);
  }
}
