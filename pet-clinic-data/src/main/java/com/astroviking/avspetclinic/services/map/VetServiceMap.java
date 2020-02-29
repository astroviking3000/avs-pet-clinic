package com.astroviking.avspetclinic.services.map;

import com.astroviking.avspetclinic.model.Specialty;
import com.astroviking.avspetclinic.model.Vet;
import com.astroviking.avspetclinic.services.SpecialtyService;
import com.astroviking.avspetclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

  private final SpecialtyService specialtyService;

  public VetServiceMap(SpecialtyService specialtyService) {
    this.specialtyService = specialtyService;
  }

  @Override
  public Vet findById(Long id) {
    return super.findById(id);
  }

  @Override
  public Vet save(Vet vet) {
    vet.getSpecialties()
        .forEach(
            specialty -> {
              if (specialty.getId() == null) {
                Specialty savedSpecialty = specialtyService.save(specialty);
                specialty.setId(savedSpecialty.getId());
              }
            });

    return super.save(vet);
  }

  @Override
  public Set<Vet> findAll() {
    return super.findAll();
  }

  @Override
  public void deleteById(Long id) {
    super.deleteById(id);
  }

  @Override
  public void delete(Vet object) {
    super.delete(object);
  }
}
