package es.fct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import es.fct.model.Favorito;
import es.fct.repository.FavoritoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FavoritoService {

    private final FavoritoRepository favoritoRepository;

    @Autowired
    public FavoritoService(FavoritoRepository favoritosRepository) {
        this.favoritoRepository = favoritosRepository;
    }

    public Favorito saveFavorito(Favorito favorito) {
        return favoritoRepository.save(favorito);
    }

    public ResponseEntity<List<Favorito>> getAllFavoritos() {
        List<Favorito> favoritos = favoritoRepository.findAll();
        return ResponseEntity.ok(favoritos);
    }

    public ResponseEntity<Favorito> getFavoritoById(int id) {
        return favoritoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Favorito> updateFavorito(int id, Favorito favoritos) {
        Optional<Favorito> existingFavoritoOptional = favoritoRepository.findById(id);

        if (existingFavoritoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Favorito existingFavorito = existingFavoritoOptional.get();


        Favorito updatedFavorito = favoritoRepository.save(existingFavorito);
        return ResponseEntity.ok(updatedFavorito);
    }

    public ResponseEntity<Void> deleteFavorito(int id) {
        Optional<Favorito> existingFavoritoOptional = favoritoRepository.findById(id);

        if (existingFavoritoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        favoritoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
