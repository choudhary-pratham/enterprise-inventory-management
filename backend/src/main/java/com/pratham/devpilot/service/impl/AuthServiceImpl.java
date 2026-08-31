package com.pratham.devpilot.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pratham.devpilot.common.enums.UserRole;
import com.pratham.devpilot.dto.request.RegisterRequest;
import com.pratham.devpilot.dto.response.RegisterResponse;
import com.pratham.devpilot.entity.User;
import com.pratham.devpilot.exception.DuplicateResourceException;
import com.pratham.devpilot.repository.UserRepository;
import com.pratham.devpilot.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException(
                    "User with email " + request.getEmail() + " already exists.");
        }

        LocalDateTime now = LocalDateTime.now();

        User user = User.builder()
                .id(UUID.randomUUID())
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(UserRole.USER)
                .enabled(true)
                .createdAt(now)
                .updatedAt(now)
                .build();

        User savedUser = userRepository.save(user);

        return RegisterResponse.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .role(savedUser.getRole().name())
                .build();
    }
}