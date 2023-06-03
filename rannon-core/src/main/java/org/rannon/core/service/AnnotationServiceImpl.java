package org.rannon.core.service;

import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.rannon.core.AnnotationService;
import org.rannon.core.model.AnnotatedText;
import org.rannon.core.model.RannonText;
import org.rannon.core.model.TextMatchTagRule;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class AnnotationServiceImpl implements AnnotationService {

    @Override
    public List<AnnotatedText> annotate(List<RannonText> rannonTexts
            , List<TextMatchTagRule> textMatchTagRules) {
        // result data
        List<AnnotatedText> annotatedTexts = new ArrayList<>();

        // set up knowledgeBase
        InternalKnowledgeBase kBase = setUpKnowledgeBase(textMatchTagRules);

        // execute rule
        KieSession kSession = kBase.newKieSession();
        kSession.insert(annotatedTexts);
        for(RannonText rannonText : rannonTexts) {
            kSession.insert(rannonText);
        }
        kSession.fireAllRules();
        kSession.dispose();

        return annotatedTexts;
    }

    private InternalKnowledgeBase setUpKnowledgeBase(List<TextMatchTagRule> textMatchTagRules) {
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
}

