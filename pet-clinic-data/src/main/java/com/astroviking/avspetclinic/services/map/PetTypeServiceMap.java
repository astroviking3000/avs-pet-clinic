package com.astroviking.avspetclinic.services.map;

import com.astroviking.avspetclinic.model.PetType;
import com.astroviking.avspetclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PetTypeServiceMap extends AbstractMapService<PetType, Long> implements PetTypeService {

  @Override
  public PetType findById(Long id) {
    return super.findById(id);
  }

  @Override
  public PetType save(PetType petType) {
    return super.save(petType);
  }

  @Override
  public Set<PetType> findAll() {
    return super.findAll();
  }

  @Override
  public void delete(PetType petType) {
    super.delete(petType);
  }

  @Override
  public void deleteById(Long id) {
    super.deleteById(id);
  }
}
