package com.bukry.gredel.cinema.service;

import com.bukry.gredel.cinema.dto.RoomCreationDto;
import com.bukry.gredel.cinema.dto.RoomDto;
import com.bukry.gredel.cinema.exception.NoSuchRoomExistsException;
import com.bukry.gredel.cinema.exception.RoomAlreadyExistsException;
import com.bukry.gredel.cinema.exception.SeatAlreadyTakenException;
import com.bukry.gredel.cinema.model.Room;
import com.bukry.gredel.cinema.model.Seance;
import com.bukry.gredel.cinema.repository.RoomRepository;
import com.bukry.gredel.cinema.repository.SeanceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    private final SeanceRepository seanceRepository;

    public RoomService(RoomRepository roomRepository, SeanceRepository seanceRepository) {
        this.roomRepository = roomRepository;
        this.seanceRepository = seanceRepository;
    }

    public List<Room> getRooms() {
        return roomRepository.findAll();
    }


    @Transactional
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

    @Transactional
    public void updateRoom(RoomDto roomDto) {
        List<Seance> seanceList = seanceRepository.findAll();
        boolean roomNotBooked = seanceList.stream()
                .filter(seance -> seance.getRoom().getId().equals(roomDto.getId()) && seance.getIsPublicated())
                .collect(Collectors.toList()).isEmpty();
        if(!roomNotBooked)
            throw new SeatAlreadyTakenException(
                    "Room with id: "+roomDto.getId()+" is already booked for seance");

        Room room = roomRepository.findById(roomDto.getId())
                .orElseThrow();

        Boolean roomExists = roomRepository.existsByName(roomDto.getName());
        if(roomExists&&!room.getName().equals(roomDto.getName()))
            throw new RoomAlreadyExistsException(
                    "Room with name: "+roomDto.getName()+" already exists");
        room.setName(roomDto.getName());
        room.setSeats(room.getSeats());
        roomRepository.save(room);
    }
}
