package es.fct.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.fct.model.Generos;

public interface GenerosRepository extends JpaRepository<Generos, Integer> {
}
