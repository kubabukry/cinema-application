package com.bukry.gredel.cinema.mapper;

import com.bukry.gredel.cinema.dto.SeanceDto;
import com.bukry.gredel.cinema.model.Seance;

import java.util.List;
import java.util.stream.Collectors;

public class SeanceMapper {

    public static SeanceDto mapSeanceToSeanceDto(Seance seance){
        return SeanceDto.builder()
                .id(seance.getId())
                .isPublicated(seance.getIsPublicated())
                .idMovie(seance.getMovie().getId())
                .idRoom(seance.getRoom().getId())
                .startDate(seance.getStartDate())
                .endDate(seance.getEndDate())
                .availableSeats(checkHowManySeatsAvailable(seance))
                .build();
    }

    public static List<SeanceDto> mapSeanceListToSeanceDtoList(List<Seance> seanceList){
        return seanceList.stream()
                .map(seance -> SeanceDto.builder()
                        .id(seance.getId())
                        .isPublicated(seance.getIsPublicated())
                        .idMovie(seance.getMovie().getId())
                        .idRoom(seance.getRoom().getId())
                        .startDate(seance.getStartDate())
                        .endDate(seance.getEndDate())
                        .availableSeats(checkHowManySeatsAvailable(seance))
                        .build())
                .collect(Collectors.toList());
    }

    private static Integer checkHowManySeatsAvailable(Seance seance){
        if(seance.getReservationList()==null)
            return 0;
        return seance.getRoom().getSeats() - seance.getReservationList().size();
    }
}
