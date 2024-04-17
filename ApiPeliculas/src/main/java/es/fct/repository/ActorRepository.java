package es.fct.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.fct.model.Actor;

public interface ActorRepository extends JpaRepository<Actor, Integer> {

}
