package com.pratham.devpilot.service;

import com.pratham.devpilot.dto.request.CodeAnalysisRequest;
import com.pratham.devpilot.dto.response.CodeAnalysisResponse;

public interface CodeAnalysisService {

    CodeAnalysisResponse analyze(CodeAnalysisRequest request);
}