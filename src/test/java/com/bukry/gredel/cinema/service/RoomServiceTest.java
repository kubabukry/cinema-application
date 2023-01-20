package com.bukry.gredel.cinema.service;

import com.bukry.gredel.cinema.dto.RoomCreationDto;
import com.bukry.gredel.cinema.dto.RoomDto;
import com.bukry.gredel.cinema.exception.NoSuchRoomExistsException;
import com.bukry.gredel.cinema.exception.RoomAlreadyExistsException;
import com.bukry.gredel.cinema.model.Room;
import com.bukry.gredel.cinema.model.Seance;
import com.bukry.gredel.cinema.repository.RoomRepository;
import com.bukry.gredel.cinema.repository.SeanceRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    @InjectMocks
    private RoomService roomService;
    @Mock
    private RoomRepository roomRepository;
    @Mock
    private SeanceRepository seanceRepository;

    private List<Room> roomList;

    private List<Seance> seanceList = new ArrayList<>();

    @BeforeEach
    void setUp(){
        this.roomService = new RoomService(this.roomRepository, this.seanceRepository);
        roomList = List.of(
                new Room(1L, "Room1"),
                new Room(2L, "Room2"),
                new Room(3L, "Room3"));
    }

    @Test
    @DisplayName("Get list of all Rooms")
    public void testGetRooms() {
        when(roomRepository.findAll()).thenReturn(roomList);
        List<Room> result = roomService.getRooms();
        assertEquals(roomList, result);
    }

    @Test
    @DisplayName("Create new Room")
    void testCreateRoom() {
        RoomCreationDto roomCreationDto = new RoomCreationDto("Room4", 100);
        when(roomRepository.existsByName(roomCreationDto.getName())).thenReturn(false);
        roomService.createRoom(roomCreationDto);
        verify(roomRepository).existsByName(roomCreationDto.getName());
        verify(roomRepository).save(any(Room.class));
    }
    @Test
    @DisplayName("Throw exception if Room already exists with given name")
    void testCreateRoomThrowRoomAlreadyExistsException(){
        String roomName = roomList.get(0).getName();
        when(roomRepository.existsByName(roomName)).thenReturn(true);
        assertThrows(RoomAlreadyExistsException.class, () -> roomService.createRoom(new RoomCreationDto(roomName, 100)),
                "Existing name should throw RoomAlreadyExistsException");
    }

    @Test
    @DisplayName("Get Room by id")
    void testGetSingleRoom() {
        Room room = roomList.get(0);
        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));
        Room result = roomService.getSingleRoom(1L);
        assertEquals(room, result, "Passing existing id should return Room by this id");
    }
    @Test
    @DisplayName("Throw exception if no Room with given id present")
    void testGetSingleRoomThrowNoSuchRoomExistsException(){
        Long id = 4L;
        when(roomRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NoSuchRoomExistsException.class, () -> roomService.getSingleRoom(id),
                "Not existing id should throw NoSuchRoomExistsException");
    }

    @Test
    @DisplayName("Delete Room if Room with id present")
    void testDeleteRoom() {
        when(roomRepository.existsById(1L)).thenReturn(true);
        roomService.deleteRoom(1L);
        verify(roomRepository).existsById(1L);
        verify(roomRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Update Room if Room with name not present")
    void testUpdateRoom() {
        RoomDto roomDto = new RoomDto(1L, "newRoom", 50);
        when(seanceRepository.findAll()).thenReturn(seanceList);
        when(roomRepository.existsByName(roomDto.getName())).thenReturn(false);
        when(roomRepository.findById(roomDto.getId())).thenReturn(Optional.of(roomList.get(0)));
        roomService.updateRoom(roomDto);
        verify(seanceRepository).findAll();
        verify(roomRepository).existsByName(roomDto.getName());
        verify(roomRepository).findById(roomDto.getId());
        verify(roomRepository).save(any(Room.class));
    }

    //test


}