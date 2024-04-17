package es.fct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import es.fct.model.Actor;
import es.fct.model.Pelicula;
import es.fct.repository.PeliculaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;

    @Autowired
    public PeliculaService(PeliculaRepository peliculaRepository) {
        this.peliculaRepository = peliculaRepository;
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
}
