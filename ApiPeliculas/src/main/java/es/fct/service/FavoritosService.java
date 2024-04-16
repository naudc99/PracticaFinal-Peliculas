package es.fct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import es.fct.model.Favoritos;
import es.fct.repository.FavoritosRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FavoritosService {

    private final FavoritosRepository favoritosRepository;

    @Autowired
    public FavoritosService(FavoritosRepository favoritosRepository) {
        this.favoritosRepository = favoritosRepository;
    }

    public Favoritos saveFavoritos(Favoritos favoritos) {
        return favoritosRepository.save(favoritos);
    }

    public ResponseEntity<List<Favoritos>> getAllFavoritos() {
        List<Favoritos> favoritos = favoritosRepository.findAll();
        return ResponseEntity.ok(favoritos);
    }

    public ResponseEntity<Favoritos> getFavoritosById(int id) {
        return favoritosRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Favoritos> updateFavoritos(int id, Favoritos favoritos) {
        Optional<Favoritos> existingFavoritosOptional = favoritosRepository.findById(id);

        if (existingFavoritosOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Favoritos existingFavoritos = existingFavoritosOptional.get();


        Favoritos updatedFavoritos = favoritosRepository.save(existingFavoritos);
        return ResponseEntity.ok(updatedFavoritos);
    }

    public ResponseEntity<Void> deleteFavoritos(int id) {
        Optional<Favoritos> existingFavoritosOptional = favoritosRepository.findById(id);

        if (existingFavoritosOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        favoritosRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
