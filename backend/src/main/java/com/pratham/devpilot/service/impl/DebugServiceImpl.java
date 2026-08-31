package com.pratham.devpilot.service.impl;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pratham.devpilot.dto.request.DebugRequest;
import com.pratham.devpilot.dto.response.DebugResponse;
import com.pratham.devpilot.service.DebugService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DebugServiceImpl implements DebugService {

    private final ChatClient chatClient;
    private final ObjectMapper objectMapper;

    @Override
    public DebugResponse debug(DebugRequest request) {

        String response = chatClient
                .prompt()
                .system("""
                        You are DevPilot, an expert software debugging assistant.

                        Analyze the provided code and error/stack trace.

                        Identify the most likely root cause and explain how to fix it.

                        Return ONLY valid JSON.

                        The JSON must contain exactly these fields:

                        {
                          "errorType": "Type of exception or error",
                          "rootCause": "Root cause of the error",
                          "problematicCode": "Relevant problematic code",
                          "explanation": "Why the error occurs",
                          "fix": "Recommended fix",
                          "correctedCode": "Corrected version of the code",
                          "debuggingSteps": ["step 1", "step 2", "step 3"]
                        }

                        Rules:
                        - Be technically accurate.
                        - Base your analysis on the provided code and error.
                        - Do not invent information that is not supported by the input.
                        - Keep the explanation concise.
                        - Do not use Markdown.
                        - Do not wrap the JSON in ```json.
                        - Do not add text outside the JSON.
                        """)
                .user("""
                        Programming Language:
                        %s

                        Code:
                        %s

                        Error / Stack Trace:
                        %s
                        """.formatted(
                        request.getLanguage(),
                        request.getCode(),
                        request.getError()))
                .call()
                .content();

        try {
            return objectMapper.readValue(
                    response,
                    DebugResponse.class);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to parse AI debugging response",
                    e);
        }
    }
}