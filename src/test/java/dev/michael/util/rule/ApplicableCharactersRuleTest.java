package dev.michael.util.rule;

import dev.michael.core.enums.ApplicableCharactersEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApplicableCharactersRuleTest {

    @Test
    void isValid_Digital_Return_False() {
        assertFalse(new ApplicableCharactersRule(ApplicableCharactersEnum.Digits).validationValue("123ab23ab").isValid());
        assertFalse(new ApplicableCharactersRule(ApplicableCharactersEnum.Digits).validationValue("ab123").isValid());
        assertFalse(new ApplicableCharactersRule(ApplicableCharactersEnum.Digits).validationValue("a123").isValid());
        assertFalse(new ApplicableCharactersRule(ApplicableCharactersEnum.Digits).validationValue("a1").isValid());
    }

    @Test
    void isValid_Digital_Return_True() {
        assertTrue(new ApplicableCharactersRule(ApplicableCharactersEnum.Digits).validationValue("1234").isValid());
        assertTrue(new ApplicableCharactersRule(ApplicableCharactersEnum.Digits).validationValue("11").isValid());
    }

    @Test
    void isValid_LowerCase_Return_False() {
        assertFalse(new ApplicableCharactersRule(ApplicableCharactersEnum.LowerCase).validationValue("ABcDEF").isValid());
        assertFalse(new ApplicableCharactersRule(ApplicableCharactersEnum.LowerCase).validationValue("AABBCVCD").isValid());
        assertFalse(new ApplicableCharactersRule(ApplicableCharactersEnum.LowerCase).validationValue("123ab23ab").isValid());
        assertFalse(new ApplicableCharactersRule(ApplicableCharactersEnum.LowerCase).validationValue("a1").isValid());
    }

    @Test
    void isValid_LowerCase_Return_True() {
        assertTrue(new ApplicableCharactersRule(ApplicableCharactersEnum.LowerCase).validationValue("asdasdqw").isValid());
        assertTrue(new ApplicableCharactersRule(ApplicableCharactersEnum.LowerCase).validationValue("xcvxcved").isValid());
        assertTrue(new ApplicableCharactersRule(ApplicableCharactersEnum.LowerCase).validationValue("aa").isValid());
    }

    @Test
    void isValid_DigitalAndLowerCase_Return_False() {
        assertFalse(new ApplicableCharactersRule(ApplicableCharactersEnum.LowerCase, ApplicableCharactersEnum.Digits).validationValue("abc123中文").isValid());
        assertFalse(new ApplicableCharactersRule(ApplicableCharactersEnum.LowerCase, ApplicableCharactersEnum.Digits).validationValue("a").isValid());
        assertFalse(new ApplicableCharactersRule(ApplicableCharactersEnum.LowerCase, ApplicableCharactersEnum.Digits).validationValue("123").isValid());
        assertFalse(new ApplicableCharactersRule(ApplicableCharactersEnum.LowerCase, ApplicableCharactersEnum.Digits).validationValue("~#abc123").isValid());
    }

    @Test
    void isValid_DigitalAndLowerCase_Return_True() {
        assertTrue(new ApplicableCharactersRule(ApplicableCharactersEnum.LowerCase, ApplicableCharactersEnum.Digits).validationValue("abc123").isValid());
        assertTrue(new ApplicableCharactersRule(ApplicableCharactersEnum.LowerCase, ApplicableCharactersEnum.Digits).validationValue("a123456").isValid());
        assertTrue(new ApplicableCharactersRule(ApplicableCharactersEnum.LowerCase, ApplicableCharactersEnum.Digits).validationValue("a1b2c3d4ef6").isValid());
    }
}