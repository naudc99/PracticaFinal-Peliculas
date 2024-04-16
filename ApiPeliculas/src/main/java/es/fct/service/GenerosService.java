package es.fct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import es.fct.model.Generos;
import es.fct.repository.GenerosRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GenerosService {

    private final GenerosRepository generosRepository;

    @Autowired
    public GenerosService(GenerosRepository generosRepository) {
        this.generosRepository = generosRepository;
    }

    public Generos saveGeneros(Generos generos) {
        return generosRepository.save(generos);
    }

    public ResponseEntity<List<Generos>> getAllGeneros() {
        List<Generos> generos = generosRepository.findAll();
        return ResponseEntity.ok(generos);
    }

    public ResponseEntity<Generos> getGenerosById(int id) {
        return generosRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Generos> updateGeneros(int id, Generos generos) {
        Optional<Generos> existingGenerosOptional = generosRepository.findById(id);

        if (existingGenerosOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Generos existingGeneros = existingGenerosOptional.get();

        if (generos.getNombre() != null) {
            existingGeneros.setNombre(generos.getNombre());
        }

        Generos updatedGeneros = generosRepository.save(existingGeneros);
        return ResponseEntity.ok(updatedGeneros);
    }

    public ResponseEntity<Void> deleteGeneros(int id) {
        Optional<Generos> existingGenerosOptional = generosRepository.findById(id);

        if (existingGenerosOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        generosRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
