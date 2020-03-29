package com.astroviking.avspetclinic.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pet_types")
@Setter
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PetType extends BaseEntity {

  private String name;

  @Builder
  public PetType(Long id, String name) {
    super(id);
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
