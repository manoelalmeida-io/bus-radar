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
import org.springframework.data.geo.Point;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_bus")
public class Bus {

  @Id
  private String code;

  @Column(columnDefinition = "point")
  private Point location;

  @ManyToOne
  @JoinColumn(name = "line", nullable = false)
  private Line line;
}
