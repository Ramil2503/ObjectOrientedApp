package org.example.webclient.service;

import org.example.webclient.model.Userr;
import org.example.webclient.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Userr registerNewUser(String username, String password) {
        if (userRepository.findByUsername(username) != null) {
            throw new IllegalArgumentException("Username already exists");
        }
        Userr user = new Userr();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles("USER");
        return userRepository.save(user);
    }
}
