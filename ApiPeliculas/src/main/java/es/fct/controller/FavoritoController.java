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

import es.fct.model.Favorito;
import es.fct.service.FavoritoService;


@RestController
@RequestMapping("/api/favoritos")
public class FavoritoController {

    private final FavoritoService favoritoService;

    @Autowired
    public FavoritoController(FavoritoService favoritoService) {
        this.favoritoService = favoritoService;
    }
    
    @GetMapping
    public ResponseEntity<List<Favorito>> getAllFavoritos() {
        return favoritoService.getAllFavoritos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Favorito> getFavoritoById(@PathVariable Integer id) {
        return favoritoService.getFavoritoById(id);
    }

    @PostMapping
    public ResponseEntity<Favorito> createFavorito(@RequestBody Favorito favorito) {
        return favoritoService.createFavorito(favorito);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Favorito> updateFavorito(@PathVariable Integer id, @RequestBody Favorito favorito) {
        return favoritoService.updateFavorito(id, favorito);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavorito(@PathVariable Integer id) {
        return favoritoService.deleteFavorito(id);
    }
}
