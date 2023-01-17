package com.bukry.gredel.cinema.service;

import com.bukry.gredel.cinema.dto.AuthenticationRequest;
import com.bukry.gredel.cinema.dto.AuthenticationResponseDto;
import com.bukry.gredel.cinema.dto.PersonCreationDto;
import com.bukry.gredel.cinema.dto.PersonUpdateDto;
import com.bukry.gredel.cinema.exception.EmailAlreadyExistsException;
import com.bukry.gredel.cinema.exception.LoginAlreadyExistsException;
import com.bukry.gredel.cinema.exception.NoSuchPersonExistsException;
import com.bukry.gredel.cinema.model.Person;
import com.bukry.gredel.cinema.model.Role;
import com.bukry.gredel.cinema.repository.PersonRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Transactional
    public AuthenticationResponseDto createPerson(PersonCreationDto personCreationDto) {
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
        person.setPassword(passwordEncoder.encode(personCreationDto.getPassword()));
        person.setRole(Role.USER);
        personRepository.save(person);

        var jwtToken = jwtService.generateToken(person);
        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();
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
    public void updatePerson(Long id, PersonUpdateDto personUpdateDto) {
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
        personRepository.save(person);
    }

    //todo change role zamiast mozliwosci zmiany w update
    //public void changeRole()
    public void deletePerson(Long id) {
        if(personRepository.existsById(id))
            personRepository.deleteById(id);
    }

    //todo osobna metoda do zmiany hasła?

    public AuthenticationResponseDto authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                authenticationRequest.getLogin(), authenticationRequest.getPassword())
        );
        Person person = personRepository.findByLogin(authenticationRequest.getLogin())
                .orElseThrow(() -> new NoSuchPersonExistsException(
                        "No such user with login: "+authenticationRequest.getLogin()+" exists"));
        var jwtToken = jwtService.generateToken(person);

        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();
    }
}
