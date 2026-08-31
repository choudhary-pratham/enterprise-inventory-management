package com.pratham.devpilot.controller;

import com.pratham.devpilot.dto.request.CodeAnalysisRequest;
import com.pratham.devpilot.dto.response.CodeAnalysisResponse;
import com.pratham.devpilot.service.CodeAnalysisService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/code-analysis")
@RequiredArgsConstructor
public class CodeAnalysisController {

    private final CodeAnalysisService codeAnalysisService;

    @PostMapping("/analyze")
    public ResponseEntity<CodeAnalysisResponse> analyze(
            @Valid @RequestBody CodeAnalysisRequest request) {

        return ResponseEntity.ok(
                codeAnalysisService.analyze(request));
    }
}