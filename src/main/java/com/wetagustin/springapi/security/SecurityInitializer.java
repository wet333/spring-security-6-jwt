package com.wetagustin.springapi.security;

import com.wetagustin.springapi.security.models.Role;
import com.wetagustin.springapi.security.repositories.RoleRepository;
import com.wetagustin.springapi.security.configurations.Roles;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SecurityInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public SecurityInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Security initializer started...");
        for (String role : Roles.asList()) {
            if (!roleRepository.findByName(role).isPresent()) {
                Role newRole = new Role(null, role);
                roleRepository.save(newRole);
            }
        }
        System.out.println("Security initializer finished.");
    }
}
