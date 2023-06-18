package org.rannon.core.service;

import org.drools.kiesession.rulebase.InternalKnowledgeBase;
import org.drools.kiesession.rulebase.KnowledgeBaseFactory;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.rannon.core.AnnotationService;
import org.rannon.core.model.AnnotatedText;
import org.rannon.core.model.RannonText;
import org.rannon.core.model.Tag;
import org.rannon.core.model.TextMatchTagRule;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class AnnotationServiceImpl implements AnnotationService {

    public InternalKnowledgeBase setUpRuleEngine(List<TextMatchTagRule> textMatchTagRules) {
        InternalKnowledgeBase kBase = null;
        try {
            String ruleString = RuleStringBuilder.buildRuleString(textMatchTagRules);
            KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder();

            kb.add(ResourceFactory.newByteArrayResource(ruleString.getBytes(StandardCharsets.UTF_8)), ResourceType.DRL);
            kBase = KnowledgeBaseFactory.newKnowledgeBase();
            kBase.addPackages(kb.getKnowledgePackages());
        } catch (URISyntaxException | IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return kBase;
    }

    @Override
    public List<AnnotatedText> annotate(List<RannonText> rannonTexts
            , List<TextMatchTagRule> textMatchTagRules) {
        // result data
        List<AnnotatedText> annotatedTexts = new ArrayList<>();

        // annotate
        InternalKnowledgeBase kieBase = setUpRuleEngine(textMatchTagRules);
        for(RannonText rannonText : rannonTexts) {
            // annotate
            AnnotatedText at = annotateSingleText(kieBase, rannonText);
            annotatedTexts.add(at);
        }

        return annotatedTexts;
    }

    AnnotatedText annotateSingleText(InternalKnowledgeBase kieBase, RannonText rannonText) {

        KieSession kieSession = kieBase.newKieSession();
        List<Tag> tags = new ArrayList<>();
        // execute rule
        kieSession.insert(tags);
        kieSession.insert(rannonText);
        kieSession.fireAllRules();
        kieSession.dispose();

        return AnnotatedText.create(rannonText, tags);

    }
}

