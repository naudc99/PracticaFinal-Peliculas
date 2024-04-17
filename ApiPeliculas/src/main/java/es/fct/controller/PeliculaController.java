package es.fct.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.fct.model.Pelicula;
import es.fct.service.PeliculaService;


@RestController
@RequestMapping("/api/peliculas")
public class PeliculaController {

    private final PeliculaService peliculaService;

    @Autowired
    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @GetMapping
    public ResponseEntity<List<Pelicula>> getAllPeliculas() {
        return peliculaService.getAllPeliculas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> getPeliculaById(@PathVariable Integer id) {
        return peliculaService.getPeliculaById(id);
    }

    @PostMapping
    public ResponseEntity<Pelicula> createPelicula(@RequestBody Pelicula pelicula) {
        return peliculaService.createPelicula(pelicula);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> updatePelicula(@PathVariable Integer id, @RequestBody Pelicula pelicula) {
        return peliculaService.updatePelicula(id, pelicula);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePelicula(@PathVariable Integer id) {
        return peliculaService.deletePelicula(id);
    }
    
    @PostMapping("/{peliculaId}/actores/{actorId}")
    public ResponseEntity<Pelicula> addActorToPelicula(@PathVariable int peliculaId, @PathVariable int actorId) {
        return peliculaService.addActorToPelicula(peliculaId, actorId);
    }
    
    @PostMapping("/{peliculaId}/directores/{directorId}")
    public ResponseEntity<Pelicula> addDirectorToPelicula(@PathVariable int peliculaId, @PathVariable int directorId) {
        return peliculaService.addDirectorToPelicula(peliculaId, directorId);
    }
    @PostMapping("/{peliculaId}/generos/{generoId}")
    public ResponseEntity<Pelicula> addGeneroToPelicula(@PathVariable int peliculaId, @PathVariable int generoId) {
        return peliculaService.addGeneroToPelicula(peliculaId, generoId);
    }

}
