package es.fct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import es.fct.model.Rol;
import es.fct.repository.RolRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {

    private final RolRepository rolRepository;

    @Autowired
    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public Rol saveRol(Rol rol) {
        return rolRepository.save(rol);
    }

    public ResponseEntity<List<Rol>> getAllRoles() {
        List<Rol> roles = rolRepository.findAll();
        return ResponseEntity.ok(roles);
    }

    public ResponseEntity<Rol> getRolById(int id) {
        return rolRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Rol> updateRol(int id, Rol rol) {
        Optional<Rol> existingRolOptional = rolRepository.findById(id);

        if (existingRolOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Rol existingRol = existingRolOptional.get();


        Rol updatedRol = rolRepository.save(existingRol);
        return ResponseEntity.ok(updatedRol);
    }

    public ResponseEntity<Void> deleteRol(int id) {
        Optional<Rol> existingRolOptional = rolRepository.findById(id);

        if (existingRolOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        rolRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
