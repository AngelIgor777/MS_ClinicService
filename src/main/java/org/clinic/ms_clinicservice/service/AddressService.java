package org.clinic.ms_clinicservice.service;

import org.clinic.ms_clinicservice.entity.Address;

import java.util.List;

public interface AddressService {

    Address create(Address address);

    Address getById(Integer id);

    List<Address> getAll();

    Address update(Integer id, Address updatedAddress);

    void delete(Integer id);
}
