package org.clinic.ms_clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.clinic.ms_clinicservice.dto.ClinicDTO;
import org.clinic.ms_clinicservice.entity.Clinic;
import org.clinic.ms_clinicservice.repository.ClinicRepository;
import org.clinic.ms_clinicservice.service.ClinicService;
import org.clinic.ms_clinicservice.service.MapServiceImpl;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class ClinicServiceImpl implements ClinicService {

    private final ClinicRepository clinicRepository;
    private final MapServiceImpl mapServiceImpl;


    public Clinic createClinic(Clinic clinic) {
        return clinicRepository.save(clinic);
    }

    public ClinicDTO getClinicById(Integer id) {
        Clinic clinic = clinicRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Clinic not found"));
        return mapServiceImpl.mapToClinicDTO(clinic);
    }

    public List<ClinicDTO> getAllClinics() {
        List<Clinic> all = clinicRepository.findAll();
        return all.stream()
                .map(mapServiceImpl::mapToClinicDTO).toList();
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
