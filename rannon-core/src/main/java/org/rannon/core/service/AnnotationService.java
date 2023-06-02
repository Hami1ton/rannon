package org.rannon.core.service;

import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.rannon.core.model.AnnotatedText;
import org.rannon.core.model.RannonText;
import org.rannon.core.RuleStringBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class AnnotationService {

    public List<AnnotatedText> annotate(List<RannonText> rannonTexts) throws IOException, URISyntaxException {

        // result data
        List<AnnotatedText> annotatedTexts = new ArrayList<>();

        // set up drl
        String ruleString = RuleStringBuilder.createRuleFromFile();
        KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder();

        kb.add(ResourceFactory.newByteArrayResource(ruleString.getBytes("utf-8")), ResourceType.DRL);
        InternalKnowledgeBase kBase = KnowledgeBaseFactory.newKnowledgeBase();
        kBase.addPackages(kb.getKnowledgePackages());

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

}

