package com.bukry.gredel.cinema.controller;

import com.bukry.gredel.cinema.dto.*;
import com.bukry.gredel.cinema.service.PersonService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bukry.gredel.cinema.mapper.PersonMapper.mapPersonListToPersonDtoList;
import static com.bukry.gredel.cinema.mapper.PersonMapper.mapPersonToPersonDto;

@RestController
@Tag(name = "users")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/persons/auth/register")
    public ResponseEntity<AuthenticationResponseDto> register(
            @Valid @RequestBody PersonCreationDto personCreationDto){
        return ResponseEntity.ok(personService.createPerson(personCreationDto));
    }

    @PostMapping("/persons/auth/authenticate")
    public ResponseEntity<AuthenticationResponseDto> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(personService.authenticate(authenticationRequest));
    }

    @GetMapping("/persons/single/{id}")
    public PersonDto getSinglePerson(@PathVariable Long id){
        return mapPersonToPersonDto(personService.getSinglePerson(id));
    }

    @GetMapping("/persons/all")
    public List<PersonDto> getPersons(){
        return mapPersonListToPersonDtoList(personService.getPersons());
    }

    @PutMapping("/persons/update/{id}")
    public void updatePerson(@Valid @PathVariable Long id, @RequestBody PersonUpdateDto personUpdateDto){
        personService.updatePerson(id, personUpdateDto);
    }

    @PutMapping("/persons/change-password")
    public void changePassword(@Valid @RequestBody ChangePasswordDto changePasswordDto){
        personService.changePassword(changePasswordDto);
    }

    @DeleteMapping("/persons/delete/{id}")
    public void deletePerson(@PathVariable Long id){
        personService.deletePerson(id);
    }
}
