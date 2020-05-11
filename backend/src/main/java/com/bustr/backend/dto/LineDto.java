package com.bustr.backend.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LineDto {

  private String code;
  @NotBlank(message = "Name is mandatory")
  private String name;
  @NotBlank(message = "Forward is mandatory")
  private String forward;
  @NotBlank(message = "Backward is mandatory")
  private String backward;
  @NotBlank(message = "Color is mandatory")
  private String color;
}
