package com.kubraevren.controller;

import com.kubraevren.dto.AuthRequest;
import com.kubraevren.dto.AuthResponse;
import com.kubraevren.dto.RegisterRequest;
import com.kubraevren.service.AuthService;
import com.kubraevren.wrappper.ApiResponse;
import com.kubraevren.wrappper.ResponseMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(ResponseMessage.USER_CREATED,null));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody AuthRequest request) {
       AuthResponse response=authService.login(request);
        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.LOGIN_SUCCESS,response));
    }
}