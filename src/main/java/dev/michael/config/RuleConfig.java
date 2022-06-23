package dev.michael.config;

import dev.michael.core.enums.ApplicableCharactersEnum;
import dev.michael.util.rule.ApplicableCharactersRule;
import dev.michael.util.rule.LengthRule;
import dev.michael.util.rule.SequenceNotRepeatRule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RuleConfig {

    @Bean
    public LengthRule lengthRule(@Value("${rule.length.min:5}") int min, @Value("${rule.length.max:12}") int max) {
        return new LengthRule(min, max);
    }

    @Bean
    public ApplicableCharactersRule applicableCharactersRule() {
        return new ApplicableCharactersRule(ApplicableCharactersEnum.Digits, ApplicableCharactersEnum.LowerCase);
    }

    @Bean
    public SequenceNotRepeatRule sequenceNotRepeatRule() {
        return new SequenceNotRepeatRule();
    }
}
