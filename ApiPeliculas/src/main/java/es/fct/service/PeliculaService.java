package es.fct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import es.fct.model.Actor;
import es.fct.model.Director;
import es.fct.model.Genero;
import es.fct.model.Pelicula;
import es.fct.repository.ActorRepository;
import es.fct.repository.DirectorRepository;
import es.fct.repository.GeneroRepository;
import es.fct.repository.PeliculaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;
    
    private final ActorRepository actorRepository;
    
    private final DirectorRepository directorRepository;
    
    private final GeneroRepository generoRepository;

    @Autowired
    public PeliculaService(PeliculaRepository peliculaRepository, ActorRepository actorRepository,
    		DirectorRepository directorRepository, GeneroRepository generoRepository ) {
        this.peliculaRepository = peliculaRepository;
        this.actorRepository=actorRepository;
        this.directorRepository=directorRepository;
        this.generoRepository=generoRepository;
    }

    public Pelicula savePelicula(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    public ResponseEntity<List<Pelicula>> getAllPeliculas() {
        List<Pelicula> peliculas = peliculaRepository.findAll();
        return ResponseEntity.ok(peliculas);
    }

    public ResponseEntity<Pelicula> getPeliculaById(int id) {
        return peliculaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    public ResponseEntity<Pelicula> createPelicula(@RequestBody Pelicula pelicula) {
    	if (pelicula.getTitulo() == null || pelicula.getSinopsis() == null || pelicula.getAnioEstreno() == null) {
            return ResponseEntity.badRequest().build();
    	}
        Pelicula savedPelicula = peliculaRepository.save(pelicula);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPelicula);
    }

    public ResponseEntity<Pelicula> updatePelicula(int id, Pelicula pelicula) {
        Optional<Pelicula> existingPeliculaOptional = peliculaRepository.findById(id);

        if (existingPeliculaOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Pelicula existingPelicula = existingPeliculaOptional.get();

        if (pelicula.getTitulo() != null) {
            existingPelicula.setTitulo(pelicula.getTitulo());
        }

        if (pelicula.getSinopsis() != null) {
            existingPelicula.setSinopsis(pelicula.getSinopsis());
        }


        if (pelicula.getAnioEstreno() != null) {
            existingPelicula.setAnioEstreno(pelicula.getAnioEstreno());
        }


        Pelicula updatedPelicula = peliculaRepository.save(existingPelicula);
        return ResponseEntity.ok(updatedPelicula);
    }

    public ResponseEntity<Void> deletePelicula(int id) {
        Optional<Pelicula> existingPeliculaOptional = peliculaRepository.findById(id);

        if (existingPeliculaOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        peliculaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    public ResponseEntity<Pelicula> addActorToPelicula(int peliculaId, int actorId) {
        Optional<Pelicula> peliculaOptional = peliculaRepository.findById(peliculaId);
        Optional<Actor> actorOptional = actorRepository.findById(actorId);

        if (peliculaOptional.isEmpty() || actorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Pelicula pelicula = peliculaOptional.get();
        Actor actor = actorOptional.get();

        if (pelicula.getActores().contains(actor)) {
            return ResponseEntity.badRequest().body(pelicula); // Actor already associated with the movie
        }

        pelicula.getActores().add(actor);
        Pelicula updatedPelicula = peliculaRepository.save(pelicula);

        return ResponseEntity.ok(updatedPelicula);
    }
    
    public ResponseEntity<Pelicula> addDirectorToPelicula(int peliculaId, int directorId) {
        Optional<Pelicula> peliculaOptional = peliculaRepository.findById(peliculaId);
        Optional<Director> directorOptional = directorRepository.findById(directorId);

        if (peliculaOptional.isEmpty() || directorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Pelicula pelicula = peliculaOptional.get();
        Director director = directorOptional.get();

        if (pelicula.getDirector() != null && pelicula.getDirector().equals(director)) {
            return ResponseEntity.badRequest().body(pelicula); // Director already associated with the movie
        }

        pelicula.setDirector(director);
        Pelicula updatedPelicula = peliculaRepository.save(pelicula);

        return ResponseEntity.ok(updatedPelicula);
    }
    
    public ResponseEntity<Pelicula> addGeneroToPelicula(int peliculaId, int generoId) {
        Optional<Pelicula> peliculaOptional = peliculaRepository.findById(peliculaId);
        Optional<Genero> generoOptional = generoRepository.findById(generoId);

        if (peliculaOptional.isEmpty() || generoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Pelicula pelicula = peliculaOptional.get();
        Genero genero = generoOptional.get();

        if (pelicula.getGenero() != null && pelicula.getGenero().equals(genero)) {
            return ResponseEntity.badRequest().body(pelicula); // Género already associated with the movie
        }

        pelicula.setGenero(genero);
        Pelicula updatedPelicula = peliculaRepository.save(pelicula);

        return ResponseEntity.ok(updatedPelicula);
    }
}
