package org.clinic.ms_clinicservice.repository;

import org.clinic.ms_clinicservice.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Integer> {
    boolean existsByName(String name);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END " +
            "FROM Clinic c WHERE LOWER(c.name) != LOWER(?1) AND c.id = ?2")
    boolean notExistsByNameAndExistById(String name, int id);
}
