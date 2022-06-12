package dev.michael.util.rule;

public class LengthRule implements ValidationRule {
    private int min;
    private int max;

    @Override
    public ValidationResult validationValue(String value) {
        if (value.length() < this.min) {
            return new ValidationResult(false, "length is too short at least " + this.min);
        } else if (value.length() > this.max) {
            return new ValidationResult(false, "length is too long at most " + this.max);
        }
        return new ValidationResult(true);
    }

    public LengthRule() {
    }

    public LengthRule(int min, int max) {
        this.min = min;
        this.max = max;
    }
}
