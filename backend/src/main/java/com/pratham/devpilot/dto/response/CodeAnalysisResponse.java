package com.pratham.devpilot.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodeAnalysisResponse {
    private String summary;

    private String rootCause;

    private String severity;

    private String solution;

    private String suggestedCode;

    private List<String> bestPractices;
}