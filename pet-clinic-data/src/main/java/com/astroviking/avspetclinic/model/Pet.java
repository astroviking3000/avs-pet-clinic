package com.astroviking.avspetclinic.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pets")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Pet extends BaseEntity {

  private String name;

  @Column(name = "birth_date")
  private LocalDate birthDate;

  @ManyToOne
  @JoinColumn(name = "pet_type_id")
  private PetType petType;

  @ManyToOne
  @JoinColumn(name = "owner_id")
  private Owner owner;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
  private Set<Visit> visits = new HashSet<>();

  @Builder
  public Pet(
      Long id, String name, LocalDate birthDate, PetType petType, Owner owner, Set<Visit> visits) {
    super(id);
    this.name = name;
    this.birthDate = birthDate;
    this.petType = petType;
    this.owner = owner;
    this.visits = visits;
  }
}
