package com.bukry.gredel.cinema.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;

@Entity
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Instant startDate;
    private Instant endDate;
    private Boolean isPublicated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_movie", referencedColumnName = "id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_room", referencedColumnName = "id")
    private Room room;

    @OneToMany(mappedBy = "id")
    private List<Reservation> reservationList;
}