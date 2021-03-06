package com.training.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ROLE")
public class Role extends CrudEntity {

  @Id
  private Long id;

  @NotNull
  private String code;

  @NotNull
  private String label;

  public Role() {
  }

  public Role(Long id, String code,
      String label) {
    this.id = id;
    this.code = code;
    this.label = label;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }
}
