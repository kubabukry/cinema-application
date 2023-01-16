package com.bukry.gredel.cinema.mapper;

import com.bukry.gredel.cinema.dto.RoomDto;
import com.bukry.gredel.cinema.model.Room;

import java.util.List;
import java.util.stream.Collectors;

public class RoomMapper {

    public static List<RoomDto> mapRoomListToRoomDtoList(List<Room> roomList){
        return roomList.stream()
                .map(room -> RoomDto.builder()
                            .id(room.getId())
                            .name(room.getName())
                            .seats(room.getSeats())
                            .build())
                .collect(Collectors.toList());
    }

    public static RoomDto mapRoomToRoomDto(Room room){
        return RoomDto.builder()
                .id(room.getId())
                .name(room.getName())
                .seats(room.getSeats())
                .build();
    }
}
