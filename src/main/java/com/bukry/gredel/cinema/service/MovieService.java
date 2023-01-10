package com.bukry.gredel.cinema.service;

import com.bukry.gredel.cinema.dto.MovieCreationDto;
import com.bukry.gredel.cinema.exception.MovieTitleAlreadyExistsException;
import com.bukry.gredel.cinema.exception.NoSuchMovieExistsException;
import com.bukry.gredel.cinema.model.Movie;
import com.bukry.gredel.cinema.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
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
}
