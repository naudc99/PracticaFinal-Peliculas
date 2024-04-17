package es.fct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import es.fct.model.Actor;
import es.fct.model.Genero;
import es.fct.model.Rol;
import es.fct.model.Usuario;
import es.fct.repository.GeneroRepository;
import es.fct.repository.RolRepository;
import es.fct.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository; 
    }

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return ResponseEntity.ok(usuarios);
    }

    public ResponseEntity<Usuario> getUsuarioById(int id) {
        return usuarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario){
    	if (usuario.getNombreUsuario() == null || usuario.getEmail() == null || usuario.getPassword() == null) {
            return ResponseEntity.badRequest().build();
    	}
    	Usuario saveUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveUsuario);
    }
    
    public ResponseEntity<Usuario> updateUsuario(int id, Usuario usuario) {
        Optional<Usuario> existingUsuarioOptional = usuarioRepository.findById(id);

        if (existingUsuarioOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Usuario existingUsuario = existingUsuarioOptional.get();

        if (usuario.getNombreUsuario() != null) {
            existingUsuario.setNombreUsuario(usuario.getNombreUsuario());
        }

        if (usuario.getEmail() != null) {
            existingUsuario.setEmail(usuario.getEmail());
        }

        if (usuario.getPassword() != null) {
            existingUsuario.setPassword(usuario.getPassword());
        }


        Usuario updatedUsuario = usuarioRepository.save(existingUsuario);
        return ResponseEntity.ok(updatedUsuario);
    }

    public ResponseEntity<Void> deleteUsuario(int id) {
        Optional<Usuario> existingUsuarioOptional = usuarioRepository.findById(id);

        if (existingUsuarioOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    public ResponseEntity<Usuario> addRoltoUsuario(int usuarioId, int rolId) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);
        Optional<Rol> rolOptional = rolRepository.findById(rolId);

        if (usuarioOptional.isEmpty() || rolOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Usuario usuario = usuarioOptional.get();
        Rol rol = rolOptional.get();

        usuario.setRol(rol);

        Usuario updatedUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.ok(updatedUsuario);
    }
}
