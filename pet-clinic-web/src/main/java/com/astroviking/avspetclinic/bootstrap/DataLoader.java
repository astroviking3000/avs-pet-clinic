package com.astroviking.avspetclinic.bootstrap;

import com.astroviking.avspetclinic.model.Owner;
import com.astroviking.avspetclinic.model.Vet;
import com.astroviking.avspetclinic.services.OwnerService;
import com.astroviking.avspetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

  private final OwnerService ownerService;
  private final VetService vetService;

  public DataLoader(OwnerService ownerService, VetService vetService) {
    this.ownerService = ownerService;
    this.vetService = vetService;
  }

  @Override
  public void run(String... args) {
    Owner owner1 = new Owner();
    owner1.setFirstName("John");
    owner1.setLastName("Smith");

    ownerService.save(owner1);

    Owner owner2 = new Owner();
    owner2.setFirstName("Jim");
    owner2.setLastName("Brown");

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
