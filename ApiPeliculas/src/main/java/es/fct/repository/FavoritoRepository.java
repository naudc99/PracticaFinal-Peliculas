package es.fct.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.fct.model.Favorito;

public interface FavoritoRepository extends JpaRepository<Favorito, Integer> {

}
