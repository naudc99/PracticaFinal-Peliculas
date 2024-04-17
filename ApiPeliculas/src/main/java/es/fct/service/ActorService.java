package es.fct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import es.fct.model.Actor;
import es.fct.repository.ActorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ActorService {

    private final ActorRepository actorRepository;

    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public Actor saveActor(Actor actor) {
        return actorRepository.save(actor);
    }

    public ResponseEntity<List<Actor>> getAllActores() {
        List<Actor> actores = actorRepository.findAll();
        return ResponseEntity.ok(actores);
    }

    public ResponseEntity<Actor> getActorById(int id) {
        return actorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Actor> createActor(@RequestBody Actor actor) {
    	if (actor.getNombre() == null || actor.getApellido() == null) {
                return ResponseEntity.badRequest().build();
        }
        Actor savedActor = actorRepository.save(actor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedActor);
    }
    
    public ResponseEntity<Actor> updateActor(int id, Actor actor) {
        Optional<Actor> existingActorOptional = actorRepository.findById(id);

        if (existingActorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Actor existingActor = existingActorOptional.get();

        if (actor.getNombre() != null) {
            existingActor.setNombre(actor.getNombre());
        }

        if (actor.getApellido() != null) {
            existingActor.setApellido(actor.getApellido());
        }

        Actor updatedActor = actorRepository.save(existingActor);
        return ResponseEntity.ok(updatedActor);
    }

    public ResponseEntity<Void> deleteActor(int id) {
        Optional<Actor> existingActorOptional = actorRepository.findById(id);

        if (existingActorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        actorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
