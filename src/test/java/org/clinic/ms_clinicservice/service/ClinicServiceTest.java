package org.clinic.ms_clinicservice.service;

import org.clinic.ms_clinicservice.dto.ClinicDTO;
import org.clinic.ms_clinicservice.entity.Address;
import org.clinic.ms_clinicservice.entity.Clinic;
import org.clinic.ms_clinicservice.entity.Contact;
import org.clinic.ms_clinicservice.repository.ClinicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClinicServiceTest {

    @Mock
    private ClinicRepository clinicRepository;

    @Mock
    private MapService mapService;

    @InjectMocks
    private ClinicService clinicService;

    private Clinic clinic;
    private ClinicDTO clinicDTO;


    private Clinic clinic2 = Clinic.builder()
            .id(0)
            .address(Address.builder().build())
            .name("PRO-CLINIC")
            .contact(Contact.builder().build())
            .description("Some description")
            .createdAt(LocalDateTime.now())
            .build();

    private Address address = Address.builder()
            .city("Moscow")
            .clinic(Clinic.builder().build())
            .buildingNumber("3")
            .street("Aurora")
            .build();

    @BeforeEach
    void setUp() {
        clinic = new Clinic();
        clinic.setId(1);
        clinic.setName("Test Clinic");

        clinicDTO = new ClinicDTO();
        clinicDTO.setId(1);
        clinicDTO.setName("Test Clinic DTO");
    }

    @Test
    void testCreateClinic() {

        Clinic result = clinicService.createClinic(clinic);

        assertNotNull(result);
        assertEquals(clinic.getId(), result.getId());
        verify(clinicRepository, times(1)).save(clinic);
    }

    @Test
    void testGetClinicById() {
        when(mapService.mapToClinicDTO(clinic)).thenReturn(clinicDTO);

        ClinicDTO result = clinicService.getClinicById(1);

        assertNotNull(result);
        assertEquals(clinicDTO.getId(), result.getId());
    }

    @Test
    void testGetAllClinics() {
        List<Clinic> clinics = Arrays.asList(clinic, new Clinic());
        when(mapService.mapToClinicDTO(any(Clinic.class))).thenReturn(clinicDTO);

        List<ClinicDTO> result = clinicService.getAllClinics();

        assertNotNull(result);
        assertEquals(clinics.size(), result.size());
        verify(clinicRepository, times(1)).findAll();
        verify(mapService, times(clinics.size())).mapToClinicDTO(any(Clinic.class));
    }

    @Test
    void testUpdateClinic() {

        Clinic result = clinicService.updateClinic(1, clinic2);

        assertNotNull(result);
        assertEquals(clinic.getId(), result.getId());
        verify(clinicRepository, times(1)).existsById(1);
        verify(clinicRepository, times(1)).save(clinic);
    }

    @Test
    void testDeleteClinic() {
        clinicService.deleteClinic(1);

        verify(clinicRepository, times(1)).deleteById(1);
    }
}
