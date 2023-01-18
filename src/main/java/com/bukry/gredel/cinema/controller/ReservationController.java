package com.bukry.gredel.cinema.controller;

import com.bukry.gredel.cinema.dto.ReservationCreationDto;
import com.bukry.gredel.cinema.dto.ReservationDto;
import com.bukry.gredel.cinema.service.ReservationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import static com.bukry.gredel.cinema.mapper.ReservationMapper.mapReservationToReservationDto;
import static com.bukry.gredel.cinema.mapper.ReservationMapper.mapReservationListToReservationListDto;

import java.util.List;

@RestController
@Tag(name = "reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/reservations")
    public void createReservation(@Valid @RequestBody ReservationCreationDto reservationCreationDto){
        reservationService.createReservation(reservationCreationDto);
    }

    @GetMapping("/reservations")
    public List<ReservationDto> getReservations(){
        return mapReservationListToReservationListDto(reservationService.getReservations());
    }

    @GetMapping("/reservations/{id}")
    public ReservationDto getSingleReservation(@PathVariable Long id){
        return mapReservationToReservationDto(reservationService.getSingleReservation(id));
    }

    @DeleteMapping("/reservations/{id}")
    public void deleteReservation(@PathVariable Long id){
        reservationService.deleteReservation(id);
    }
}
