package com.example.authentification;

import com.example.authentification.Entity.RoleEntity;
import com.example.authentification.Repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    @Bean
    CommandLineRunner initRoles(RoleRepository roleRepository) {
        return args -> {
            String roleName = "user";
            if (roleRepository.findByName(roleName).isEmpty()) {
                RoleEntity userRole = new RoleEntity();
                userRole.setName(roleName);
                roleRepository.save(userRole);
                System.out.println("Rôle 'user' créé et sauvegardé.");
            } else {
                System.out.println("Rôle 'user' déjà existant.");
            }
        };
    }
}
