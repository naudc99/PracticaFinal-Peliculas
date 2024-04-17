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

import es.fct.model.Genero;
import es.fct.service.GeneroService;

@RestController
@RequestMapping("/api/generos")
public class GeneroController {

    private final GeneroService generoService;
    
    @Autowired
    public GeneroController(GeneroService generoService) {
        this.generoService = generoService;
    }

    @GetMapping
    public ResponseEntity<List<Genero>> getAllGeneros() {
        return generoService.getAllGeneros();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genero> getGeneroById(@PathVariable Integer id) {
        return generoService.getGeneroById(id);
    }

    @PostMapping
    public ResponseEntity<Genero> createGenero(@RequestBody Genero genero) {
        return generoService.createGenero(genero);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genero> updateGenero(@PathVariable Integer id, @RequestBody Genero genero) {
        return generoService.updateGenero(id, genero);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenero(@PathVariable Integer id) {
        return generoService.deleteGenero(id);
    }
}
