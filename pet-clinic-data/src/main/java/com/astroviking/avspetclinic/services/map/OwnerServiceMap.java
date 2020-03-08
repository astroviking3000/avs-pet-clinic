package com.astroviking.avspetclinic.services.map;

import com.astroviking.avspetclinic.model.Owner;
import com.astroviking.avspetclinic.model.Pet;
import com.astroviking.avspetclinic.services.OwnerService;
import com.astroviking.avspetclinic.services.PetService;
import com.astroviking.avspetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

  private final PetTypeService petTypeService;
  private final PetService petService;

  public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
    this.petTypeService = petTypeService;
    this.petService = petService;
  }

  @Override
  public Owner findById(Long id) {
    return super.findById(id);
  }

  @Override
  public Owner save(Owner owner) {
    if (owner != null) {
      if (owner.getPets() != null) {
        owner
            .getPets()
            .forEach(
                pet -> {
                  if (pet.getPetType() != null) {
                    if (pet.getPetType().getName() == null) {
                      pet.setPetType(petTypeService.save(pet.getPetType()));
                    }
                  } else {
                    throw new RuntimeException("Pet Type is required");
                  }

                  if (pet.getId() == null) {
                    Pet savedPet = petService.save(pet);
                    pet.setId(savedPet.getId());
                  }
                });
      }
      return super.save(owner);
    } else {
      return null;
    }
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
