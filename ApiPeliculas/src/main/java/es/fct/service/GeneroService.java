package es.fct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import es.fct.model.Actor;
import es.fct.model.Director;
import es.fct.model.Genero;
import es.fct.repository.GeneroRepository;
import es.fct.repository.PeliculaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroService {

    private final GeneroRepository generoRepository;

    @Autowired
    public GeneroService(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    public Genero saveGenero(Genero genero) {
        return generoRepository.save(genero);
    }

    public ResponseEntity<List<Genero>> getAllGeneros() {
        List<Genero> generos = generoRepository.findAll();
        return ResponseEntity.ok(generos);
    }

    public ResponseEntity<Genero> getGeneroById(int id) {
        return generoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    public ResponseEntity<Genero> createGenero(@RequestBody Genero genero){
    	if (genero.getNombre() == null) {
            return ResponseEntity.badRequest().build();
    	}
    	Genero saveGenero = generoRepository.save(genero);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveGenero);
    }

    public ResponseEntity<Genero> updateGenero(int id, Genero genero) {
        Optional<Genero> existingGeneroOptional = generoRepository.findById(id);

        if (existingGeneroOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Genero existingGenero = existingGeneroOptional.get();

        if (genero.getNombre() != null) {
            existingGenero.setNombre(genero.getNombre());
        }

        Genero updatedGenero = generoRepository.save(existingGenero);
        return ResponseEntity.ok(updatedGenero);
    }

    public ResponseEntity<Void> deleteGenero(int id) {
        Optional<Genero> existingGeneroOptional = generoRepository.findById(id);

        if (existingGeneroOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        generoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
