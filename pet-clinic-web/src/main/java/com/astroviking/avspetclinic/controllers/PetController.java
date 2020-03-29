package com.astroviking.avspetclinic.controllers;

import com.astroviking.avspetclinic.model.Owner;
import com.astroviking.avspetclinic.model.Pet;
import com.astroviking.avspetclinic.model.PetType;
import com.astroviking.avspetclinic.services.OwnerService;
import com.astroviking.avspetclinic.services.PetService;
import com.astroviking.avspetclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}/pets")
public class PetController {

  public static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

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

  @GetMapping("/new")
  public String initCreationForm(Owner owner, Model model) {
    Pet pet = new Pet();
    owner.getPets().add(pet);
    pet.setOwner(owner);
    model.addAttribute("pet", pet);
    return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
  }

  @PostMapping("/new")
  public String processCreationForm(
      Owner owner, @Valid Pet pet, BindingResult result, Model model) {
    if (StringUtils.hasLength(pet.getName())
        && pet.isNew()
        && owner.getPet(pet.getName(), true) != null) {
      result.rejectValue("name", "duplicate", "already exists");
    }
    owner.getPets().add(pet);
    if (result.hasErrors()) {
      model.addAttribute("pet", pet);
      return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    } else {
      petService.save(pet);
      return "redirect:/owners/" + owner.getId();
    }
  }

  @GetMapping("/{petId}/edit")
  public String initUpdateForm(@PathVariable("petId") Long petId, Model model) {
    Pet pet = petService.findById(petId);
    model.addAttribute("pet", pet);
    return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
  }

  @PostMapping("/{petId}/edit")
  public String processUpdateForm(@Valid Pet pet, BindingResult result, Owner owner, Model model) {
    if (result.hasErrors()) {
      pet.setOwner(owner);
      model.addAttribute("pet", pet);
      return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    } else {
      owner.getPets().add(pet);
      petService.save(pet);
      return "redirect:/owners/" + owner.getId();
    }
  }
}
