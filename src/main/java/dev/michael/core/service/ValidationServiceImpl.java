package dev.michael.core.service;

import dev.michael.core.model.ValidationRequest;
import dev.michael.core.model.ValidationResponse;
import dev.michael.util.rule.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ValidationServiceImpl implements ValidationService {

    private List<ValidationRule> validationRules;

    @Override
    public ValidationResponse validation(ValidationRequest request) {
        // Request params check
        if (request == null || request.getPassword() == null || request.getPassword().isEmpty())
            return new ValidationResponse(false, Arrays.asList("password is null or Empty"));
        // validationRules check
        List<String> errorMsgs = new ArrayList<>();
        if (this.validationRules != null && !this.validationRules.isEmpty()) {
            for (ValidationRule validationRule : this.validationRules) {
                ValidationResult validationResult = validationRule.validationValue(request.getPassword());
                if (!validationResult.isValid()) {
                    errorMsgs.add(validationResult.getErrorMsg());
                }
            }
        }
        if (!errorMsgs.isEmpty())
            return new ValidationResponse(false, errorMsgs);
        return new ValidationResponse(true);
    }

    public ValidationServiceImpl() {
        this.validationRules = Arrays.asList(
                new LengthRule(5, 12),
                new DigitsRule(),
                new LowerCaseLetterRule(),
                new SequenceNotRepeatRule()
        );
    }

    public ValidationServiceImpl(List<ValidationRule> validationRules) {
        this.validationRules = validationRules;
    }
}
