package com.bustr.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LineDto {

  private String code;
  private String name;
  private String forward;
  private String backward;
  private String color;
}
