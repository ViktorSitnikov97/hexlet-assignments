package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import exercise.model.Contact;
import exercise.repository.ContactRepository;
import exercise.dto.ContactDTO;
import exercise.dto.ContactCreateDTO;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactRepository contactRepository;

    // BEGIN
    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO create(@RequestBody ContactCreateDTO contactCreateDTO) {
        var contact = new Contact();

        contact.setFirstName(contactCreateDTO.getFirstName());
        contact.setLastName(contactCreateDTO.getLastName());
        contact.setPhone(contactCreateDTO.getPhone());

        var currentContact = contactRepository.save(contact);
        ContactDTO dto = new ContactDTO();

        dto.setId(currentContact.getId());
        dto.setFirstName(currentContact.getFirstName());
        dto.setLastName(currentContact.getLastName());
        dto.setPhone(currentContact.getPhone());
        dto.setCreatedAt(currentContact.getCreatedAt());
        dto.setUpdatedAt(currentContact.getUpdatedAt());

        return dto;
    }
    // END
}
