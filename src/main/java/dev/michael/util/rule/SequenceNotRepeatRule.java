package dev.michael.util.rule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SequenceNotRepeatRule implements ValidationRule {

    /**
     * .+ => Any character appear 1's up
     * \1 => matches two consecutive identical characters.(in the java \\ => \)
     */
    private final Pattern REGEXP = Pattern.compile("(.+)\\1");

    @Override
    public ValidationResult validationValue(String value) {
        Matcher matcher = REGEXP.matcher(value);
        if (matcher.find()) {
            return new ValidationResult(false, "contain any sequence of characters immediately followed by the same sequence (" + matcher.group() + ")");
        }
        return new ValidationResult(true);
    }

}
