package com.bustr.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_stop")
public class Stop {

  @Id
  private String code;
  @Column(columnDefinition = "numeric")
  private Double latitude;
  @Column(columnDefinition = "numeric")
  private Double longitude;
  private String address;
}
