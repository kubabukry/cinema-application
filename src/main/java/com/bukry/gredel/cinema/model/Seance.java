package com.bukry.gredel.cinema.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Seance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Instant startDate;
    private Instant endDate;
    private Boolean isPublicated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_movie", referencedColumnName = "id")
    @ToString.Exclude
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_room", referencedColumnName = "id")
    @ToString.Exclude
    private Room room;

    @OneToMany(mappedBy = "seance")
    @ToString.Exclude
    private List<Reservation> reservationList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Seance seance = (Seance) o;
        return id != null && Objects.equals(id, seance.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
