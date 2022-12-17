package com.bukry.gredel.cinema.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private Instant duration;

    @OneToMany(mappedBy = "id")
    private List<Seance> seanceList;

}
