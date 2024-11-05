package com.dungoensAndDragons.dungoensAndDragons.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dungoensAndDragons.dungoensAndDragons.model.User;
import com.dungoensAndDragons.dungoensAndDragons.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
	if (userService.getUserByUsername(user.getUsername()) != null) {
	    return ResponseEntity.badRequest().body("Username already taken.");
	}
	userService.registerUser(user.getUsername(), user.getPassword());
	return ResponseEntity.ok("User registered successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
	User existingUser = userService.getUserByUsername(user.getUsername());
	if (existingUser != null && passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
	    return ResponseEntity.ok("Login successful.");
	}
	return ResponseEntity.status(401).body("Invalid username or password.");
    }
}
