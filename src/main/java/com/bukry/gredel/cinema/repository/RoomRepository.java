package com.bukry.gredel.cinema.repository;

import com.bukry.gredel.cinema.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Boolean existsByName(String name);

}
