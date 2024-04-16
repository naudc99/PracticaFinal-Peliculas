package es.fct.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.fct.model.Favoritos;

public interface FavoritosRepository extends JpaRepository<Favoritos, Integer> {

}
