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
public class DebugResponse {

    private String errorType;

    private String rootCause;

    private String problematicCode;

    private String explanation;

    private String fix;

    private String correctedCode;

    private List<String> debuggingSteps;
}