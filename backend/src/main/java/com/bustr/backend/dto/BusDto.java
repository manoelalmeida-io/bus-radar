package com.bustr.backend.dto;

import com.bustr.backend.model.Line;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusDto {

  private String code;
  private Double coordinateX;
  private Double coordinateY;
  private Line line;
}
