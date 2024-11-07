package org.clinic.ms_clinicservice.repository;

import org.clinic.ms_clinicservice.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Integer> {
}
