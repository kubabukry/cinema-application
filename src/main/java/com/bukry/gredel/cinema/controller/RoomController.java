package com.bukry.gredel.cinema.controller;

import com.bukry.gredel.cinema.dto.RoomCreationDto;
import com.bukry.gredel.cinema.dto.RoomDto;
import com.bukry.gredel.cinema.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import static com.bukry.gredel.cinema.mapper.RoomMapper.mapRoomListToRoomDtoList;
import static com.bukry.gredel.cinema.mapper.RoomMapper.mapRoomToRoomDto;

import java.util.List;

@RestController
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/rooms")
    public List<RoomDto> getRooms(){
        return mapRoomListToRoomDtoList(roomService.getRooms());
    }

    @GetMapping("/rooms/{id}")
    public RoomDto getSingleRoom(@PathVariable Long id){
        return mapRoomToRoomDto(roomService.getSingleRoom(id));
    }

    @PostMapping("/rooms")
    public void createRoom(@Valid @RequestBody RoomCreationDto roomCreationDto){
        roomService.createRoom(roomCreationDto);
    }

    @PutMapping("/rooms")
    public void updateRoom(@Valid @RequestBody RoomDto roomDto){
        roomService.updateRoom(roomDto);
    }

    @DeleteMapping("/rooms/{id}")
    public void deleteRoom(@PathVariable Long id){
        roomService.deleteRoom(id);
    }
}
