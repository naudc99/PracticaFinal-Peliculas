package es.fct.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.fct.model.Usuarios;

public interface UsuariosRepository extends JpaRepository<Usuarios, Integer>{

}
