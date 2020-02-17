package com.astroviking.avspetclinic.services;

import com.astroviking.avspetclinic.model.Vet;

import java.util.Set;

public interface VetService {

  Vet findById(Long id);

  Vet save(Vet vet);

  Set<Vet> findAll();
}
