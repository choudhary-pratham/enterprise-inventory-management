package com.pratham.devpilot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pratham.devpilot.dto.request.DebugRequest;
import com.pratham.devpilot.dto.response.DebugResponse;
import com.pratham.devpilot.service.DebugService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/debug")
@RequiredArgsConstructor
public class DebugController {

    private final DebugService debugService;

    @PostMapping
    public ResponseEntity<DebugResponse> debug(
            @Valid @RequestBody DebugRequest request) {

        return ResponseEntity.ok(
                debugService.debug(request));
    }
}