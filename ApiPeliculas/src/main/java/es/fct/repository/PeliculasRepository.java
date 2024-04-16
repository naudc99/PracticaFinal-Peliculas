package es.fct.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.fct.model.Peliculas;

public interface PeliculasRepository extends JpaRepository<Peliculas, Integer>{

}
