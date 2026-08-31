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
public class CodeReviewResponse {

    private String overallAssessment;

    private String qualityScore;

    private List<String> issues;

    private List<String> improvements;

    private List<String> bestPractices;

    private String improvedCode;
}