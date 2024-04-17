package es.fct.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.fct.model.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Integer>{

}
