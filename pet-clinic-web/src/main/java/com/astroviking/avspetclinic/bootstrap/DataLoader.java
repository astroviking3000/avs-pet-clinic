package com.astroviking.avspetclinic.bootstrap;

import com.astroviking.avspetclinic.model.*;
import com.astroviking.avspetclinic.services.OwnerService;
import com.astroviking.avspetclinic.services.PetTypeService;
import com.astroviking.avspetclinic.services.SpecialtyService;
import com.astroviking.avspetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

  private final OwnerService ownerService;
  private final VetService vetService;
  private final PetTypeService petTypeService;
  private final SpecialtyService specialtyService;

  public DataLoader(
      OwnerService ownerService,
      VetService vetService,
      PetTypeService petTypeService,
      SpecialtyService specialtyService) {
    this.ownerService = ownerService;
    this.vetService = vetService;
    this.petTypeService = petTypeService;
    this.specialtyService = specialtyService;
  }

  @Override
  public void run(String... args) {
    int count = petTypeService.findAll().size();
    if (count == 0) loadData();
  }

  private void loadData() {
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

    Specialty radiology = new Specialty();
    radiology.setName("Radiology");
    Specialty savedRadiology = specialtyService.save(radiology);

    Specialty surgery = new Specialty();
    surgery.setName("Surgery");
    Specialty savedSurgery = specialtyService.save(surgery);

    Specialty dentistry = new Specialty();
    dentistry.setName("Dentistry");
    Specialty savedDentistry = specialtyService.save(dentistry);

    Vet vet1 = new Vet();
    vet1.setFirstName("Carol");
    vet1.setLastName("Adams");
    vet1.getSpecialties().add(savedRadiology);

    vetService.save(vet1);

    Vet vet2 = new Vet();
    vet2.setFirstName("Steve");
    vet2.setLastName("Jones");
    vet2.getSpecialties().add(savedSurgery);

    vetService.save(vet2);

    System.out.println("Loading Vets...");
  }
}
