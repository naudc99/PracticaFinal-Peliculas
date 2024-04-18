package es.fct.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.fct.model.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {
	@Query("SELECT p FROM Pelicula p WHERE p.genero.id = :idGenero")
	List<Pelicula> findByGeneroId(@Param("idGenero") Integer idGenero);
}

