package org.example.webclient.config;

import org.example.webclient.model.Userr;
import org.example.webclient.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner init() {
        return args -> {
            if (userRepository.findByUsername("admin") == null) {
                Userr admin = new Userr();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("password"));
                admin.setRoles("ADMIN");
                userRepository.save(admin);
            }
        };
    }
}
