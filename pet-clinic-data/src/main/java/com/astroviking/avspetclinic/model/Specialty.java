package com.astroviking.avspetclinic.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "specialties")
@Setter
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Specialty extends BaseEntity {
  private String name;
}
