package com.bukry.gredel.cinema.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private Integer seats;

    @OneToMany(mappedBy = "id")
    private List<Seance> seanceList;
}
