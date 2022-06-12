package dev.michael.util.rule;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LowerCaseLetterRuleTest {

    @Test
    void isValid_NotContains_LowerCaseLatter_Return_False() {
        assertFalse(new LowerCaseLetterRule().validationValue("A").isValid());
        assertFalse(new LowerCaseLetterRule().validationValue("BB").isValid());
        assertFalse(new LowerCaseLetterRule().validationValue("中文字測試").isValid());
        assertFalse(new LowerCaseLetterRule().validationValue("123").isValid());
        assertFalse(new LowerCaseLetterRule().validationValue("MNDSCPPW").isValid());
        assertFalse(new LowerCaseLetterRule().validationValue("URW<E,~~AUMM").isValid());
    }

    @Test
    void isValid_Contains_LowerCaseLatter_Return_True() {
        assertTrue(new LowerCaseLetterRule().validationValue("a").isValid());
        assertTrue(new LowerCaseLetterRule().validationValue("a1").isValid());
        assertTrue(new LowerCaseLetterRule().validationValue("2bxtPF+hNdS+").isValid());
        assertTrue(new LowerCaseLetterRule().validationValue("AAAAAAAAAAa").isValid());
        assertTrue(new LowerCaseLetterRule().validationValue("6by9rz4f5uuf").isValid());
    }

}