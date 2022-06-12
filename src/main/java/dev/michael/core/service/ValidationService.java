package dev.michael.core.service;

import dev.michael.core.model.ValidationRequest;
import dev.michael.core.model.ValidationResponse;

public interface ValidationService {
    ValidationResponse validation(ValidationRequest request);
}
