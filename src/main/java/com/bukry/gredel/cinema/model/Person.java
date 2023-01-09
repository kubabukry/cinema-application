package com.bukry.gredel.cinema.model;

import com.bukry.gredel.cinema.validation.ValidLogin;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Pattern(regexp = "^\\w+$", message = "login can contain only letters, numbers or _ character")
    private String login;
    private String password;
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

}
