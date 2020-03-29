package com.astroviking.avspetclinic.controllers;

import com.astroviking.avspetclinic.model.Owner;
import com.astroviking.avspetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/owners")
public class OwnerController {

  public static final String VIEW_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
  private final OwnerService ownerService;

  public OwnerController(OwnerService ownerService) {
    this.ownerService = ownerService;
  }

  @InitBinder
  public void setAllowedFields(WebDataBinder dataBinder) {
    dataBinder.setDisallowedFields("id");
  }

  @GetMapping
  public String processFindForm(Owner owner, BindingResult bindingResult, Model model) {

    if (owner.getLastName() == null) {
      owner.setLastName("");
    }

    // find owners by last name
    List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");

    if (results.isEmpty()) {
      bindingResult.rejectValue("lastName", "notFound", "not found");
      return "owners/findOwners";
    } else if (results.size() == 1) {
      owner = results.get(0);
      return "redirect:/owners/" + owner.getId();
    } else {
      model.addAttribute("selections", results);
      return "owners/ownersList";
    }
  }

  @GetMapping("/new")
  public String initCreationForm(Model model) {
    model.addAttribute("owner", Owner.builder().build());
    return VIEW_OWNER_CREATE_OR_UPDATE_FORM;
  }

  @PostMapping("/new")
  public String processCreationForm(@Valid Owner owner, BindingResult result) {
    if (result.hasErrors()) {
      return VIEW_OWNER_CREATE_OR_UPDATE_FORM;
    } else {
      owner = ownerService.save(owner);
      return "redirect:/owners/" + owner.getId();
    }
  }

  @GetMapping("{ownerId}/edit")
  public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {
    Owner owner = ownerService.findById(ownerId);
    model.addAttribute("owner", owner);
    return VIEW_OWNER_CREATE_OR_UPDATE_FORM;
  }

  @PostMapping("{ownerId}/edit")
  public String processUpdateOwnerForm(
      @PathVariable Long ownerId, @Valid Owner owner, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return VIEW_OWNER_CREATE_OR_UPDATE_FORM;
    } else {
      owner.setId(ownerId);
      owner = ownerService.save(owner);
      return "redirect:/owners/" + owner.getId();
    }
  }

  @GetMapping({"/find"})
  public String findOwners(Model model) {
    model.addAttribute("owner", Owner.builder().build());
    return "owners/findOwners";
  }

  @GetMapping("/{ownerId}")
  public ModelAndView showOwner(@PathVariable Long ownerId) {
    ModelAndView mav = new ModelAndView("owners/ownerDetails");
    mav.addObject(this.ownerService.findById(ownerId));
    return mav;
  }
}
