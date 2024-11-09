package org.clinic.ms_clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.clinic.ms_clinicservice.entity.Address;
import org.clinic.ms_clinicservice.repository.AddressRepository;
import org.clinic.ms_clinicservice.service.AddressService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public Address create(Address address) {
        return addressRepository.save(address);
    }

    public Address getById(Integer id) {
        return addressRepository.findById(id).orElse(null);
    }

    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    public Address update(Integer id, Address updatedAddress) {
        if (addressRepository.existsById(id)) {
            updatedAddress.setId(id);
            return addressRepository.save(updatedAddress);
        }
        return null;
    }

    public void delete(Integer id) {
        addressRepository.deleteById(id);
    }
}
