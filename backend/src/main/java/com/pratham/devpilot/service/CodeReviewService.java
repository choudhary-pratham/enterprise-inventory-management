package com.pratham.devpilot.service;

import com.pratham.devpilot.dto.request.CodeReviewRequest;
import com.pratham.devpilot.dto.response.CodeReviewResponse;

public interface CodeReviewService {

    CodeReviewResponse review(CodeReviewRequest request);
}