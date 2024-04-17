package es.fct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import es.fct.model.Actor;
import es.fct.model.Comentario;
import es.fct.repository.ComentarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;

    @Autowired
    public ComentarioService(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    public Comentario saveComentario(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    public ResponseEntity<List<Comentario>> getAllComentarios() {
        List<Comentario> comentarios = comentarioRepository.findAll();
        return ResponseEntity.ok(comentarios);
    }

    public ResponseEntity<Comentario> getComentarioById(int id) {
        return comentarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    public ResponseEntity<Comentario> createComentario(@RequestBody Comentario comentario) {
        Comentario savedComentario = comentarioRepository.save(comentario);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComentario);
    }

    public ResponseEntity<Comentario> updateComentario(int id, Comentario comentario) {
        Optional<Comentario> existingComentarioOptional = comentarioRepository.findById(id);

        if (existingComentarioOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Comentario existingComentario = existingComentarioOptional.get();

        if (comentario.getComentario() != null) {
            existingComentario.setComentario(comentario.getComentario());
        }

        if (comentario.getPuntuacion() != null) {
            existingComentario.setPuntuacion(comentario.getPuntuacion());
        }

        Comentario updatedComentario = comentarioRepository.save(existingComentario);
        return ResponseEntity.ok(updatedComentario);
    }

    public ResponseEntity<Void> deleteComentario(int id) {
        Optional<Comentario> existingComentarioOptional = comentarioRepository.findById(id);

        if (existingComentarioOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        comentarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
