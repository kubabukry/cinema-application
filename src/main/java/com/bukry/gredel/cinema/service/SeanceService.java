package com.bukry.gredel.cinema.service;

import com.bukry.gredel.cinema.dto.SeanceCreationDto;
import com.bukry.gredel.cinema.exception.NoSuchMovieExistsException;
import com.bukry.gredel.cinema.exception.NoSuchRoomExistsException;
import com.bukry.gredel.cinema.exception.NoSuchSeanceExistsException;
import com.bukry.gredel.cinema.model.Movie;
import com.bukry.gredel.cinema.model.Room;
import com.bukry.gredel.cinema.model.Seance;
import com.bukry.gredel.cinema.repository.MovieRepository;
import com.bukry.gredel.cinema.repository.RoomRepository;
import com.bukry.gredel.cinema.repository.SeanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeanceService {
    private final SeanceRepository seanceRepository;

    private final MovieRepository movieRepository;

    private final RoomRepository roomRepository;

    public SeanceService(SeanceRepository seanceRepository, MovieRepository movieRepository, RoomRepository roomRepository) {
        this.seanceRepository = seanceRepository;
        this.movieRepository = movieRepository;
        this.roomRepository = roomRepository;
    }

    //todo jakie jeszcze wyjatki?
    public void createSeance(SeanceCreationDto seanceCreationDto) {
        Movie movie = movieRepository.findById(seanceCreationDto.getIdMovie())
                .orElseThrow(() -> new NoSuchMovieExistsException(
                        "No such movie with id: "+seanceCreationDto.getIdMovie()+" exists"));
        Room room = roomRepository.findById(seanceCreationDto.getIdRoom())
                .orElseThrow(() -> new NoSuchRoomExistsException(
                        "No such room with id: "+seanceCreationDto.getIdRoom()+" exists"));
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
}
