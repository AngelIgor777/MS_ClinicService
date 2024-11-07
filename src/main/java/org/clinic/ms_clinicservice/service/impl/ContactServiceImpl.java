package org.clinic.ms_clinicservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.clinic.ms_clinicservice.entity.Contact;
import org.clinic.ms_clinicservice.repository.ContactRepository;
import org.clinic.ms_clinicservice.service.ContactService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;


    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public Contact getContactById(Integer id) {
        return contactRepository.findById(id).orElse(null);
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Contact updateContact(Integer id, Contact updatedContact) {
        if (contactRepository.existsById(id)) {
            updatedContact.setId(id);
            return contactRepository.save(updatedContact);
        }
        return null;
    }

    public void deleteContact(Integer id) {
        contactRepository.deleteById(id);
    }
}
