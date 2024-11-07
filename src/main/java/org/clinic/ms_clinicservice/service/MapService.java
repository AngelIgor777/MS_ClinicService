package org.clinic.ms_clinicservice.service;

import org.clinic.ms_clinicservice.dto.ClinicDTO;
import org.clinic.ms_clinicservice.entity.Clinic;
import org.springframework.stereotype.Service;
@Service
public class MapService {

    public ClinicDTO mapToClinicDTO(Clinic clinic) {
        int addressId = 0;
        int contactId = 0;
        if (clinic.getAddress() != null) {
            addressId = clinic.getAddress().getId();
        }
        if (clinic.getContact() != null) {
            contactId = clinic.getContact().getId();
        }


        return ClinicDTO.builder()
                .id(clinic.getId())
                .name(clinic.getName())
                .addressId(addressId)
                .createdAt(clinic.getCreatedAt().toString())
                .contactId(contactId)
                .description(clinic.getDescription())
                .build();
    }
}
