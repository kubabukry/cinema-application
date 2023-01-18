package com.bukry.gredel.cinema.service;

import com.bukry.gredel.cinema.model.Room;
import com.bukry.gredel.cinema.repository.RoomRepository;
import com.bukry.gredel.cinema.repository.SeanceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    @InjectMocks
    private RoomService roomService;
    @Mock
    private RoomRepository roomRepository;
    @Mock
    private SeanceRepository seanceRepository;

    @BeforeEach
    void setUp(){
        this.roomService = new RoomService(this.roomRepository, this.seanceRepository);
    }

    @Test
    public void testGetRooms() {
        Room room1 = new Room(1L, "Room1");
        Room room2 = new Room(2L, "Room2");
        List<Room> rooms = Arrays.asList(room1, room2);
        when(roomRepository.findAll()).thenReturn(rooms);
        List<Room> result = roomService.getRooms();
        assertEquals(rooms, result);
    }

    @Test
    void createRoom() {
    }

    @Test
    void getSingleRoom() {
        Room room = new Room(1L, "Room");
        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));
        Room result = roomService.getSingleRoom(1L);
        assertEquals(room, result);
    }

    @Test
    void deleteRoom() {
    }

    @Test
    void updateRoom() {
    }
}