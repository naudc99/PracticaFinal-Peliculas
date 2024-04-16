package es.fct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import es.fct.model.Actores;
import es.fct.repository.ActoresRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ActoresService {

    private final ActoresRepository actoresRepository;

    @Autowired
    public ActoresService(ActoresRepository actoresRepository) {
        this.actoresRepository = actoresRepository;
    }

    public Actores saveActores(Actores actores) {
        return actoresRepository.save(actores);
    }

    public ResponseEntity<List<Actores>> getAllActores() {
        List<Actores> actores = actoresRepository.findAll();
        return ResponseEntity.ok(actores);
    }

    public ResponseEntity<Actores> getActoresById(int id) {
        return actoresRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Actores> updateActores(int id, Actores actores) {
        Optional<Actores> existingActoresOptional = actoresRepository.findById(id);

        if (existingActoresOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Actores existingActores = existingActoresOptional.get();

        if (actores.getNombre() != null) {
            existingActores.setNombre(actores.getNombre());
        }

        if (actores.getApellido() != null) {
            existingActores.setApellido(actores.getApellido());
        }

        Actores updatedActores = actoresRepository.save(existingActores);
        return ResponseEntity.ok(updatedActores);
    }

    public ResponseEntity<Void> deleteActores(int id) {
        Optional<Actores> existingActoresOptional = actoresRepository.findById(id);

        if (existingActoresOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        actoresRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
