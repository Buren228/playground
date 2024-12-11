package ru.playground.playground.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.playground.playground.entities.PgUser;
import ru.playground.playground.entities.Role;
import ru.playground.playground.repos.UserRepository;

import java.util.Arrays;
import java.util.HashSet;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
        PgUser user = new PgUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setEnabled(true);

        Role role = new Role();
        role.setRoleName("ROLE_USER");

        user.setRoles(new HashSet<>(Arrays.asList(role)));
        userRepository.save(user);

        return "redirect:/login";
    }
}
