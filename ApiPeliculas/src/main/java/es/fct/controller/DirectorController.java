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

import es.fct.model.Director;
import es.fct.service.DirectorService;

@RestController
@RequestMapping("/api/directores")
public class DirectorController {

    private final DirectorService directorService;

    @Autowired
    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping
    public ResponseEntity<List<Director>> getAllDirector() {
        return directorService.getAllDirectores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Director> getDirectorById(@PathVariable Integer id) {
        return directorService.getDirectorById(id);
    }

    @PostMapping
    public ResponseEntity<Director> createDirector(@RequestBody Director director) {
        return directorService.createDirector(director);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Director> updateDirector(@PathVariable Integer id, @RequestBody Director director) {
        return directorService.updateDirector(id, director);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirector(@PathVariable Integer id) {
        return directorService.deleteDirector(id);
    }
}
