package com.bukry.gredel.cinema.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Entity
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
//  private Instant duration; //chcemy podac ile bedzie trwal film a nie czas trwania w kalendarzu
    private Integer duration;

    @OneToMany(mappedBy = "id")
    private List<Seance> seanceList;

}
