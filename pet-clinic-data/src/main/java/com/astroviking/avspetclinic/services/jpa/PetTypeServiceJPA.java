package com.astroviking.avspetclinic.services.jpa;

import com.astroviking.avspetclinic.model.PetType;
import com.astroviking.avspetclinic.repositories.PetTypeRepository;
import com.astroviking.avspetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("jpa")
public class PetTypeServiceJPA implements PetTypeService {

  private final PetTypeRepository petTypeRepository;

  public PetTypeServiceJPA(PetTypeRepository petTypeRepository) {
    this.petTypeRepository = petTypeRepository;
  }

  @Override
  public PetType findById(Long aLong) {
    return petTypeRepository.findById(aLong).orElse(null);
  }

  @Override
  public PetType save(PetType object) {
    return petTypeRepository.save(object);
  }

  @Override
  public Set<PetType> findAll() {
    Set<PetType> petTypes = new HashSet<>();
    petTypeRepository.findAll().forEach(petTypes::add);
    return petTypes;
  }

  @Override
  public void delete(PetType object) {
    petTypeRepository.delete(object);
  }

  @Override
  public void deleteById(Long aLong) {
    petTypeRepository.deleteById(aLong);
  }
}
