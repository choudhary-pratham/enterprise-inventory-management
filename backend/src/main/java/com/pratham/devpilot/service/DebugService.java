package com.pratham.devpilot.service;

import com.pratham.devpilot.dto.request.DebugRequest;
import com.pratham.devpilot.dto.response.DebugResponse;

public interface DebugService {

    DebugResponse debug(DebugRequest request);
}