package es.fct.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.fct.model.Director;

public interface DirectorRepository extends JpaRepository<Director, Integer> {

}
