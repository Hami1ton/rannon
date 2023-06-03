package org.rannon.core.service;

import org.rannon.core.model.TextMatchTagRule;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RuleStringBuilder {

    static String buildRuleString() {
        StringBuilder sb = new StringBuilder("");
        sb.append("");

        return "";
    }

    static String buildImportSection() throws URISyntaxException, IOException {
        Path file = Paths.get(RuleStringBuilder.class.getClassLoader().getResource("ImportSectionTemplate.drl").toURI());
        String text = Files.readString(file);
        return text;
    }

    static String buildRuleSection(TextMatchTagRule textMatchTagRule) throws URISyntaxException, IOException {
        Path file = Paths.get(RuleStringBuilder.class.getClassLoader().getResource("RuleSectionTemplate.drl").toURI());
        String text = Files.readString(file);

        text = text.replace("TEMPLATE_MATCH_TEXT", textMatchTagRule.matchText());
        text = text.replace("TEMPLATE_TAG", textMatchTagRule.tag());
        return text;
    }

    static String createRuleFromFile() throws IOException, URISyntaxException {
        Path file = Paths.get(RuleStringBuilder.class.getClassLoader().getResource("template.drl").toURI());
        String text = Files.readString(file);
        System.out.println(text);
        return text;
    }
}
