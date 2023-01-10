package com.bukry.gredel.cinema.controller;

import com.bukry.gredel.cinema.dto.SeanceCreationDto;
import com.bukry.gredel.cinema.dto.SeanceDto;
import com.bukry.gredel.cinema.service.SeanceService;
import org.springframework.web.bind.annotation.*;

import static com.bukry.gredel.cinema.mapper.SeanceMapper.mapSeanceListToSeanceDtoList;
import static com.bukry.gredel.cinema.mapper.SeanceMapper.mapSeanceToSeanceDto;

import java.util.List;

@RestController
public class SeanceController {
    private final SeanceService seanceService;

    public SeanceController(SeanceService seanceService) {
        this.seanceService = seanceService;
    }

    @PostMapping("/seances")
    public void createSeance(@RequestBody SeanceCreationDto seanceCreationDto){
        seanceService.createSeance(seanceCreationDto);
    }

    @GetMapping("/seances")
    public List<SeanceDto> getSeances(){
        return mapSeanceListToSeanceDtoList(seanceService.getSeances());
    }

    @GetMapping("/seances/{id}")
    public SeanceDto getSingleSeance(@PathVariable Long id){
        return mapSeanceToSeanceDto(seanceService.getSingleSeance(id));
    }

    @DeleteMapping("/seances/{id}")
    public void deleteSeance(@PathVariable Long id){
        seanceService.deleteSeance(id);
    }
}
