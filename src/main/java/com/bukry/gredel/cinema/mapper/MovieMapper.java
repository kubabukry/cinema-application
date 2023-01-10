package com.bukry.gredel.cinema.mapper;

import com.bukry.gredel.cinema.dto.MovieDto;
import com.bukry.gredel.cinema.model.Movie;

import java.util.List;
import java.util.stream.Collectors;

public class MovieMapper {

    public static List<MovieDto> mapMovieListToMovieDtoList(List<Movie> movieList){
        return movieList.stream()
                .map(movie -> MovieDto.builder()
                        .id(movie.getId())
                        .title(movie.getTitle())
                        .duration(movie.getDuration())
                        .build())
                .collect(Collectors.toList());
    }

    public static MovieDto mapMovieToMovieDto(Movie movie){
        return MovieDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .duration(movie.getDuration())
                .build();
    }
}
