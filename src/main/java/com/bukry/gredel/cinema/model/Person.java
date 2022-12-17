package com.bukry.gredel.cinema.model;

import jakarta.persistence.*;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String login;
    private String password;
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

}
