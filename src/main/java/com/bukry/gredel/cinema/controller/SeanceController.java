package com.bukry.gredel.cinema.controller;

import com.bukry.gredel.cinema.dto.SeanceCreationDto;
import com.bukry.gredel.cinema.dto.SeanceDto;
import com.bukry.gredel.cinema.service.SeanceService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.bukry.gredel.cinema.mapper.SeanceMapper.mapSeanceListToSeanceDtoList;
import static com.bukry.gredel.cinema.mapper.SeanceMapper.mapSeanceToSeanceDto;

import java.util.List;

@RestController
@Tag(name = "seances")
public class SeanceController {
    private final SeanceService seanceService;

    public SeanceController(SeanceService seanceService) {
        this.seanceService = seanceService;
    }

    @PostMapping("/seances/create")
    @SecurityRequirement(name = "bearer")
    public void createSeance(@RequestBody SeanceCreationDto seanceCreationDto){
        seanceService.createSeance(seanceCreationDto);
    }

    @PutMapping("/seances/update")
    @SecurityRequirement(name = "bearer")
    public void updateSeance(@RequestBody SeanceDto seanceDto){
        seanceService.updateSeance(seanceDto);
    }

    @GetMapping("/seances/all")
    public List<SeanceDto> getSeances(){
        return mapSeanceListToSeanceDtoList(seanceService.getSeances());
    }

    @GetMapping("/seances/single/{id}")
    public SeanceDto getSingleSeance(@PathVariable Long id){
        return mapSeanceToSeanceDto(seanceService.getSingleSeance(id));
    }

    @DeleteMapping("/seances/delete/{id}")
    @SecurityRequirement(name = "bearer")
    public void deleteSeance(@PathVariable Long id){
        seanceService.deleteSeance(id);
    }
}
