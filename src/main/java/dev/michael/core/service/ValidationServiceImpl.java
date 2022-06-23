package dev.michael.core.service;

import dev.michael.core.model.ValidationRequest;
import dev.michael.core.model.ValidationResponse;
import dev.michael.util.rule.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ValidationServiceImpl implements ValidationService {

    private List<ValidationRule> validationRules;

    @Autowired
    private LengthRule lengthRule;
    @Autowired
    private ApplicableCharactersRule applicableCharactersRule;
    @Autowired
    private SequenceNotRepeatRule SequenceNotRepeatRule;

    @Override
    public ValidationResponse validation(ValidationRequest request) {

        // Request params check
        if (request == null || request.getPassword() == null || request.getPassword().trim().isEmpty())
            return new ValidationResponse(false, Arrays.asList("password is null or Empty"));
        // validationRules check
        List<String> errorMsgs = new ArrayList<>();
        for (ValidationRule validationRule : this.getValidationRules()) {
            ValidationResult validationResult = validationRule.validationValue(request.getPassword());
            if (!validationResult.isValid()) {
                errorMsgs.add(validationResult.getErrorMsg());
            }
        }
        if (!errorMsgs.isEmpty())
            return new ValidationResponse(false, errorMsgs);
        return new ValidationResponse(true);
    }

    private List<ValidationRule> getValidationRules() {
        if (validationRules == null) {
            validationRules = new ArrayList<>();
            validationRules.add(lengthRule);
            validationRules.add(applicableCharactersRule);
            validationRules.add(SequenceNotRepeatRule);
        }
        return validationRules;
    }
}
