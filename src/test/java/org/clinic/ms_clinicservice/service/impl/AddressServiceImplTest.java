package org.clinic.ms_clinicservice.service.impl;

import org.clinic.ms_clinicservice.entity.Address;
import org.clinic.ms_clinicservice.entity.Clinic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AddressServiceImplTest {

    private Address address;
    private Clinic clinic;
    Address createdAddress;

    @Autowired
    private ClinicServiceImpl clinicService;
    @Autowired
    private AddressServiceImpl addressService;


    @BeforeEach
    public void setUp() {
        clinic = Clinic.builder()
                .name("Test Clinic")
                .id(1)
                .description("Test Description")
                .createdAt(LocalDateTime.now())
                .build();
        address = Address.builder()
                .city("Chisinau")
                .clinic(clinic)
                .street("Eminescu")
                .buildingNumber("31")
                .build();

        clinicService.create(clinic);//должно быть первым так как address ссылается на clinic
        createdAddress = addressService.create(address);
    }

    @Test
    void createAddress() {
        Integer id = createdAddress.getId();
        Clinic createdAddressClinic = createdAddress.getClinic();

        Assertions.assertEquals(id, createdAddressClinic.getId());
        Assertions.assertEquals(createdAddress.getStreet(), address.getStreet());
        Assertions.assertEquals(createdAddressClinic, clinic);
    }

    @Test
    void getAddressById() {
        Address addressById = addressService.getById(createdAddress.getId());
        Assertions.assertNotNull(addressById);
        Assertions.assertEquals(addressById, createdAddress);
    }

    @Test
    void getAllAddresses() {
        Clinic buildClinic = Clinic.builder().name("Test-name2").build();
        clinicService.create(buildClinic);
        Address buildAddress = Address.builder()
                .city("Chicago")
                .street("Hirozoy")
                .buildingNumber("13")
                .clinic(buildClinic).build();
        addressService.create(buildAddress);

        List<Address> addresses = addressService.getAll();

        Assertions.assertNotNull(addresses);
        Assertions.assertEquals(addresses.size(), 2); //1 адрес уже сохранён
    }

    @Test
    void updateAddress() {
        Clinic buildClinic = Clinic.builder().name("Test-name2").build();
        Address buildAddress = Address.builder()
                .city("Chicago")
                .street("Hirozoy")
                .buildingNumber("13")
                .clinic(buildClinic).build();

        Address update = addressService.update(createdAddress.getId(), buildAddress);

        Assertions.assertNotNull(update);
        Assertions.assertEquals(buildAddress.getCity(), update.getCity());
        Assertions.assertEquals(buildAddress.getStreet(), update.getStreet());
        Assertions.assertEquals(buildAddress.getBuildingNumber(), update.getBuildingNumber());
        Assertions.assertEquals(buildAddress.getClinic(), update.getClinic());


    }

    @Test
    void deleteAddress() {
        Integer addressId = createdAddress.getId();
        addressService.delete(addressId);
        Assertions.assertNull(addressService.getById(addressId));
    }
}