package com.bukry.gredel.cinema.controller;

import com.bukry.gredel.cinema.dto.MovieCreationDto;
import com.bukry.gredel.cinema.dto.MovieDto;
import com.bukry.gredel.cinema.service.MovieService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bukry.gredel.cinema.mapper.MovieMapper.mapMovieListToMovieDtoList;
import static com.bukry.gredel.cinema.mapper.MovieMapper.mapMovieToMovieDto;

@RestController
@Tag(name = "movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies/all")
    public List<MovieDto> getMovies(){
        return mapMovieListToMovieDtoList(movieService.getMovies());
    }

    @GetMapping("/movies/single/{id}")
    public MovieDto getSingleMovie(@PathVariable Long id){
        return mapMovieToMovieDto(movieService.getSingleMovie(id));
    }

    @PostMapping("/movies/create")
    @SecurityRequirement(name = "bearer")
    public void createMovie(@Valid @RequestBody MovieCreationDto movieCreationDto){
        movieService.createMovie(movieCreationDto);
    }

    @PutMapping("/movies/update")
    @SecurityRequirement(name = "bearer")
    public void updateMovie(@Valid @RequestBody MovieDto movieDto){
        movieService.updateMovie(movieDto);
    }

    @DeleteMapping("/movies/delete/{id}")
    @SecurityRequirement(name = "bearer")
    public void deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
    }
}
