package dev.michael.util.rule;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SequenceNotRepeatRuleTest {

    @Test
    void isValid_SequenceRepeat_Return_False() {
        assertFalse(new SequenceNotRepeatRule().validationValue("123ab23ab").isValid());
        assertFalse(new SequenceNotRepeatRule().validationValue("12341234ab").isValid());
        assertFalse(new SequenceNotRepeatRule().validationValue("1234abab12").isValid());
        assertFalse(new SequenceNotRepeatRule().validationValue("egg").isValid());
    }

    @Test
    void isValid_SequenceNotRepeat_Return_True() {
        assertTrue(new SequenceNotRepeatRule().validationValue("abc123abc").isValid());
        assertTrue(new SequenceNotRepeatRule().validationValue("abcab").isValid());
    }

}