package com.bukry.gredel.cinema.mapper;

import com.bukry.gredel.cinema.dto.PersonDto;
import com.bukry.gredel.cinema.model.Person;

import java.util.List;
import java.util.stream.Collectors;

public class PersonMapper {

    public static PersonDto mapPersonToPersonDto(Person person){
        return PersonDto.builder()
                .id(person.getId())
                .email(person.getEmail())
                .login(person.getLogin())
                .role(person.getRole().name())
                .build();
    }

    public static List<PersonDto> mapPersonListToPersonDtoList(List<Person> personList){
        return personList.stream()
                .map(person -> PersonDto.builder()
                        .id(person.getId())
                        .email((person.getEmail()))
                        .login(person.getLogin())
                        .role(person.getRole().name())
                        .build())
                .collect(Collectors.toList());
    }
}
