package com.bukry.gredel.cinema.service;

import com.bukry.gredel.cinema.dto.ReservationCreationDto;
import com.bukry.gredel.cinema.exception.*;
import com.bukry.gredel.cinema.model.Person;
import com.bukry.gredel.cinema.model.Reservation;
import com.bukry.gredel.cinema.model.Room;
import com.bukry.gredel.cinema.model.Seance;
import com.bukry.gredel.cinema.repository.PersonRepository;
import com.bukry.gredel.cinema.repository.ReservationRepository;
import com.bukry.gredel.cinema.repository.SeanceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    private final Clock clock;
    private final PersonRepository personRepository;
    private final SeanceRepository seanceRepository;

    public ReservationService(ReservationRepository reservationRepository, Clock clock, PersonRepository personRepository, SeanceRepository seanceRepository) {
        this.reservationRepository = reservationRepository;
        this.clock = clock;
        this.personRepository = personRepository;
        this.seanceRepository = seanceRepository;
    }

    @Transactional
    public void createReservation(ReservationCreationDto reservationCreationDto) {
        Person person = personRepository.findById(reservationCreationDto.getIdPerson())
                .orElseThrow(() -> new NoSuchPersonExistsException(
                        "No such person with id: "+reservationCreationDto.getIdPerson()+" exists"));
        Seance seance = seanceRepository.findById(reservationCreationDto.getIdSeance())
                .orElseThrow(() -> new NoSuchSeanceExistsException(
                        "No such seance with id: "+reservationCreationDto.getIdSeance()+" exists"));

        Boolean seatAvailable = checkSeatAvailability(seance, reservationCreationDto.getSeat());
        if(!seatAvailable)
            throw new SeatAlreadyTakenException(
                    "Seat: "+reservationCreationDto.getSeat()+" for seance: "+seance.getId()+" already taken");

        Boolean seatExists = checkSeatExists(seance.getRoom(), reservationCreationDto.getSeat());
        if(!seatExists)
            throw new SeatAlreadyTakenException("Seat number: "+reservationCreationDto.getSeat()+" does not exist");

        Boolean seanceNotFull = checkSeanceAvailability(seance);
        if(!seanceNotFull){
            throw new SeanceDateException("Seance with id: "+seance.getId()+" has no more seats available");
        }

        Boolean seanceStarted = checkSeanceStarted(seance);
        if(seanceStarted){
            throw new SeanceAlreadyStartedException("It is already past start date of seance: "+seance.getId());
        }

        Reservation reservation = new Reservation();
        reservation.setPerson(person);
        reservation.setSeance(seance);
        reservation.setSeat(reservationCreationDto.getSeat());
        reservationRepository.save(reservation);
    }

    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getSingleReservation(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new NoSuchReservationExistsException(
                        "No such reservation with id: "+id+" exists"));
    }

    public void deleteReservation(Long id) {
        if(reservationRepository.existsById(id))
            reservationRepository.deleteById(id);
    }

    private Boolean checkSeatAvailability(Seance seance, Integer seat){
        List<Reservation> reservationList = new ArrayList<>();
        reservationRepository.findAll().stream()
                .forEach(reservation -> {
                    if(reservation.getSeance().equals(seance)&&reservation.getSeat().equals(seat))
                        reservationList.add(reservation);
                });
        if(reservationList.isEmpty())
            return true;
        return false;
    }

    private Boolean checkSeanceAvailability(Seance seance){
        List<Reservation> reservationList = new ArrayList<>();
        reservationRepository.findAll().stream()
                .forEach(reservation -> {
                    if(reservation.getSeance().equals(seance))
                        reservationList.add(reservation);
                });
        if(reservationList.size()>=seance.getRoom().getSeats())
            return false;
        return true;
    }

    private Boolean checkSeanceStarted(Seance seance){
        Instant startDate = seance.getStartDate();
        if(startDate.isBefore(clock.instant()))
            return true;
        return false;
    }

    private Boolean checkSeatExists(Room room, Integer seatTaken){
        Integer seatNumber = room.getSeats();
        if(seatNumber<=0||seatNumber<seatTaken)
            return false;
        return true;
    }
}
