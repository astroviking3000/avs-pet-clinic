package com.astroviking.avspetclinic.controllers;

import com.astroviking.avspetclinic.model.Owner;
import com.astroviking.avspetclinic.model.PetType;
import com.astroviking.avspetclinic.services.OwnerService;
import com.astroviking.avspetclinic.services.PetService;
import com.astroviking.avspetclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/pet")
public class PetController {

  private final PetService petService;
  private final PetTypeService petTypeService;
  private final OwnerService ownerService;

  public PetController(
      PetService petService, PetTypeService petTypeService, OwnerService ownerService) {
    this.petService = petService;
    this.petTypeService = petTypeService;
    this.ownerService = ownerService;
  }

  @ModelAttribute("types")
  public Collection<PetType> populatePetTYpes() {
    return petTypeService.findAll();
  }

  @ModelAttribute("owner")
  public Owner findOwner(@PathVariable Long ownerId) {
    return ownerService.findById(ownerId);
  }

  @InitBinder("owner")
  public void initOwnerBinder(WebDataBinder webDataBinder) {
    webDataBinder.setDisallowedFields("id");
  }
}
