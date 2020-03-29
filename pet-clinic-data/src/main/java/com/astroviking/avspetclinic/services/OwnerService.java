package com.astroviking.avspetclinic.services;

import com.astroviking.avspetclinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {

  Owner findByLastName(String lastName);

  List<Owner> findAllByLastNameLike(String lastName);
}
