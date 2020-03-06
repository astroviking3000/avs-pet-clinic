package com.astroviking.avspetclinic.services.jpa;

import com.astroviking.avspetclinic.model.Owner;
import com.astroviking.avspetclinic.repositories.OwnerRepository;
import com.astroviking.avspetclinic.repositories.PetRepository;
import com.astroviking.avspetclinic.repositories.PetTypeRepository;
import com.astroviking.avspetclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("jpa")
public class OwnerServiceJPA implements OwnerService {

  private final OwnerRepository ownerRepository;
  private final PetRepository petRepository;
  private final PetTypeRepository petTypeRepository;

  public OwnerServiceJPA(
      OwnerRepository ownerRepository,
      PetRepository petRepository,
      PetTypeRepository petTypeRepository) {
    this.ownerRepository = ownerRepository;
    this.petRepository = petRepository;
    this.petTypeRepository = petTypeRepository;
  }

  @Override
  public Owner findByLastName(String lastName) {
    return ownerRepository.findByLastName(lastName);
  }

  @Override
  public Owner findById(Long id) {
    return ownerRepository.findById(id).orElse(null);
  }

  @Override
  public Owner save(Owner object) {
    return ownerRepository.save(object);
  }

  @Override
  public Set<Owner> findAll() {
    Set<Owner> owners = new HashSet<>();
    ownerRepository.findAll().forEach(owners::add);
    return owners;
  }

  @Override
  public void delete(Owner owner) {
    ownerRepository.delete(owner);
  }

  @Override
  public void deleteById(Long id) {
    ownerRepository.deleteById(id);
  }
}
