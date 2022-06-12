package dev.michael.util.rule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

public class DigitsRuleTest {

    @Test
    void isValid_NotContainsDigits_Return_False() {
        assertFalse(new DigitsRule().validationValue("p").isValid());
        assertFalse(new DigitsRule().validationValue("AA").isValid());
        assertFalse(new DigitsRule().validationValue("中文字測試").isValid());
        assertFalse(new DigitsRule().validationValue("ijrefrcw").isValid());
        assertFalse(new DigitsRule().validationValue("MNDSCPPW").isValid());
        assertFalse(new DigitsRule().validationValue("K<S@KSR'").isValid());
    }

    @Test
    void isValid_ContainsDigits_Return_True() {
        assertTrue(new DigitsRule().validationValue("1").isValid());
        assertTrue(new DigitsRule().validationValue("11").isValid());
        assertTrue(new DigitsRule().validationValue("z[:*/T2,").isValid());
        assertTrue(new DigitsRule().validationValue("bekU2G5r").isValid());
        assertTrue(new DigitsRule().validationValue(".g%cuj8v'").isValid());
    }
}