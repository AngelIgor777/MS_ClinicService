package org.clinic.ms_clinicservice.controller;

import lombok.RequiredArgsConstructor;
import org.clinic.ms_clinicservice.dto.ClinicDTO;
import org.clinic.ms_clinicservice.entity.Clinic;
import org.clinic.ms_clinicservice.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clinics")
public class ClinicController {

    private final ClinicService clinicService;


    @PostMapping
    public ResponseEntity<Clinic> createClinic(@RequestBody Clinic clinic) {
        Clinic createdClinic = clinicService.createClinic(clinic);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClinic);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicDTO> getClinicById(@PathVariable Integer id) {
        ClinicDTO clinic = clinicService.getClinicById(id);
        return clinic != null ? ResponseEntity.ok(clinic) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<ClinicDTO>> getAllClinics() {
        List<ClinicDTO> clinics = clinicService.getAllClinics();
        return ResponseEntity.ok(clinics);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clinic> updateClinic(@PathVariable Integer id, @RequestBody Clinic clinic) {
        Clinic updatedClinic = clinicService.updateClinic(id, clinic);
        return updatedClinic != null ? ResponseEntity.ok(updatedClinic) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClinic(@PathVariable Integer id) {
        clinicService.deleteClinic(id);
        return ResponseEntity.noContent().build();
    }
}
