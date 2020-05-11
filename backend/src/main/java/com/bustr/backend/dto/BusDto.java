package com.bustr.backend.dto;

import com.bustr.backend.model.Line;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusDto {

  private String code;
  @NotNull(message = "Latitude is mandatory")
  private Double latitude;
  @NotNull(message = "Longitude is mandatory")
  private Double longitude;
  @NotNull(message = "Line is mandatory")
  private Line line;
}
