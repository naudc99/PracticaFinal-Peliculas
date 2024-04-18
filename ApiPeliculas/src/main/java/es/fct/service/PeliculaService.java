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

import java.util.ArrayList;
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

        if (pelicula.getGenero() != null && pelicula.getGenero().getIdGenero() != 0) {
            Genero genero = generoRepository.findById(pelicula.getGenero().getIdGenero()).orElse(null);
            if (genero != null) {
                pelicula.setGenero(genero);
            }
        }

        if (pelicula.getDirector() != null && pelicula.getDirector().getIdDirector() != 0) {
            Director director = directorRepository.findById(pelicula.getDirector().getIdDirector()).orElse(null);
            if (director != null) {
                pelicula.setDirector(director);
            }
        }

        if (pelicula.getActores() != null && !pelicula.getActores().isEmpty()) {
            List<Actor> actores = new ArrayList<>();
            for (Actor actor : pelicula.getActores()) {
                Optional<Actor> actorOptional = actorRepository.findById(actor.getIdActor());
                actorOptional.ifPresent(actores::add);
            }
            pelicula.setActores(actores);
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
    public List<Pelicula> getPeliculasByGenero(Integer idGenero) {
        return peliculaRepository.findByGeneroId(idGenero);
    }
    

}
