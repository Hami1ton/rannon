package org.rannon.core;

import org.kie.api.KieServices;
import org.kie.api.command.Command;
import org.kie.api.runtime.ExecutionResults;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.command.CommandFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnnotationService {
    KieServices ks = KieServices.Factory.get();

    KieContainer kieContainer = ks.getKieClasspathContainer();

    StatelessKieSession kieSession = kieContainer.newStatelessKieSession();

    public List<AnnotatedDialogue> annotate(List<Dialogue> dialogues) {

        List<AnnotatedDialogue> annotatedDialogues = new ArrayList<>();
        Command cmd_1 = CommandFactory.newInsertElements(Arrays.asList(annotatedDialogues));
        Command cmd_2 = CommandFactory.newInsertElements(dialogues);
        // execute
        kieSession.execute(CommandFactory.newBatchExecution(Arrays.asList(cmd_1, cmd_2)));

        return annotatedDialogues;
    }

}

