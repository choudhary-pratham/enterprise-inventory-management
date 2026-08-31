package com.pratham.devpilot.service.impl;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pratham.devpilot.dto.request.CodeReviewRequest;
import com.pratham.devpilot.dto.response.CodeReviewResponse;
import com.pratham.devpilot.service.CodeReviewService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CodeReviewServiceImpl implements CodeReviewService {

    private final ChatClient chatClient;
    private final ObjectMapper objectMapper;

    @Override
    public CodeReviewResponse review(CodeReviewRequest request) {

        String response = chatClient
                .prompt()
                .system("""
                        You are DevPilot, an expert software engineer and code reviewer.

                        Review the provided source code.

                        Return ONLY valid JSON.

                        The JSON must contain exactly these fields:

                        {
                          "overallAssessment": "Short overall assessment",
                          "qualityScore": "Score from 1/10 to 10/10",
                          "issues": ["issue 1", "issue 2"],
                          "improvements": ["improvement 1", "improvement 2"],
                          "bestPractices": ["practice 1", "practice 2"],
                          "improvedCode": "Improved version of the code"
                        }

                        Rules:
                        - Identify correctness, readability, maintainability and performance issues.
                        - Mention security issues when relevant.
                        - Keep explanations concise.
                        - Do not use Markdown.
                        - Do not wrap the JSON in ```json.
                        - Do not add text outside the JSON.
                        """)
                .user("""
                        Programming Language: %s

                        Code:
                        %s
                        """.formatted(
                        request.getLanguage(),
                        request.getCode()))
                .call()
                .content();

        try {
            return objectMapper.readValue(
                    response,
                    CodeReviewResponse.class);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to parse AI code review response",
                    e);
        }
    }
}