package org.clinic.ms_clinicservice.service.impl;

import org.clinic.ms_clinicservice.dto.ClinicDTO;
import org.clinic.ms_clinicservice.entity.Clinic;
import org.clinic.ms_clinicservice.request.ext.ClinicBasicRequest;
import org.clinic.ms_clinicservice.request.ext.ClinicUpdateRequest;
import org.clinic.ms_clinicservice.response.ClinicResponse;
import org.clinic.ms_clinicservice.response.ext.ClinicBasicResponse;
import org.clinic.ms_clinicservice.service.MapService;
import org.springframework.stereotype.Service;

@Service
public class MapServiceImpl implements MapService {

    public ClinicDTO mapToClinicDTO(Clinic clinic) {
        if (clinic == null) {
            throw new IllegalArgumentException("CLINIC CANNOT BE NULL");
        }
        int addressId = 0;
        int contactId = 0;
        if (clinic.getAddress() != null) {
            addressId = clinic.getAddress().getId();
        }
        if (clinic.getContact() != null) {
            contactId = clinic.getContact().getId();
        }


        return new ClinicDTO(clinic.getId(),
                clinic.getName(),
                clinic.getDescription(),
                clinic.getCreatedAt().toString(),
                addressId,
                contactId);
    }

//    public ClinicDTO mapToClinicDTO(ClinicBasicRequest clinic) {
//        if (clinic == null) {
//            throw new IllegalArgumentException("CLINIC CANNOT BE NULL");
//        }
//       new ClinicDTO(clinic.g)
//    }

    public Clinic mapToClinic(ClinicBasicRequest clinicBasicRequest) {
        if (clinicBasicRequest == null) {
            throw new IllegalArgumentException("CLINIC-DTO CANNOT BE NULL");
        }
        Clinic build = Clinic.builder()
                .name(clinicBasicRequest.getName())
                .description(clinicBasicRequest.getDescription())
                .build();
        return build;
    }
    public Clinic mapToClinic(ClinicUpdateRequest clinicBasicRequest) {
        if (clinicBasicRequest == null) {
            throw new IllegalArgumentException("CLINIC-DTO CANNOT BE NULL");
        }
        Clinic build = Clinic.builder()
                .name(clinicBasicRequest.getName())
                .description(clinicBasicRequest.getDescription())
                .build();
        return build;
    }



    public ClinicResponse mapToClinicResponse(ClinicBasicRequest clinicBasicRequest) {
        if (clinicBasicRequest == null) {
            throw new IllegalArgumentException("CLINIC CANNOT BE NULL");
        }

        ClinicBasicResponse clinicBasicResponse = new ClinicBasicResponse(clinicBasicRequest.getId(),
                clinicBasicRequest.getName(),
                clinicBasicRequest.getDescription());
        return clinicBasicResponse;

    }
}
