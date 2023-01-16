package com.bukry.gredel.cinema.repository;

import com.bukry.gredel.cinema.model.Seance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeanceRepository extends JpaRepository<Seance, Long> {
}
