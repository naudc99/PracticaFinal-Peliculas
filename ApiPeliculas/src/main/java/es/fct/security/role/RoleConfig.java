package es.fct.security.role;

import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Configuration
public class RoleConfig {

    private final RoleRepository roleRepository;

    public RoleConfig(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void initRoles() {
        createRoleIfNotFound("USER");
        createRoleIfNotFound("ADMIN");
    }

    @Transactional
    public void createRoleIfNotFound(String name) {
        if (!roleRepository.findByName(name).isPresent()) {
            Role role = new Role(name);
            roleRepository.save(role);
        }
    }
}


