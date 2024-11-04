package org.clinic.ms_clinicservice.service;

import lombok.RequiredArgsConstructor;
import org.clinic.ms_clinicservice.dto.ClinicDTO;
import org.clinic.ms_clinicservice.entity.Clinic;
import org.clinic.ms_clinicservice.repository.ClinicRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ClinicService {

    private final ClinicRepository clinicRepository;


    public Clinic createClinic(Clinic clinic) {
        return clinicRepository.save(clinic);
    }

    public ClinicDTO getClinicById(Integer id) {
        Clinic clinic = clinicRepository.findById(id).orElse(null);
        return mapToClinicDTO(clinic);
    }

    public List<ClinicDTO> getAllClinics() {
        List<Clinic> all = clinicRepository.findAll();
        return all.stream()
                .map(this::mapToClinicDTO).toList();
    }

//    public List<ClinicDTO> getAllClinicsDTO(List<Clinic> clinics) {
//        return clinics.stream()
//                .map(this::mapToClinicDTO).toList();
//    }


    public Clinic updateClinic(Integer id, Clinic updatedClinic) {
        if (clinicRepository.existsById(id)) {
            updatedClinic.setId(id);
            return clinicRepository.save(updatedClinic);
        }
        return null;
    }

    public void deleteClinic(Integer id) {
        clinicRepository.deleteById(id);
    }

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
