package org.clinic.ms_clinicservice.service.impl;

import org.clinic.ms_clinicservice.dto.ClinicDTO;
import org.clinic.ms_clinicservice.entity.Clinic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import javax.transaction.Transactional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ClinicServiceImplTest {


    @Autowired
    private ClinicServiceImpl clinicServiceImpl;

    private Clinic clinic;

    @BeforeEach
    void setUp() {
        clinic = new Clinic();
        clinic.setName("Test Clinic");
    }

    @Test
    void testCreate() {
        Clinic savedClinic = clinicServiceImpl.create(clinic);

        assertNotNull(savedClinic.getId());
        assertEquals("Test Clinic", savedClinic.getName());
    }

    @Test
    void testGetClinicById() {
        Clinic savedClinic = clinicServiceImpl.create(clinic);
        ClinicDTO clinicDTO = clinicServiceImpl.getClinicById(savedClinic.getId());

        assertNotNull(clinicDTO);
        assertEquals(savedClinic.getId(), clinicDTO.getId());
        assertEquals("Test Clinic", clinicDTO.getName());
    }

    @Test
    void testGetAllClinics() {
        Clinic clinic2 = new Clinic();
        clinic2.setName("Another Clinic");
        clinicServiceImpl.create(clinic);
        clinicServiceImpl.create(clinic2);

        List<ClinicDTO> allClinics = clinicServiceImpl.getAllClinics();

        assertEquals(2, allClinics.size());
    }

    @Test
    void testUpdateClinic() {
        Clinic savedClinic = clinicServiceImpl.create(clinic);


        Clinic update = Clinic.builder().name("update-name").build();

        Clinic updatedClinic = clinicServiceImpl.updateClinic(savedClinic.getId(), update);

        assertNotNull(updatedClinic);
        assertEquals("update-name", updatedClinic.getName());
    }

    @Test
    void testDeleteClinic() {
        Clinic savedClinic = clinicServiceImpl.create(clinic);

        int i = clinicServiceImpl.deleteClinic(savedClinic.getId());

        Assertions.assertEquals(savedClinic.getId(), i);
    }


}