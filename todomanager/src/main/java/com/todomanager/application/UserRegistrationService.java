package com.todomanager.application;

import com.todomanager.domain.User;
import com.todomanager.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {
    private final UserRepository userRepository;

    public UserRegistrationResponse register(UserRegistrationRequest request) {
        validateRequest(request);

        if (userRepository.existsByUsername(request.username())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User savedUser = userRepository.save(new User(null, request.username(), request.email()));
        return new UserRegistrationResponse(savedUser.id(), savedUser.username(), savedUser.email());
    }

    private void validateRequest(UserRegistrationRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request is required");
        }
        if (request.username() == null || request.username().isBlank()) {
            throw new IllegalArgumentException("username is required");
        }
        if (request.email() == null || request.email().isBlank()) {
            throw new IllegalArgumentException("email is required");
        }
    }
}
