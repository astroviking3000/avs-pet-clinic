package com.astroviking.avspetclinic.services;

import com.astroviking.avspetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

  Owner findByLastName(String lastName);
}
