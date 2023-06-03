package org.rannon.core.service;

import org.junit.jupiter.api.Test;
import org.rannon.core.model.TextMatchTagRule;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RuleStringBuilderTest {


    @Test
    void test_buildRuleString() throws URISyntaxException, IOException {

        List<TextMatchTagRule> textMatchTagRules = new ArrayList<>();
        textMatchTagRules.add(new TextMatchTagRule("天気", "天気予報タグ"));
        textMatchTagRules.add(new TextMatchTagRule("お菓子", "スイーツタグ"));

        String ruleString = RuleStringBuilder.buildRuleString(textMatchTagRules);

        Path file = Paths.get(RuleStringBuilder.class.getClassLoader().getResource("BuildRuleTemplateTest.drl").toURI());
        String text = Files.readString(file);

        assertEquals(text, ruleString);
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

        String ruleString = RuleStringBuilder.buildRuleSection(new TextMatchTagRule("天気", "天気予報タグ"), 0);

        Path file = Paths.get(RuleStringBuilder.class.getClassLoader().getResource("RuleSectionTemplateTest.drl").toURI());
        String text = Files.readString(file);

        assertEquals(text, ruleString);
    }

}
