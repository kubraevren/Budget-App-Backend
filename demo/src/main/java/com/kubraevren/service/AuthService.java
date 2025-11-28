package com.kubraevren.service;

import com.kubraevren.dto.AuthRequest;
import com.kubraevren.dto.AuthResponse;
import com.kubraevren.dto.RegisterRequest;
import com.kubraevren.model.UserEntity;
import com.kubraevren.repository.UserRepository;
import com.kubraevren.security.CustomUserDetails;
import com.kubraevren.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public void register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Kullanıcı adı zaten kullanımda.");
        }

        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        // Role default olarak Entity içinde "USER" atandığı için set etmeye gerek yok

        userRepository.save(user);
    }

    public AuthResponse login(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();
        String jwtToken = jwtService.generateToken(userDetails, userId);

        return AuthResponse.builder()
                .accessToken(jwtToken)
                .expiresIn(10 * 60 * 60L) // 10 saat (saniye cinsinden)
                .build();
    }
}