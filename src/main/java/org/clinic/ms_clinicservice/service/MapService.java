package org.clinic.ms_clinicservice.service;

import org.clinic.ms_clinicservice.dto.ClinicDTO;
import org.clinic.ms_clinicservice.entity.Clinic;

public interface MapService {
    ClinicDTO mapToClinicDTO(Clinic clinic);
}
