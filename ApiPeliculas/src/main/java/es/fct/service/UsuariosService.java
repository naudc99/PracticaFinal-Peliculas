package es.fct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import es.fct.model.Usuarios;
import es.fct.repository.UsuariosRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariosService {

    private final UsuariosRepository usuariosRepository;

    @Autowired
    public UsuariosService(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    public Usuarios saveUsuarios(Usuarios usuarios) {
        return usuariosRepository.save(usuarios);
    }

    public ResponseEntity<List<Usuarios>> getAllUsuarios() {
        List<Usuarios> usuarios = usuariosRepository.findAll();
        return ResponseEntity.ok(usuarios);
    }

    public ResponseEntity<Usuarios> getUsuariosById(int id) {
        return usuariosRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Usuarios> updateUsuarios(int id, Usuarios usuarios) {
        Optional<Usuarios> existingUsuariosOptional = usuariosRepository.findById(id);

        if (existingUsuariosOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Usuarios existingUsuarios = existingUsuariosOptional.get();

        if (usuarios.getNombreUsuario() != null) {
            existingUsuarios.setNombreUsuario(usuarios.getNombreUsuario());
        }

        if (usuarios.getEmail() != null) {
            existingUsuarios.setEmail(usuarios.getEmail());
        }

        if (usuarios.getContraseña() != null) {
            existingUsuarios.setContraseña(usuarios.getContraseña());
        }


        Usuarios updatedUsuarios = usuariosRepository.save(existingUsuarios);
        return ResponseEntity.ok(updatedUsuarios);
    }

    public ResponseEntity<Void> deleteUsuarios(int id) {
        Optional<Usuarios> existingUsuariosOptional = usuariosRepository.findById(id);

        if (existingUsuariosOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        usuariosRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
