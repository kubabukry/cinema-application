package com.bukry.gredel.cinema.mapper;

import com.bukry.gredel.cinema.dto.ReservationDto;
import com.bukry.gredel.cinema.model.Reservation;

import java.util.List;
import java.util.stream.Collectors;

public class ReservationMapper {

    public static List<ReservationDto> mapReservationListToReservationListDto(List<Reservation> reservationList){
        return reservationList.stream()
                .map(reservation -> ReservationDto.builder()
                        .id(reservation.getId())
                        .seat(reservation.getSeat())
                        .idPerson(reservation.getPerson().getId())
                        .idSeance(reservation.getSeance().getId())
                        .build())
                .collect(Collectors.toList());
    }

    public static ReservationDto mapReservationToReservationDto(Reservation reservation){
        return ReservationDto.builder()
                .id(reservation.getId())
                .seat(reservation.getSeat())
                .idPerson(reservation.getPerson().getId())
                .idSeance(reservation.getSeance().getId())
                .build();
    }
}
