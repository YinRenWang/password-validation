package dev.michael.util.rule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LengthRuleTest {

    private LengthRule lengthRule;
    private LengthRule equalRule;

    @BeforeEach
    public void init() {
        lengthRule = new LengthRule(5, 12);
        equalRule = new LengthRule(5, 5);
    }

    @Test
    void isValid_tooShort_Return_False() {
        assertFalse(lengthRule.validationValue("1").isValid());
        assertFalse(lengthRule.validationValue("42").isValid());
        assertFalse(lengthRule.validationValue("1ab").isValid());
        assertFalse(lengthRule.validationValue("1a5b").isValid());
    }

    @Test
    void isValid_tooLong_Return_False() {
        assertFalse(lengthRule.validationValue("ffmfg2V48p6RBbex").isValid());
    }

    @Test
    void isValid_Return_True() {
        assertTrue(lengthRule.validationValue("w39p3").isValid());
        assertTrue(lengthRule.validationValue("mgmTp7Cf4F").isValid());
        assertTrue(lengthRule.validationValue("WHmDRAHzppgk").isValid());
    }

    @Test
    void isValid_equalsRange_True() {
        assertTrue(equalRule.validationValue("2mKeK").isValid());
    }
}