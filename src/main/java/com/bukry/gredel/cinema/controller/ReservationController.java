package com.bukry.gredel.cinema.controller;

import com.bukry.gredel.cinema.dto.ReservationCreationDto;
import com.bukry.gredel.cinema.dto.ReservationDto;
import com.bukry.gredel.cinema.service.PersonService;
import com.bukry.gredel.cinema.service.ReservationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import static com.bukry.gredel.cinema.mapper.ReservationMapper.mapReservationToReservationDto;
import static com.bukry.gredel.cinema.mapper.ReservationMapper.mapReservationListToReservationListDto;


import java.util.List;

@RestController
@Tag(name = "reservations")
public class ReservationController {
    private final ReservationService reservationService;
    private final PersonService personService;

    public ReservationController(ReservationService reservationService, PersonService personService) {
        this.reservationService = reservationService;
        this.personService = personService;
    }

    @PostMapping("/reservations")
    @SecurityRequirement(name = "bearer")
    public void createReservation(@Valid @RequestBody ReservationCreationDto reservationCreationDto, @RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        Long personId = personService.getSinglePersonByToken(token);
        reservationCreationDto.setIdPerson(personId);
        reservationService.createReservation(reservationCreationDto);
    }

    @GetMapping("/reservations")
    @SecurityRequirement(name = "bearer")
    public List<ReservationDto> getReservations(){
        return mapReservationListToReservationListDto(reservationService.getReservations());
    }

    @GetMapping("/reservations/{id}")
    @SecurityRequirement(name = "bearer")
    public ReservationDto getSingleReservation(@PathVariable Long id){
        return mapReservationToReservationDto(reservationService.getSingleReservation(id));
    }

    @DeleteMapping("/reservations/{id}")
    @SecurityRequirement(name = "bearer")
    public void deleteReservation(@PathVariable Long id){
        reservationService.deleteReservation(id);
    }
}
