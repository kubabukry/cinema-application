package com.bukry.gredel.cinema.service;

import com.bukry.gredel.cinema.dto.MovieCreationDto;
import com.bukry.gredel.cinema.dto.MovieDto;
import com.bukry.gredel.cinema.exception.MovieTitleAlreadyExistsException;
import com.bukry.gredel.cinema.exception.NoSuchMovieExistsException;
import com.bukry.gredel.cinema.exception.SeatAlreadyTakenException;
import com.bukry.gredel.cinema.model.Movie;
import com.bukry.gredel.cinema.model.Seance;
import com.bukry.gredel.cinema.repository.MovieRepository;
import com.bukry.gredel.cinema.repository.SeanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    private final SeanceRepository seanceRepository;

    public MovieService(MovieRepository movieRepository, SeanceRepository seanceRepository) {
        this.movieRepository = movieRepository;
        this.seanceRepository = seanceRepository;
    }

    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    public void createMovie(MovieCreationDto movieCreationDto) {
        Boolean titleExists = movieRepository.existsByTitle(movieCreationDto.getTitle());
        if(titleExists)
            throw new MovieTitleAlreadyExistsException(
                    "Movie with title: "+movieCreationDto.getTitle()+" already exists");
        Movie movie = new Movie();
        movie.setTitle(movieCreationDto.getTitle());
        movie.setDuration(movieCreationDto.getDuration());
        movieRepository.save(movie);
    }

    public Movie getSingleMovie(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new NoSuchMovieExistsException(
                        "No such movie with id: "+id+" exists"));
    }

    public void deleteMovie(Long id) {
        if(movieRepository.existsById(id))
            movieRepository.deleteById(id);
    }

    public void updateMovie(MovieDto movieDto) {
        List<Seance> seanceList = seanceRepository.findAll();
        Boolean movieNotBooked = seanceList.stream()
                .filter(seance -> seance.getMovie().getId().equals(movieDto.getId())
                        &&seance.getIsPublicated()==true)
                .collect(Collectors.toList()).isEmpty();
        if(!movieNotBooked)
            throw new SeatAlreadyTakenException(
                    "Movie with id: "+movieDto.getId()+" is already being displayed");
        Movie movie = getSingleMovie(movieDto.getId());
        movie.setTitle(movieDto.getTitle());
        movie.setDuration(movie.getDuration());
        movieRepository.save(movie);
    }
}
