package es.fct.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.fct.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
}
