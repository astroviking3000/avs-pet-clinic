package com.astroviking.avspetclinic.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "visits")
@Setter
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Visit extends BaseEntity {

  private LocalDate date;
  private String description;

  @ManyToOne
  @JoinColumn(name = "pet_id")
  private Pet pet;
}
