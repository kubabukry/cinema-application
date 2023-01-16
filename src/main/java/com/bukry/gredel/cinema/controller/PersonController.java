package com.bukry.gredel.cinema.controller;

import com.bukry.gredel.cinema.dto.PersonCreationDto;
import com.bukry.gredel.cinema.dto.PersonDto;
import com.bukry.gredel.cinema.dto.PersonUpdateDto;
import com.bukry.gredel.cinema.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bukry.gredel.cinema.mapper.PersonMapper.mapPersonListToPersonDtoList;
import static com.bukry.gredel.cinema.mapper.PersonMapper.mapPersonToPersonDto;

@RestController
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/persons")
    public void createPerson(@Valid @RequestBody PersonCreationDto personCreationDto){
        personService.createPerson(personCreationDto);
    }

    @GetMapping("/persons/{id}")
    public PersonDto getSinglePerson(@PathVariable Long id){
        return mapPersonToPersonDto(personService.getSinglePerson(id));
    }

    @GetMapping("/persons")
    public List<PersonDto> getPersons(){
        return mapPersonListToPersonDtoList(personService.getPersons());
    }

    @PutMapping("/persons/{id}")
    public void updatePerson(@Valid @PathVariable Long id, @RequestBody PersonUpdateDto personUpdateDto){
        personService.updatePerson(id, personUpdateDto);
    }

    @DeleteMapping("/persons/{id}")
    public void deletePerson(@PathVariable Long id){
        personService.deletePerson(id);
    }
}
