package com.pratham.devpilot.service;

import com.pratham.devpilot.dto.request.LoginRequest;
import com.pratham.devpilot.dto.request.RegisterRequest;
import com.pratham.devpilot.dto.response.LoginResponse;
import com.pratham.devpilot.dto.response.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}