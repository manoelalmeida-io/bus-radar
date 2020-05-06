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
  @Column(columnDefinition = "numeric")
  private Double latitude;
  @Column(columnDefinition = "numeric")
  private Double longitude;

  @ManyToOne
  @JoinColumn(name = "line", nullable = false)
  private Line line;
}
