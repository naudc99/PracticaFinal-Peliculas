package es.fct.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.fct.model.Comentarios;

public interface ComentariosRepository extends JpaRepository<Comentarios, Integer> {

}
