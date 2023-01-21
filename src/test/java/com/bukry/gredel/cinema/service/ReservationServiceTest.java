package com.bukry.gredel.cinema.service;

import com.bukry.gredel.cinema.dto.ReservationCreationDto;
import com.bukry.gredel.cinema.exception.SeanceAlreadyStartedException;
import com.bukry.gredel.cinema.model.Person;
import com.bukry.gredel.cinema.model.Reservation;
import com.bukry.gredel.cinema.model.Room;
import com.bukry.gredel.cinema.model.Seance;
import com.bukry.gredel.cinema.repository.PersonRepository;
import com.bukry.gredel.cinema.repository.ReservationRepository;
import com.bukry.gredel.cinema.repository.SeanceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @InjectMocks
    private ReservationService reservationService;
    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private SeanceRepository seanceRepository;
    @Mock
    private PersonRepository personRepository;
    @Mock
    private Clock clock;
    List<Reservation> reservationList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        this.reservationService = new ReservationService(
                this.reservationRepository,
                clock, this.personRepository,
                this.seanceRepository);
        reservationList = List.of(
                new Reservation(),
                new Reservation(),
                new Reservation());
    }

    @Test
    void createReservationWhenStartDateAfterNow() {
        //startDate Mon Jan 20 2025 21:24:51 GMT+0000
        //endDate Mon Jan 20 2025 21:24:52 GMT+0000
        Person person = new Person();
        person.setId(1L);
        Room room = new Room(null, null, 10, null);
        Seance seance = new Seance(1L, Instant.ofEpochSecond(1737408291), Instant.ofEpochSecond(1737408292), null, null, room, null);
        ReservationCreationDto reservationCreationDto = new ReservationCreationDto(1, 1L, 1L);

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(seanceRepository.findById(1L)).thenReturn(Optional.of(seance));
        when(clock.instant()).thenReturn(Instant.ofEpochSecond(1737408291));
        reservationService.createReservation(reservationCreationDto);
        verify(personRepository).findById(1L);
        verify(seanceRepository).findById(1L);
        verify(reservationRepository).save(any(Reservation.class));
    }

    @Test
    void shouldThrowExceptionWhenCreateReservationWithStartDateBeforeNow(){
        //startDate Thu Jan 20 2022 21:55:40 GMT+0000
        //endDate Thu Jan 20 2022 21:55:41 GMT+0000
        Person person = new Person();
        person.setId(1L);
        Room room = new Room(null, null, 10, null);
        Seance seance = new Seance(1L, Instant.ofEpochSecond(0), Instant.ofEpochSecond(0), null, null, room, null);
        ReservationCreationDto reservationCreationDto = new ReservationCreationDto(1, 1L, 1L);

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(seanceRepository.findById(1L)).thenReturn(Optional.of(seance));
        when(clock.instant()).thenReturn(Instant.ofEpochSecond(1737408291));
        assertThrows(SeanceAlreadyStartedException.class, () -> reservationService.createReservation(reservationCreationDto));
    }
    @Test
    void getReservations() {
        when(reservationRepository.findAll()).thenReturn(reservationList);
        List<Reservation> result = reservationService.getReservations();
        assertEquals(result, reservationList);
    }

    @Test
    void getSingleReservation() {
    }

    @Test
    void deleteReservation() {
    }
}