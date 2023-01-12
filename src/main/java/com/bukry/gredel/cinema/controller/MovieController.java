package com.bukry.gredel.cinema.controller;

import com.bukry.gredel.cinema.dto.MovieCreationDto;
import com.bukry.gredel.cinema.dto.MovieDto;
import com.bukry.gredel.cinema.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bukry.gredel.cinema.mapper.MovieMapper.mapMovieListToMovieDtoList;
import static com.bukry.gredel.cinema.mapper.MovieMapper.mapMovieToMovieDto;

@RestController
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public List<MovieDto> getMovies(){
        return mapMovieListToMovieDtoList(movieService.getMovies());
    }

    @GetMapping("/movies/{id}")
    public MovieDto getSingleMovie(@PathVariable Long id){
        return mapMovieToMovieDto(movieService.getSingleMovie(id));
    }

    @PostMapping("/movies")
    public void createMovie(@Valid @RequestBody MovieCreationDto movieCreationDto){
        movieService.createMovie(movieCreationDto);
    }

    @PutMapping("/movies")
    public void updateMovie(@Valid @RequestBody MovieDto movieDto){
        movieService.updateMovie(movieDto);
    }

    @DeleteMapping("/movies/{id}")
    public void deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
    }
}
