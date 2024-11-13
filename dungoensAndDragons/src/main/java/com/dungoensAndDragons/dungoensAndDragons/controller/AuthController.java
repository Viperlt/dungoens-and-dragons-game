package com.dungoensAndDragons.dungoensAndDragons.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dungoensAndDragons.dungoensAndDragons.payload.LoginRequest;
import com.dungoensAndDragons.dungoensAndDragons.payload.RegisterRequest;
import com.dungoensAndDragons.dungoensAndDragons.security.JwtTokenProvider;
import com.dungoensAndDragons.dungoensAndDragons.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Lazy
    @Autowired
    private AuthenticationManager authManager;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
	if (userService.getUserByUsername(registerRequest.getUsername()) != null) {
	    return ResponseEntity.badRequest().body("Username already taken.");
	}
	userService.registerUser(registerRequest.getUsername(), registerRequest.getPassword());
	return ResponseEntity.ok("User registered successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
	try {
	    Authentication authentication = authManager.authenticate(
		    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

	    // Comment the rest to isolate issues
	    // SecurityContextHolder.getContext().setAuthentication(authentication);
	    // String jwt = jwtTokenProvider.generateToken(authentication);
	    // return ResponseEntity.ok(new JwtResponse(jwt));

	    return ResponseEntity.ok("Authentication Successful");
	} catch (Exception e) {
	    e.printStackTrace(); // Print to debug the exception
	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
	}
    }
}
