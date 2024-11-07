package org.clinic.ms_clinicservice.service;

import lombok.RequiredArgsConstructor;
import org.clinic.ms_clinicservice.dto.ClinicDTO;
import org.clinic.ms_clinicservice.entity.Clinic;
import org.clinic.ms_clinicservice.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class ClinicService {

    private final ClinicRepository clinicRepository;
    private final MapService mapService;


    public Clinic createClinic(Clinic clinic) {
        return clinicRepository.save(clinic);
    }

    public ClinicDTO getClinicById(Integer id) {
        Clinic clinic = clinicRepository.findById(id).orElse(null);
        return mapService.mapToClinicDTO(clinic);
    }

    public List<ClinicDTO> getAllClinics() {
        List<Clinic> all = clinicRepository.findAll();
        return all.stream()
                .map(mapService::mapToClinicDTO).toList();
    }


    public Clinic updateClinic(Integer id, Clinic updatedClinic) {
        if (clinicRepository.existsById(id)) {
            updatedClinic.setId(id);
            return clinicRepository.save(updatedClinic);
        }
        return null;
    }

    public int deleteClinic(Integer id) {
        clinicRepository.deleteById(id);
        return id;
    }


}
