package com.bukry.gredel.cinema.service;

import com.bukry.gredel.cinema.dto.PersonCreationDto;
import com.bukry.gredel.cinema.dto.PersonUpdateDto;
import com.bukry.gredel.cinema.exception.EmailAlreadyExistsException;
import com.bukry.gredel.cinema.exception.LoginAlreadyExistsException;
import com.bukry.gredel.cinema.exception.NoSuchPersonExistsException;
import com.bukry.gredel.cinema.model.Person;
import com.bukry.gredel.cinema.model.Role;
import com.bukry.gredel.cinema.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
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
        person.setRole(Role.USER);

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

    @Transactional
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

        person.setLogin(personUpdateDto.getLogin());
        person.setEmail(personUpdateDto.getEmail());
        person.setPassword(personUpdateDto.getPassword());
        return personRepository.save(person);
    }

    //todo change role zamiast mozliwosci zmiany w update
    //public void changeRole()
    public void deletePerson(Long id) {
        if(personRepository.existsById(id))
            personRepository.deleteById(id);
    }
}
