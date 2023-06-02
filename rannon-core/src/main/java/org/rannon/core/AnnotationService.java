package org.rannon.core;

import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class AnnotationService {
//    KieServices ks = KieServices.Factory.get();
//
//    KieContainer kieContainer = ks.getKieClasspathContainer();

//    StatelessKieSession kieSession = kieContainer.newStatelessKieSession();

    public List<AnnotatedDialogue> annotate(List<Dialogue> dialogues) throws IOException, URISyntaxException {

        // result data
        List<AnnotatedDialogue> annotatedDialogues = new ArrayList<>();

        // set up drl
        String ruleString = RuleStringBuilder.createRuleFromFile();
        KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder();

        kb.add(ResourceFactory.newByteArrayResource(ruleString.getBytes("utf-8")), ResourceType.DRL);
        InternalKnowledgeBase kBase = KnowledgeBaseFactory.newKnowledgeBase();
        kBase.addPackages(kb.getKnowledgePackages());

        // execute rule
        KieSession kSession = kBase.newKieSession();
        kSession.insert(annotatedDialogues);
        for(Dialogue dialogue : dialogues) {
            kSession.insert(dialogue);
        }
        kSession.fireAllRules();
        kSession.dispose();

        return annotatedDialogues;
    }

//    public List<AnnotatedDialogue> annotate_bk(List<Dialogue> dialogues) {
//
//        List<AnnotatedDialogue> annotatedDialogues = new ArrayList<>();
//        Command cmd_1 = CommandFactory.newInsertElements(Arrays.asList(annotatedDialogues));
//        Command cmd_2 = CommandFactory.newInsertElements(dialogues);
//        // execute
//        kieSession.execute(CommandFactory.newBatchExecution(Arrays.asList(cmd_1, cmd_2)));
//
//        return annotatedDialogues;
//    }

}

