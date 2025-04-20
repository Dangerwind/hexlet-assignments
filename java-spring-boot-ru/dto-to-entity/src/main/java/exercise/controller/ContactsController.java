package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import java.time.LocalDate;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactRepository contactRepository;

    // BEGIN
    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO createContact(@RequestBody ContactCreateDTO contactCreateDTO) {

        var cont = toEntity(contactCreateDTO);


        contactRepository.save(cont);

        var myDTO = toDTO(cont);
        return myDTO;


    }


    private ContactDTO toDTO(Contact contact) {
        ContactDTO dto = new ContactDTO();

        dto.setPhone(contact.getPhone());
        dto.setFirstName(contact.getFirstName());
        dto.setLastName(contact.getLastName());
        dto.setCreatedAt(contact.getCreatedAt());
        dto.setUpdatedAt(contact.getUpdatedAt());
        dto.setId(contact.getId());

        return dto;
    }

    private Contact toEntity(ContactCreateDTO dto) {
        Contact contact = new Contact();

        contact.setPhone(dto.getPhone());
        contact.setFirstName(dto.getFirstName());
        contact.setLastName(dto.getLastName());

        return contact;
    }
    
    // END
}
