package org.rannon.core;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        AnnotationService service = new AnnotationService();

        List<Dialogue> list = new ArrayList<>();
        Dialogue dialogue = new Dialogue("天気");
        list.add(dialogue);

        // execute
        List<AnnotatedDialogue> result = service.annotate(list);

        System.out.println(result);
    }
}
