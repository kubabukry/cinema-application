package com.bukry.gredel.cinema.service;

import com.bukry.gredel.cinema.dto.PersonCreationDto;
import com.bukry.gredel.cinema.dto.PersonUpdateDto;
import com.bukry.gredel.cinema.exception.EmailAlreadyExistsException;
import com.bukry.gredel.cinema.exception.LoginAlreadyExistsException;
import com.bukry.gredel.cinema.exception.NoSuchPersonExistsException;
import com.bukry.gredel.cinema.exception.NoSuchRoleExistsException;
import com.bukry.gredel.cinema.model.Person;
import com.bukry.gredel.cinema.model.Role;
import com.bukry.gredel.cinema.repository.PersonRepository;
import com.bukry.gredel.cinema.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    private final RoleRepository roleRepository;

    public PersonService(PersonRepository personRepository, RoleRepository roleRepository) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
    }

    public Person createPerson(PersonCreationDto personCreationDto) {
        Boolean loginExists = personRepository.existsByLogin(personCreationDto.getLogin());
        Boolean mailExists = personRepository.existsByEmail(personCreationDto.getEmail());
        if(loginExists){
            throw new LoginAlreadyExistsException("Login: "+personCreationDto.getLogin()+" already exists");
        }
        if(mailExists){
            throw new EmailAlreadyExistsException("Email: "+personCreationDto.getEmail()+" already exists");
        }

        Person person = new Person();
        person.setLogin(personCreationDto.getLogin());
        person.setEmail(personCreationDto.getEmail());
        person.setPassword(personCreationDto.getPassword());
        person.setRole(roleRepository.findByName("user"));

        return personRepository.save(person);
    }

    public Person getSinglePerson(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NoSuchPersonExistsException(
                        "No such person with id: "+id+" exists"));
    }


    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    public Person updatePerson(Long id, PersonUpdateDto personUpdateDto) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new NoSuchPersonExistsException(
                        "No such person with id: "+id+" exists"));
        Boolean loginExists = personRepository.existsByLogin(personUpdateDto.getLogin());
        Boolean emailExists = personRepository.existsByEmail(personUpdateDto.getEmail());

        if(loginExists){
            throw new LoginAlreadyExistsException("Login: "+personUpdateDto.getLogin()+" already exists");
        }
        if(emailExists){
            throw new EmailAlreadyExistsException("Email: "+personUpdateDto.getEmail()+" already exists");
        }
        Role role = roleRepository.findById(personUpdateDto.getRoleId())
                .orElseThrow(() -> new NoSuchRoleExistsException(
                        "No such role with id: "+personUpdateDto.getRoleId()+" exists"));

        person.setLogin(personUpdateDto.getLogin());
        person.setEmail(personUpdateDto.getEmail());
        person.setPassword(personUpdateDto.getPassword());
        person.setRole(role);
        return personRepository.save(person);
    }

    public void deletePerson(Long id) {
        if(personRepository.existsById(id))
            personRepository.deleteById(id);
    }
}
