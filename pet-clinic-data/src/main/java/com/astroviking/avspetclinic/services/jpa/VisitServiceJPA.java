package com.astroviking.avspetclinic.services.jpa;

import com.astroviking.avspetclinic.model.Visit;
import com.astroviking.avspetclinic.repositories.VisitRepository;
import com.astroviking.avspetclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("jpa")
public class VisitServiceJPA implements VisitService {

  private final VisitRepository visitRepository;

  public VisitServiceJPA(VisitRepository visitRepository) {
    this.visitRepository = visitRepository;
  }

  @Override
  public Visit findById(Long id) {
    return visitRepository.findById(id).orElse(null);
  }

  @Override
  public Visit save(Visit object) {
    return visitRepository.save(object);
  }

  @Override
  public Set<Visit> findAll() {
    Set<Visit> visits = new HashSet<>();
    visitRepository.findAll().forEach(visits::add);
    return visits;
  }

  @Override
  public void delete(Visit object) {
    visitRepository.delete(object);
  }

  @Override
  public void deleteById(Long aLong) {
    visitRepository.deleteById(aLong);
  }
}
