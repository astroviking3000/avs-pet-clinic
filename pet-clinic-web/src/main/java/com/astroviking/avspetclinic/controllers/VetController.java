package com.astroviking.avspetclinic.controllers;

import com.astroviking.avspetclinic.model.Vet;
import com.astroviking.avspetclinic.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
public class VetController {

  private final VetService vetService;

  public VetController(VetService vetService) {
    this.vetService = vetService;
  }

  @RequestMapping({"/vets", "/vets/index.html", "/vets/index", "vets.html"})
  public String listVets(Model model) {
    model.addAttribute("vets", vetService.findAll());
    return "vets/index";
  }

  @GetMapping("/api/vets")
  public @ResponseBody Set<Vet> getVetsJson() {
    return vetService.findAll();
  }
}
