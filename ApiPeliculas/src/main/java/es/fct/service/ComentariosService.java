package es.fct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import es.fct.model.Comentarios;
import es.fct.repository.ComentariosRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ComentariosService {

    private final ComentariosRepository comentariosRepository;

    @Autowired
    public ComentariosService(ComentariosRepository comentariosRepository) {
        this.comentariosRepository = comentariosRepository;
    }

    public Comentarios saveComentarios(Comentarios comentarios) {
        return comentariosRepository.save(comentarios);
    }

    public ResponseEntity<List<Comentarios>> getAllComentarios() {
        List<Comentarios> comentarios = comentariosRepository.findAll();
        return ResponseEntity.ok(comentarios);
    }

    public ResponseEntity<Comentarios> getComentariosById(int id) {
        return comentariosRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Comentarios> updateComentarios(int id, Comentarios comentarios) {
        Optional<Comentarios> existingComentariosOptional = comentariosRepository.findById(id);

        if (existingComentariosOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Comentarios existingComentarios = existingComentariosOptional.get();

        if (comentarios.getComentario() != null) {
            existingComentarios.setComentario(comentarios.getComentario());
        }

        if (comentarios.getPuntuacion() != null) {
            existingComentarios.setPuntuacion(comentarios.getPuntuacion());
        }

        Comentarios updatedComentarios = comentariosRepository.save(existingComentarios);
        return ResponseEntity.ok(updatedComentarios);
    }

    public ResponseEntity<Void> deleteComentarios(int id) {
        Optional<Comentarios> existingComentariosOptional = comentariosRepository.findById(id);

        if (existingComentariosOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        comentariosRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
