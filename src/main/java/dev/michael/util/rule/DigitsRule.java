package dev.michael.util.rule;

public class DigitsRule implements ValidationRule {
    private static final int CHAR_ZERO = 48;
    private static final int CHAR_NINE = 57;

    @Override
    public ValidationResult validationValue(String value) {
        if (!RuleUtils.isContainsRangeChar(value, CHAR_ZERO, CHAR_NINE)) {
            return new ValidationResult(false, "is not contains numerical digits");
        }
        return new ValidationResult(true);
    }

}
