package com.astroviking.avspetclinic.services.jpa;

import com.astroviking.avspetclinic.model.Owner;
import com.astroviking.avspetclinic.repositories.OwnerRepository;
import com.astroviking.avspetclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Profile("jpa")
public class OwnerServiceJPA implements OwnerService {

  private final OwnerRepository ownerRepository;

  public OwnerServiceJPA(OwnerRepository ownerRepository) {
    this.ownerRepository = ownerRepository;
  }

  @Override
  public Owner findByLastName(String lastName) {
    return ownerRepository.findByLastName(lastName);
  }

  @Override
  public List<Owner> findAllByLastNameLike(String lastName) {
    return ownerRepository.findAllByLastNameLike(lastName);
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
