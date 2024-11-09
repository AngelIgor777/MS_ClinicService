package org.clinic.ms_clinicservice.controller;

import lombok.RequiredArgsConstructor;

import org.clinic.ms_clinicservice.entity.Address;
import org.clinic.ms_clinicservice.service.impl.AddressServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressServiceImpl addressServiceImpl;


    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        Address createdAddress = addressServiceImpl.create(address);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAddress);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Integer id) {
        Address address = addressServiceImpl.getById(id);
        return address != null ? ResponseEntity.ok(address) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Address>> getAllAddresses() {
        List<Address> addresses = addressServiceImpl.getAll();
        return ResponseEntity.ok(addresses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Integer id, @RequestBody Address address) {
        Address updatedAddress = addressServiceImpl.update(id, address);
        return updatedAddress != null ? ResponseEntity.ok(updatedAddress) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Integer id) {
        addressServiceImpl.delete(id);
        return ResponseEntity.noContent().build();
    }
}
