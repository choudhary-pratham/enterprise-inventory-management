package com.pratham.devpilot.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodeAnalysisRequest {

    @NotBlank(message = "Code or error message is required")
    private String content;

    @NotBlank(message = "Programming language is required")
    private String language;
}