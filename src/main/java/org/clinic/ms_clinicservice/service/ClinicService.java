package org.clinic.ms_clinicservice.service;

import org.clinic.ms_clinicservice.dto.ClinicDTO;
import org.clinic.ms_clinicservice.entity.Clinic;

import java.util.List;

public interface ClinicService {
     Clinic createClinic(Clinic clinic);

    ClinicDTO getClinicById(Integer id);

    List<ClinicDTO> getAllClinics();

    Clinic updateClinic(Integer id, Clinic updatedClinic);

    int deleteClinic(Integer id);
}
