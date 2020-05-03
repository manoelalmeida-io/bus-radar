package com.bustr.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_bus")
public class Bus {

  @Id
  private String code;
  @Column(name = "coordinate_x", columnDefinition = "numeric")
  private Double coordinateX;
  @Column(name = "coordinate_y", columnDefinition = "numeric")
  private Double coordinateY;

  @ManyToOne
  @JoinColumn(name = "line", nullable = false)
  private Line line;
}
