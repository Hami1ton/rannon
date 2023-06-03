package org.rannon.core.service;

import org.rannon.core.model.TextMatchTagRule;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
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
        try (InputStream in = RuleStringBuilder.class.getClassLoader().getResourceAsStream("ImportSectionTemplate.drl")) {
            String text = new String(in.readAllBytes(), StandardCharsets.UTF_8);

            return text;
        }
    }

    static String buildRuleSection(TextMatchTagRule textMatchTagRule, int ruleIndex) throws URISyntaxException, IOException {
        try (InputStream in = RuleStringBuilder.class.getClassLoader().getResourceAsStream("RuleSectionTemplate.drl")) {
            String text = new String(in.readAllBytes(), StandardCharsets.UTF_8);

            text = text.replace("TEMPLATE_INDEX", String.valueOf(ruleIndex));
            text = text.replace("TEMPLATE_MATCH_TEXT", textMatchTagRule.matchText());
            text = text.replace("TEMPLATE_TAG", textMatchTagRule.tag());

            return text;
        }
    }
}
