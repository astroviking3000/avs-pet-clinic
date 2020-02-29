package com.astroviking.avspetclinic.bootstrap;

import com.astroviking.avspetclinic.model.Owner;
import com.astroviking.avspetclinic.model.Pet;
import com.astroviking.avspetclinic.model.PetType;
import com.astroviking.avspetclinic.model.Vet;
import com.astroviking.avspetclinic.services.OwnerService;
import com.astroviking.avspetclinic.services.PetTypeService;
import com.astroviking.avspetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

  private final OwnerService ownerService;
  private final VetService vetService;
  private final PetTypeService petTypeService;

  public DataLoader(
      OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
    this.ownerService = ownerService;
    this.vetService = vetService;
    this.petTypeService = petTypeService;
  }

  @Override
  public void run(String... args) {

    PetType dog = new PetType();
    dog.setName("Dog");
    PetType savedDogPetType = petTypeService.save(dog);

    PetType cat = new PetType();
    dog.setName("Cat");
    PetType savedCatPetType = petTypeService.save(cat);

    Owner owner1 = new Owner();
    owner1.setFirstName("John");
    owner1.setLastName("Smith");
    owner1.setAddress("123 Candy Lane");
    owner1.setCity("Blemis");
    owner1.setTelephone("111-222-3333");

    Pet dog1 = new Pet();
    dog1.setPetType(savedDogPetType);
    dog1.setOwner(owner1);
    dog1.setName("Bingo");
    dog1.setBirthDate(LocalDate.now());

    owner1.getPets().add(dog1);

    ownerService.save(owner1);

    Owner owner2 = new Owner();
    owner2.setFirstName("Jim");
    owner2.setLastName("Brown");
    owner2.setAddress("456 Tree Street");
    owner2.setCity("Atlanata");
    owner2.setTelephone("444-555-6666");

    Pet cat1 = new Pet();
    cat1.setPetType(savedCatPetType);
    cat1.setName("Meower");
    cat1.setBirthDate(LocalDate.now());
    cat1.setOwner(owner2);

    owner2.getPets().add(cat1);

    ownerService.save(owner2);

    System.out.println("Loading Owners...");

    Vet vet1 = new Vet();
    vet1.setFirstName("Carol");
    vet1.setLastName("Adams");

    vetService.save(vet1);

    Vet vet2 = new Vet();
    vet2.setFirstName("Steve");
    vet2.setLastName("Jones");

    vetService.save(vet2);

    System.out.println("Loading Vets...");
  }
}
