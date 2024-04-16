package es.fct.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.fct.model.Generos;

public interface GeneroRepository extends JpaRepository<Generos, Integer> {
}