package com.astroviking.avspetclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "owners")
@Setter
@Getter
@NoArgsConstructor
public class Owner extends Person {

  private String address;
  private String city;
  private String telephone;

  @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
  private Set<Pet> pets = new HashSet<>();

  @Builder
  public Owner(
      Long id, String firstName, String lastName, String address, String city, String telephone) {
    super(id, firstName, lastName);
    this.address = address;
    this.city = city;
    this.telephone = telephone;
  }
}
