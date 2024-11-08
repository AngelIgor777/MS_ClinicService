package org.clinic.ms_clinicservice.service;

import org.clinic.ms_clinicservice.dto.ClinicDTO;
import org.clinic.ms_clinicservice.entity.Clinic;
import org.clinic.ms_clinicservice.request.ext.ClinicBasicRequest;
import org.clinic.ms_clinicservice.response.ClinicResponse;

public interface MapService {
    ClinicDTO mapToClinicDTO(Clinic clinic);

    Clinic mapToClinic(ClinicBasicRequest clinicBasicRequest);

    ClinicResponse mapToClinicResponse(ClinicBasicRequest clinicBasicRequest);
}
