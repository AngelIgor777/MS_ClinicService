package org.clinic.ms_clinicservice.service;

import lombok.RequiredArgsConstructor;
import org.clinic.ms_clinicservice.entity.Address;
import org.clinic.ms_clinicservice.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    public Address getAddressById(Integer id) {
        return addressRepository.findById(id).orElse(null);
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address updateAddress(Integer id, Address updatedAddress) {
        if (addressRepository.existsById(id)) {
            updatedAddress.setId(id);
            return addressRepository.save(updatedAddress);
        }
        return null;
    }

    public void deleteAddress(Integer id) {
        addressRepository.deleteById(id);
    }
}
