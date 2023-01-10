package com.bukry.gredel.cinema.service;

import com.bukry.gredel.cinema.dto.RoomCreationDto;
import com.bukry.gredel.cinema.exception.NoSuchRoomExistsException;
import com.bukry.gredel.cinema.exception.RoomAlreadyExistsException;
import com.bukry.gredel.cinema.model.Room;
import com.bukry.gredel.cinema.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getRooms() {
        return roomRepository.findAll();
    }


    public void createRoom(RoomCreationDto roomCreationDto) {
        Boolean roomExists = roomRepository.existsByName(roomCreationDto.getName());
        if(roomExists)
            throw new RoomAlreadyExistsException(
                    "Room with name: "+roomCreationDto.getName()+" already exists");
        Room room = new Room();
        room.setName(roomCreationDto.getName());
        room.setSeats(roomCreationDto.getSeats());
        roomRepository.save(room);
    }

    public Room getSingleRoom(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new NoSuchRoomExistsException(
                        "No such room with id: "+id+" exists"));
    }

    //todo warunek nie ma seansów
    public void deleteRoom(Long id) {
        if(roomRepository.existsById(id))
            roomRepository.deleteById(id);
    }
}
