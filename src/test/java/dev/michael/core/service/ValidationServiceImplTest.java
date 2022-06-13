package dev.michael.core.service;

import dev.michael.core.enums.ApplicableCharactersEnum;
import dev.michael.core.model.ValidationRequest;
import dev.michael.util.rule.ApplicableCharactersRule;
import dev.michael.util.rule.LengthRule;
import dev.michael.util.rule.SequenceNotRepeatRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class ValidationServiceImplTest {

    @Test
    public void isValid_5to12() {
        ValidationServiceImpl validationService = new ValidationServiceImpl(Arrays.asList(new LengthRule(5, 12)));

        Assertions.assertAll(
                () -> Assertions.assertFalse(validationService.validation(null).isValid()),
                () -> Assertions.assertFalse(validationService.validation(new ValidationRequest(null)).isValid()),
                () -> Assertions.assertFalse(validationService.validation(new ValidationRequest("")).isValid()),
                () -> Assertions.assertFalse(validationService.validation(new ValidationRequest("1234")).isValid()),
                () -> Assertions.assertFalse(validationService.validation(new ValidationRequest("123456789abc-def")).isValid()),
                () -> Assertions.assertTrue(validationService.validation(new ValidationRequest("12345")).isValid())
        );
    }

    @Test
    public void isValid_LowerCaseLetterAndDigits() {
        ValidationServiceImpl validationService = new ValidationServiceImpl(Arrays.asList(new ApplicableCharactersRule(ApplicableCharactersEnum.Digits, ApplicableCharactersEnum.LowerCase)));
        Assertions.assertAll(
                () -> Assertions.assertFalse(validationService.validation(null).isValid()),
                () -> Assertions.assertFalse(validationService.validation(new ValidationRequest(null)).isValid()),
                () -> Assertions.assertFalse(validationService.validation(new ValidationRequest("")).isValid()),
                () -> Assertions.assertFalse(validationService.validation(new ValidationRequest("11111")).isValid()),
                () -> Assertions.assertFalse(validationService.validation(new ValidationRequest("array")).isValid()),
                () -> Assertions.assertTrue(validationService.validation(new ValidationRequest("test123")).isValid())
        );
    }

    @Test
    public void isValid_5to12AndLowerCaseLetterAndDigits() {
        ValidationServiceImpl validationService = new ValidationServiceImpl(Arrays.asList(new LengthRule(5, 12), new ApplicableCharactersRule(ApplicableCharactersEnum.Digits, ApplicableCharactersEnum.LowerCase)));
        Assertions.assertAll(
                () -> Assertions.assertFalse(validationService.validation(null).isValid()),
                () -> Assertions.assertFalse(validationService.validation(new ValidationRequest(null)).isValid()),
                () -> Assertions.assertFalse(validationService.validation(new ValidationRequest("")).isValid()),
                () -> Assertions.assertFalse(validationService.validation(new ValidationRequest("12345")).isValid()),
                () -> Assertions.assertFalse(validationService.validation(new ValidationRequest("abide")).isValid()),
                () -> Assertions.assertTrue(validationService.validation(new ValidationRequest("a12345")).isValid())
        );
    }

    @Test
    public void isValid_SequenceNotRepeatRule() {
        ValidationServiceImpl validationService = new ValidationServiceImpl(Arrays.asList(new SequenceNotRepeatRule()));
        Assertions.assertAll(
                () -> Assertions.assertFalse(validationService.validation(null).isValid()),
                () -> Assertions.assertFalse(validationService.validation(new ValidationRequest(null)).isValid()),
                () -> Assertions.assertFalse(validationService.validation(new ValidationRequest("")).isValid()),
                () -> Assertions.assertFalse(validationService.validation(new ValidationRequest("aa")).isValid()),
                () -> Assertions.assertFalse(validationService.validation(new ValidationRequest("egg")).isValid()),
                () -> Assertions.assertFalse(validationService.validation(new ValidationRequest("0abcabc")).isValid()),
                () -> Assertions.assertFalse(validationService.validation(new ValidationRequest("a12323")).isValid()),
                () -> Assertions.assertFalse(validationService.validation(new ValidationRequest("Aab12ab12")).isValid()),
                () -> Assertions.assertFalse(validationService.validation(new ValidationRequest("abcd1234512345xyz")).isValid()),
                () -> Assertions.assertTrue(validationService.validation(new ValidationRequest("a")).isValid()),
                () -> Assertions.assertTrue(validationService.validation(new ValidationRequest("abc")).isValid()),
                () -> Assertions.assertTrue(validationService.validation(new ValidationRequest("abcab")).isValid()),
                () -> Assertions.assertTrue(validationService.validation(new ValidationRequest("abc123abc")).isValid()),
                () -> Assertions.assertTrue(validationService.validation(new ValidationRequest("+d3=zd=e97a")).isValid())
        );
    }

    @Test
    public void isValid_Service_validation() {
        ValidationServiceImpl validationService = new ValidationServiceImpl(Arrays.asList(
                new LengthRule(5, 12),
                new ApplicableCharactersRule(ApplicableCharactersEnum.Digits, ApplicableCharactersEnum.LowerCase),
                new SequenceNotRepeatRule()
        ));
        Assertions.assertAll(
                () -> Assertions.assertFalse(validationService.validation(null).isValid()),
                () -> Assertions.assertFalse(validationService.validation(new ValidationRequest(null)).isValid()),
                () -> Assertions.assertFalse(validationService.validation(new ValidationRequest("")).isValid()),
                () -> Assertions.assertFalse(validationService.validation(new ValidationRequest("abcd")).isValid()),
                () -> Assertions.assertFalse(validationService.validation(new ValidationRequest("1234")).isValid()),
                () -> Assertions.assertFalse(validationService.validation(new ValidationRequest("123456")).isValid()),
                () -> Assertions.assertFalse(validationService.validation(new ValidationRequest("ACABAD")).isValid()),
                () -> Assertions.assertFalse(validationService.validation(new ValidationRequest("pXnEzFcp")).isValid()),
                () -> Assertions.assertFalse(validationService.validation(new ValidationRequest("q9jg2i中")).isValid()),
                () -> Assertions.assertFalse(validationService.validation(new ValidationRequest("123123ab")).isValid()),
                () -> Assertions.assertTrue(validationService.validation(new ValidationRequest("123ab123")).isValid()),
                () -> Assertions.assertTrue(validationService.validation(new ValidationRequest("q9jg2i")).isValid()),
                () -> Assertions.assertTrue(validationService.validation(new ValidationRequest("96mij8ia")).isValid())
        );
    }
}