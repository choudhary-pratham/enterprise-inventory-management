package com.pratham.devpilot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pratham.devpilot.dto.request.CodeReviewRequest;
import com.pratham.devpilot.dto.response.CodeReviewResponse;
import com.pratham.devpilot.service.CodeReviewService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/code-review")
@RequiredArgsConstructor
public class CodeReviewController {

    private final CodeReviewService codeReviewService;

    @PostMapping("/review")
    public ResponseEntity<CodeReviewResponse> review(
            @Valid @RequestBody CodeReviewRequest request) {

        return ResponseEntity.ok(
                codeReviewService.review(request));
    }
}