package org.clinic.ms_clinicservice.service;

import org.clinic.ms_clinicservice.entity.Contact;

import java.util.List;

public interface ContactService {
    Contact createContact(Contact contact);

    Contact getContactById(Integer id);

    List<Contact> getAllContacts();

    Contact updateContact(Integer id, Contact updatedContact);
}
