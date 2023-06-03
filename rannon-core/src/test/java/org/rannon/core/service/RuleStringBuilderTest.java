package org.rannon.core.service;

import org.junit.jupiter.api.Test;
import org.rannon.core.model.TextMatchTagRule;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RuleStringBuilderTest {

    @Test
    void test() {

        String ruleString = RuleStringBuilder.buildRuleString();

        assertEquals("", ruleString);
    }

    @Test
    void test_ImportSectionTemplate() throws URISyntaxException, IOException {

        String ruleString = RuleStringBuilder.buildImportSection();

        Path file = Paths.get(RuleStringBuilder.class.getClassLoader().getResource("ImportSectionTemplateTest.drl").toURI());
        String text = Files.readString(file);

        assertEquals(text, ruleString);
    }

    @Test
    void test_RuleSectionTemplate() throws URISyntaxException, IOException {

        String ruleString = RuleStringBuilder.buildRuleSection(new TextMatchTagRule("天気", "天気予報タグ"));

        Path file = Paths.get(RuleStringBuilder.class.getClassLoader().getResource("RuleSectionTemplateTest.drl").toURI());
        String text = Files.readString(file);

        assertEquals(text, ruleString);
    }

}
