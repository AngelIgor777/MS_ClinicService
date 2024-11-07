package org.clinic.ms_clinicservice.repository;

import org.clinic.ms_clinicservice.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
}
