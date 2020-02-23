package com.astroviking.avspetclinic.services.map;

import com.astroviking.avspetclinic.model.Owner;
import com.astroviking.avspetclinic.services.OwnerService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

  @Override
  public Owner findById(Long id) {
    return super.findById(id);
  }

  @Override
  public Owner save(Owner owner) {
    return super.save(owner);
  }

  @Override
  public Set<Owner> findAll() {
    return super.findAll();
  }

  @Override
  public void deleteById(Long id) {
    super.deleteById(id);
  }

  @Override
  public void delete(Owner object) {
    super.delete(object);
  }

  @Override
  public Owner findByLastName(String lastName) {
    return super.findAll().stream()
        .filter(entry -> entry.getLastName().equals(lastName))
        .findFirst()
        .orElse(null);
  }
}
