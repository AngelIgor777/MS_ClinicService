package org.clinic.ms_clinicservice.repository;

import org.clinic.ms_clinicservice.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Integer> {
    boolean existsByName(String name);

    @Query("select case when count(c) > 0 then true else false end from Clinic c where c.name != ?1 and c.id = ?2")
    boolean notExistsByNameAndExistById(String name,int id);
}
