package com.bustr.backend.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StopDto {

  private String code;
  @NotNull(message = "Latitude is mandatory")
  private Double latitude;
  @NotNull(message = "Longitude is mandatory")
  private Double longitude;
  @NotEmpty(message = "Address is mandatory")
  private String address;
}
