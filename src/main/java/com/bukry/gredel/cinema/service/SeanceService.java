package com.bukry.gredel.cinema.service;

import com.bukry.gredel.cinema.dto.SeanceCreationDto;
import com.bukry.gredel.cinema.dto.SeanceDto;
import com.bukry.gredel.cinema.exception.*;
import com.bukry.gredel.cinema.model.Movie;
import com.bukry.gredel.cinema.model.Reservation;
import com.bukry.gredel.cinema.model.Room;
import com.bukry.gredel.cinema.model.Seance;
import com.bukry.gredel.cinema.repository.MovieRepository;
import com.bukry.gredel.cinema.repository.RoomRepository;
import com.bukry.gredel.cinema.repository.SeanceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeanceService {
    private final SeanceRepository seanceRepository;

    private final MovieRepository movieRepository;

    private final RoomRepository roomRepository;

    private final ReservationService reservationService;

    private final MovieService movieService;

    private final RoomService roomService;

    public SeanceService(SeanceRepository seanceRepository, MovieRepository movieRepository, RoomRepository roomRepository, ReservationService reservationService, MovieService movieService, RoomService roomService) {
        this.seanceRepository = seanceRepository;
        this.movieRepository = movieRepository;
        this.roomRepository = roomRepository;
        this.reservationService = reservationService;
        this.movieService = movieService;
        this.roomService = roomService;
    }

    //todo jakie jeszcze wyjatki?
    @Transactional
    public void createSeance(SeanceCreationDto seanceCreationDto) {
        Movie movie = movieService.getSingleMovie(seanceCreationDto.getIdMovie());
        Room room = roomService.getSingleRoom(seanceCreationDto.getIdRoom());

        Boolean pastStartDate = seanceCreationDto.getStartDate().isBefore(Instant.now());
        if(pastStartDate)
            throw new SeanceDateException("It is already past start date");
        Boolean pastEndDate = seanceCreationDto.getEndDate().isBefore(seanceCreationDto.getStartDate());
        if(pastEndDate)
            throw new SeanceDateException("End date can not be before start date");

        List<Seance> seanceList = room.getSeanceList();
        Boolean roomAvailable = checkIfRoomAvailable(room.getSeanceList().stream().toList(), seanceCreationDto.getStartDate());
        if(!roomAvailable)
            throw new SeanceDateException(
                    "Room with id: "+room.getId()+" has already seance planned at this time");

        Seance seance = new Seance();
        seance.setStartDate(seanceCreationDto.getStartDate());
        seance.setEndDate(seanceCreationDto.getEndDate());
        seance.setIsPublicated(seanceCreationDto.getIsPublicated());
        seance.setMovie(movie);
        seance.setRoom(room);
        seanceRepository.save(seance);
    }

    public List<Seance> getSeances() {
        return seanceRepository.findAll();
    }

    public Seance getSingleSeance(Long id) {
        return seanceRepository.findById(id)
                .orElseThrow(() -> new NoSuchSeanceExistsException(
                        "No such seance with id: "+id+" exists"));
    }

    public void deleteSeance(Long id) {
        if(seanceRepository.existsById(id))
            seanceRepository.deleteById(id);
    }

    @Transactional
    public void updateSeance(SeanceDto seanceDto) {
        List<Reservation> reservationList = reservationService.getReservations();
        Boolean seanceNotBooked = reservationList.stream()
                .filter(reservation -> reservation.getSeance().getId().equals(seanceDto.getId()))
                .collect(Collectors.toList()).isEmpty();
        if(!seanceNotBooked)
            throw new SeatAlreadyTakenException("Seance with id: "+seanceDto.getId()+" is already booked");

        Boolean pastStartDate = seanceDto.getStartDate().isBefore(Instant.now());
        if(pastStartDate)
            throw new SeanceDateException("It is already past start date");
        Boolean pastEndDate = seanceDto.getEndDate().isBefore(seanceDto.getStartDate());
        if(pastEndDate)
            throw new SeanceDateException("End date can not be before start date");

        Movie movie = movieService.getSingleMovie(seanceDto.getIdMovie());
        Room room = roomService.getSingleRoom(seanceDto.getIdRoom());

        Boolean roomAvailable = checkIfRoomAvailable(room.getSeanceList(), seanceDto.getStartDate());
        if(!roomAvailable)
            throw new SeanceDateException(
                    "Room with id: "+room.getId()+" has already seance planned at this time");

        Seance seance = getSingleSeance(seanceDto.getId());
        seance.setIsPublicated(seanceDto.getIsPublicated());
        seance.setMovie(movie);
        seance.setRoom(room);
        seance.setStartDate(seanceDto.getStartDate());
        seance.setEndDate(seanceDto.getEndDate());
        seanceRepository.save(seance);
    }
    //todo z jakiegos powodu nie dziala
    private Boolean checkIfRoomAvailable(List<Seance> seanceList, Instant startCheck){
        return seanceList.stream()
                .filter(seance -> startCheck.isBefore(seance.getEndDate())
                        &&startCheck.isAfter(seance.getStartDate()))
                .collect(Collectors.toList()).isEmpty();
    }
}
