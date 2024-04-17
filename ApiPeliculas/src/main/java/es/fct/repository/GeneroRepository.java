package es.fct.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.fct.model.Genero;

public interface GeneroRepository extends JpaRepository<Genero, Integer> {
}
