package dev.michael.util.rule;

public class LowerCaseLetterRule implements ValidationRule {

    private static final int CHAR_LOWER_A = 97;
    private static final int CHAR_LOWER_Z = 122;

    @Override
    public ValidationResult validationValue(String value) {

        if (!RuleUtils.isContainsRangeChar(value, CHAR_LOWER_A, CHAR_LOWER_Z))
            return new ValidationResult(false, "is not contains lowercase letters");
        return new ValidationResult(true);
    }
}
