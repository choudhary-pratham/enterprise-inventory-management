package com.pratham.devpilot.service.impl;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pratham.devpilot.dto.request.CodeAnalysisRequest;
import com.pratham.devpilot.dto.response.CodeAnalysisResponse;
import com.pratham.devpilot.service.CodeAnalysisService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CodeAnalysisServiceImpl implements CodeAnalysisService {

        private final ChatClient chatClient;
        private final ObjectMapper objectMapper;

        @Override
        public CodeAnalysisResponse analyze(CodeAnalysisRequest request) {

                String response = chatClient
                                .prompt()
                                .system("""
                                                You are DevPilot, an expert software engineering assistant.

                                                Analyze the provided programming problem.

                                                Return ONLY valid JSON.

                                                The JSON must contain exactly these fields:
                                                {
                                                  "summary": "Short summary of the problem",
                                                  "rootCause": "Root cause of the problem",
                                                  "severity": "LOW, MEDIUM, HIGH, or CRITICAL",
                                                  "solution": "Recommended solution",
                                                  "suggestedCode": "Corrected code when applicable",
                                                  "bestPractices": ["practice 1", "practice 2"]
                                                }

                                                Rules:
                                                - Keep each explanation concise.
                                                - Do not use Markdown.
                                                - Do not wrap the JSON in ```json.
                                                - Do not add any text before or after the JSON.
                                                """)
                                .user("""
                                                Programming Language: %s

                                                Code / Error:
                                                %s
                                                """.formatted(
                                                request.getLanguage(),
                                                request.getContent()))
                                .call()
                                .content();

                try {
                        return objectMapper.readValue(
                                        response,
                                        CodeAnalysisResponse.class);
                } catch (Exception e) {
                        throw new RuntimeException(
                                        "Failed to parse AI response",
                                        e);
                }
        }
}