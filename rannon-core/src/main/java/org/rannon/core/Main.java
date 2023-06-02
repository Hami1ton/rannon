package org.rannon.core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.rannon.core.RuleStringBuilder.createRuleFromFile;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        AnnotationService service = new AnnotationService();

//        createRuleFromFile();

        List<Dialogue> list = new ArrayList<>();
        Dialogue dialogue_1 = new Dialogue("天気がいい");
        Dialogue dialogue_2 = new Dialogue("今日の天気は晴れ");
        Dialogue dialogue_3 = new Dialogue("明日は雨");
        list.addAll(Arrays.asList(dialogue_1, dialogue_2, dialogue_3));

        // execute
        List<AnnotatedDialogue> result = service.annotate(list);

        System.out.println(result);
    }
}
