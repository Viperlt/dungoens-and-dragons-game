package com.dungoensAndDragons.dungoensAndDragons.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dungoensAndDragons.dungoensAndDragons.exceptions.ResourceNotFoundException;
import com.dungoensAndDragons.dungoensAndDragons.model.User;
import com.dungoensAndDragons.dungoensAndDragons.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> getUserById(Long id) {
	return userRepository.findById(id);
    }

    public User getUserByUsername(String username) {
	return userRepository.findByUsername(username);
    }

    public User createUser(User user) {
	return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
	User user = userRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));

	user.setUsername(userDetails.getUsername());
	user.setPassword(userDetails.getPassword());
	user.setMail(userDetails.getMail());

	return userRepository.save(user);
    }

    public User registerUser(String username, String password) {
	User user = new User();

	user.setUsername(username);
	user.setPassword(passwordEncoder.encode(password));

	return userRepository.save(user);
    }
}
