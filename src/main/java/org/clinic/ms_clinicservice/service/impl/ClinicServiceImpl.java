package org.clinic.ms_clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.clinic.ms_clinicservice.dto.ClinicDTO;
import org.clinic.ms_clinicservice.entity.Clinic;
import org.clinic.ms_clinicservice.exception.EmptyResultException;
import org.clinic.ms_clinicservice.repository.ClinicRepository;
import org.clinic.ms_clinicservice.service.ClinicService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
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
        if (clinicRepository.existsByName(clinic.getName())) {
            throw new EntityExistsException("Clinic with name " + clinic.getName() + " already exists");
        }

        return clinicRepository.save(clinic);
    }

    public ClinicDTO getClinicById(Integer id) {
        Clinic clinic = clinicRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Clinic not found"));
        return mapServiceImpl.mapToClinicDTO(clinic);
    }

    public List<ClinicDTO> getAllClinics() {
        List<Clinic> all = clinicRepository.findAll();
        List<ClinicDTO> clinicDTOS = all.stream()
                .map(mapServiceImpl::mapToClinicDTO).toList();

        return clinicDTOS;
    }



    public Clinic updateClinic(Integer id, Clinic updatedClinic) {
        if (clinicRepository.notExistsByNameAndExistById(updatedClinic.getName(), id)) {
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
