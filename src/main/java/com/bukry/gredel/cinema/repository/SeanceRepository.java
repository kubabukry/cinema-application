package com.bukry.gredel.cinema.repository;

import com.bukry.gredel.cinema.model.Room;
import com.bukry.gredel.cinema.model.Seance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface SeanceRepository extends JpaRepository<Seance, Long> {
    Boolean existsByStartDateAndRoom(Instant startDate, Room room);
}
