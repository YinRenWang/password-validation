package dev.michael;

import dev.michael.core.enums.ApplicableCharactersEnum;
import dev.michael.core.model.ValidationRequest;
import dev.michael.core.service.ValidationServiceImpl;
import dev.michael.util.rule.ApplicableCharactersRule;
import dev.michael.util.rule.LengthRule;
import dev.michael.util.rule.SequenceNotRepeatRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class PasswordValidationApplicationTests {
    @Test
    void contextLoads() {
    }
}
