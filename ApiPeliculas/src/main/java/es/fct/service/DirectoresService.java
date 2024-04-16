package es.fct.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import es.fct.model.Directores;
import es.fct.repository.DirectoresRepository;

@Service
public class DirectoresService {

    private final DirectoresRepository directoresRepository;

    @Autowired
    public DirectoresService(DirectoresRepository directoresRepository) {
        this.directoresRepository = directoresRepository;
    }

    public Directores saveDirectores(Directores directores) {
        return directoresRepository.save(directores);
    }

    public ResponseEntity<List<Directores>> getAllDirectores() {
        List<Directores> directores = directoresRepository.findAll();
        return ResponseEntity.ok(directores);
    }

    public ResponseEntity<Directores> getDirectoresById(int id) {
        return directoresRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Directores> updateDirectores(int id, Directores directores) {
        Optional<Directores> existingDirectoresOptional = directoresRepository.findById(id);

        if (existingDirectoresOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Directores existingDirectores = existingDirectoresOptional.get();

        if (directores.getNombre() != null) {
            existingDirectores.setNombre(directores.getNombre());
        }

        if (directores.getApellido() != null) {
            existingDirectores.setApellido(directores.getApellido());
        }

        Directores updatedDirectores = directoresRepository.save(existingDirectores);
        return ResponseEntity.ok(updatedDirectores);
    }

    public ResponseEntity<Void> deleteDirectores(int id) {
        Optional<Directores> existingDirectoresOptional = directoresRepository.findById(id);

        if (existingDirectoresOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        directoresRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


