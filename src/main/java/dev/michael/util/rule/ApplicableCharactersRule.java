package dev.michael.util.rule;

import dev.michael.core.enums.ApplicableCharactersEnum;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ApplicableCharactersRule implements ValidationRule {

    private Set<ApplicableCharactersEnum> applicableTypes;

    public ApplicableCharactersRule(ApplicableCharactersEnum... applicableTypes) {
        this.applicableTypes = new HashSet<>(Arrays.asList(applicableTypes));
    }

    @Override
    public ValidationResult validationValue(String value) {
        Set<ApplicableCharactersEnum> passTypes = new HashSet<>();
        int passCount = 0;

        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            for (ApplicableCharactersEnum e : this.applicableTypes) {
                if (c >= e.getMin() && c <= e.getMax()) {
                    passTypes.add(e);
                    passCount++;
                }
            }
        }

        if (value.length() != passCount) {
            return new ValidationResult(false, "contains invalid characters");
        } else if (applicableTypes.size() != passTypes.size()) {
            return new ValidationResult(false, "characters not meet the requirements of the rules not enough "
                    + applicableTypes.stream().filter(e -> !passTypes.contains(e)).map(Enum::name).collect(Collectors.joining(",")));
        }
        return new ValidationResult(true);
    }

}
