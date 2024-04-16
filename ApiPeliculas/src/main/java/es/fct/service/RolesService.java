package es.fct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import es.fct.model.Roles;
import es.fct.repository.RolesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RolesService {

    private final RolesRepository rolesRepository;

    @Autowired
    public RolesService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public Roles saveRoles(Roles roles) {
        return rolesRepository.save(roles);
    }

    public ResponseEntity<List<Roles>> getAllRoles() {
        List<Roles> roles = rolesRepository.findAll();
        return ResponseEntity.ok(roles);
    }

    public ResponseEntity<Roles> getRolesById(int id) {
        return rolesRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Roles> updateRoles(int id, Roles roles) {
        Optional<Roles> existingRolesOptional = rolesRepository.findById(id);

        if (existingRolesOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Roles existingRoles = existingRolesOptional.get();


        Roles updatedRoles = rolesRepository.save(existingRoles);
        return ResponseEntity.ok(updatedRoles);
    }

    public ResponseEntity<Void> deleteRoles(int id) {
        Optional<Roles> existingRolesOptional = rolesRepository.findById(id);

        if (existingRolesOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        rolesRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
