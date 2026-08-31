package com.pratham.devpilot.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CodeReviewRequest {

    @NotBlank(message = "Programming language is required")
    private String language;

    @NotBlank(message = "Code is required")
    private String code;
}