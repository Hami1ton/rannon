package org.rannon.core.service;

import org.rannon.core.model.TextMatchTagRule;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class RuleStringBuilder {

    static String buildRuleString(List<TextMatchTagRule> textMatchTagRules) throws URISyntaxException, IOException {
        StringBuilder sb = new StringBuilder("");
        sb.append(RuleStringBuilder.buildImportSection());
        sb.append("\n");

        int counta = 0;
        for(TextMatchTagRule textMatchTagRule : textMatchTagRules) {
            sb.append(RuleStringBuilder.buildRuleSection(textMatchTagRule, counta));
            sb.append("\n");
            counta += 1;
        }

        return sb.toString();
    }

    static String buildImportSection() throws URISyntaxException, IOException {
        Path file = Paths.get(RuleStringBuilder.class.getClassLoader().getResource("ImportSectionTemplate.drl").toURI());
        String text = Files.readString(file);
        return text;
    }

    static String buildRuleSection(TextMatchTagRule textMatchTagRule, int ruleIndex) throws URISyntaxException, IOException {
        Path file = Paths.get(RuleStringBuilder.class.getClassLoader().getResource("RuleSectionTemplate.drl").toURI());
        String text = Files.readString(file);

        text = text.replace("TEMPLATE_INDEX", String.valueOf(ruleIndex));
        text = text.replace("TEMPLATE_MATCH_TEXT", textMatchTagRule.matchText());
        text = text.replace("TEMPLATE_TAG", textMatchTagRule.tag());
        return text;
    }
}
