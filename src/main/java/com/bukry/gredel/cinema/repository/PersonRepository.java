package com.bukry.gredel.cinema.repository;

import com.bukry.gredel.cinema.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Boolean existsByLogin(String login);
    Boolean existsByEmail(String email);

    //todo why to use Optionals here?
    Optional<Person> findByLogin(String login);
}
