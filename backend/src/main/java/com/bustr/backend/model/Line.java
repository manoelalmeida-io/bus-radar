package com.bustr.backend.model;

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
@Table(name = "tb_line")
public class Line {

  @Id
  private String code;
  private String name;
  private String forward;
  private String backward;
  private String color;
}
