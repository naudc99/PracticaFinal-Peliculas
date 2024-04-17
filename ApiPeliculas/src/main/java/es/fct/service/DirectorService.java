package es.fct.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import es.fct.model.Director;
import es.fct.repository.DirectorRepository;

@Service
public class DirectorService {

    private final DirectorRepository directorRepository;

    @Autowired
    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    public Director saveDirector(Director director) {
        return directorRepository.save(director);
    }

    public ResponseEntity<List<Director>> getAllDirectores() {
        List<Director> directores = directorRepository.findAll();
        return ResponseEntity.ok(directores);
    }

    public ResponseEntity<Director> getDirectorById(int id) {
        return directorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Director> updateDirector(int id, Director director) {
        Optional<Director> existingDirectorOptional = directorRepository.findById(id);

        if (existingDirectorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Director existingDirector = existingDirectorOptional.get();

        if (director.getNombre() != null) {
            existingDirector.setNombre(director.getNombre());
        }

        if (director.getApellido() != null) {
            existingDirector.setApellido(director.getApellido());
        }

        Director updatedDirector = directorRepository.save(existingDirector);
        return ResponseEntity.ok(updatedDirector);
    }

    public ResponseEntity<Void> deleteDirector(int id) {
        Optional<Director> existingDirectorOptional = directorRepository.findById(id);

        if (existingDirectorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        directorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


