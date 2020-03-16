package com.astroviking.avspetclinic.services.jpa;

import com.astroviking.avspetclinic.model.Owner;
import com.astroviking.avspetclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerServiceJPATest {

  @Mock OwnerRepository ownerRepository;

  @InjectMocks OwnerServiceJPA ownerService;

  Owner returnOwner;

  @BeforeEach
  void setUp() {
    returnOwner = Owner.builder().id(1L).lastName("Smith").build();
  }

  @Test
  void findByLastName() {
    when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);

    Owner owner = ownerService.findByLastName("Smith");

    assertEquals(returnOwner.getId(), owner.getId());
    assertEquals(returnOwner.getLastName(), owner.getLastName());
  }

  @Test
  void findById() {
    when(ownerRepository.findById(any())).thenReturn(Optional.of(returnOwner));

    Owner owner = ownerService.findById(1L);

    assertEquals(returnOwner.getId(), owner.getId());
  }

  @Test
  void findByIdNotFound() {
    Owner owner = ownerService.findById(1L);

    assertNull(owner);
  }

  @Test
  void save() {
    Owner ownerToSave = Owner.builder().id(1L).build();

    when(ownerRepository.save(any())).thenReturn(ownerToSave);

    Owner owner = ownerRepository.save(ownerToSave);

    assertNotNull(owner);
    verify(ownerRepository).save(any());
  }

  @Test
  void findAll() {
    Set<Owner> returnOwnerSet = new HashSet<>();
    returnOwnerSet.add(Owner.builder().id(1L).build());
    returnOwnerSet.add(Owner.builder().id(2L).build());

    when(ownerRepository.findAll()).thenReturn(returnOwnerSet);

    Set<Owner> owners = ownerService.findAll();

    assertEquals(2, owners.size());
  }

  @Test
  void delete() {
    ownerService.delete(returnOwner);

    verify(ownerRepository).delete(any());
  }

  @Test
  void deleteById() {
    ownerService.deleteById(1L);

    verify(ownerRepository).deleteById(1L);
  }
}
