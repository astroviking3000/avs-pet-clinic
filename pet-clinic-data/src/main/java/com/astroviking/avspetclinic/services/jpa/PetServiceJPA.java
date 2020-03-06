package com.astroviking.avspetclinic.services.jpa;

import com.astroviking.avspetclinic.model.Pet;
import com.astroviking.avspetclinic.repositories.PetRepository;
import com.astroviking.avspetclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("jpa")
public class PetServiceJPA implements PetService {

  private final PetRepository petRepository;

  public PetServiceJPA(PetRepository petRepository) {
    this.petRepository = petRepository;
  }

  @Override
  public Pet findById(Long aLong) {
    return petRepository.findById(aLong).orElse(null);
  }

  @Override
  public Pet save(Pet object) {
    return petRepository.save(object);
  }

  @Override
  public Set<Pet> findAll() {
    Set<Pet> pets = new HashSet<>();
    petRepository.findAll().forEach(pets::add);
    return pets;
  }

  @Override
  public void delete(Pet object) {
    petRepository.delete(object);
  }

  @Override
  public void deleteById(Long aLong) {
    petRepository.deleteById(aLong);
  }
}
