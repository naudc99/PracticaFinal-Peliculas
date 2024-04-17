package es.fct.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.fct.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {

}
