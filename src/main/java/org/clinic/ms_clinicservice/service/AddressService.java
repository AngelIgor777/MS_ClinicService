package org.clinic.ms_clinicservice.service;

import org.clinic.ms_clinicservice.entity.Address;

import java.util.List;

public interface AddressService {

    Address createAddress(Address address);

    Address getAddressById(Integer id);

    List<Address> getAllAddresses();

    Address updateAddress(Integer id, Address updatedAddress);

    void deleteAddress(Integer id);
}
