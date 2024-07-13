package com.ticketing.userservice.service;

import com.ticketing.userservice.model.User;
import com.ticketing.userservice.repository.UserRepository;
import com.ticketing.userservice.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "user-events";

    public User saveUser(User user) {
        User savedUser = userRepository.save(user);
        kafkaTemplate.send(TOPIC, "User created: " + savedUser.toString());
        return savedUser;
    }

    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
        user.setEmail(userDetails.getEmail());
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setRole(userDetails.getRole());
        User updatedUser = userRepository.save(user);
        kafkaTemplate.send(TOPIC, "User updated: " + updatedUser.toString());
        return updatedUser;
    }
}
