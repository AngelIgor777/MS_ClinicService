package org.clinic.ms_clinicservice.controller;

import lombok.RequiredArgsConstructor;
import org.clinic.ms_clinicservice.dto.ClinicDTO;
import org.clinic.ms_clinicservice.entity.Clinic;
import org.clinic.ms_clinicservice.service.impl.ClinicServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clinics")
public class ClinicController {

    private final ClinicServiceImpl clinicServiceImpl;


    @PostMapping
    public ResponseEntity<HashMap<String, Integer>> createClinic(@RequestBody Clinic clinic) {
        Clinic createdClinic = clinicServiceImpl.createClinic(clinic);
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("id", createdClinic.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(hashMap);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicDTO> getClinicById(@PathVariable Integer id) {
        ClinicDTO clinic = clinicServiceImpl.getClinicById(id);
        return clinic != null ? ResponseEntity.ok(clinic) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<ClinicDTO>> getAllClinics() {
        List<ClinicDTO> clinics = clinicServiceImpl.getAllClinics();
        return ResponseEntity.ok(clinics);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HashMap<String, Integer>> updateClinic(@PathVariable Integer id, @RequestBody Clinic clinic) {
        Clinic updatedClinic = clinicServiceImpl.updateClinic(id, clinic);
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("id", updatedClinic.getId());
        return ResponseEntity.ok(hashMap);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClinic(@PathVariable Integer id) {
        clinicServiceImpl.deleteClinic(id);
        return ResponseEntity.noContent().build();
    }
}
