package es.fct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import es.fct.model.Peliculas;
import es.fct.repository.PeliculasRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculasService {

    private final PeliculasRepository peliculasRepository;

    @Autowired
    public PeliculasService(PeliculasRepository peliculasRepository) {
        this.peliculasRepository = peliculasRepository;
    }

    public Peliculas savePeliculas(Peliculas peliculas) {
        return peliculasRepository.save(peliculas);
    }

    public ResponseEntity<List<Peliculas>> getAllPeliculas() {
        List<Peliculas> peliculas = peliculasRepository.findAll();
        return ResponseEntity.ok(peliculas);
    }

    public ResponseEntity<Peliculas> getPeliculasById(int id) {
        return peliculasRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Peliculas> updatePeliculas(int id, Peliculas peliculas) {
        Optional<Peliculas> existingPeliculasOptional = peliculasRepository.findById(id);

        if (existingPeliculasOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Peliculas existingPeliculas = existingPeliculasOptional.get();

        if (peliculas.getTitulo() != null) {
            existingPeliculas.setTitulo(peliculas.getTitulo());
        }

        if (peliculas.getSinopsis() != null) {
            existingPeliculas.setSinopsis(peliculas.getSinopsis());
        }


        if (peliculas.getAnioEstreno() != null) {
            existingPeliculas.setAnioEstreno(peliculas.getAnioEstreno());
        }

        if (peliculas.getActoresActuantes() != null) {
            existingPeliculas.setActoresActuantes(peliculas.getActoresActuantes());
        }

        Peliculas updatedPeliculas = peliculasRepository.save(existingPeliculas);
        return ResponseEntity.ok(updatedPeliculas);
    }

    public ResponseEntity<Void> deletePeliculas(int id) {
        Optional<Peliculas> existingPeliculasOptional = peliculasRepository.findById(id);

        if (existingPeliculasOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        peliculasRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
