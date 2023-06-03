package org.rannon.examples.simpleusecorejar;

import org.rannon.core.AnnotationService;
import org.rannon.core.model.RannonText;
import org.rannon.core.model.TextMatchTagRule;
import org.rannon.core.service.AnnotationServiceImpl;

import java.util.Arrays;

public class Main {

    public static void main(String[] args){

        RannonText rt1 = new RannonText("実験にいく");
        RannonText rt2 = new RannonText("理科の実験室");

        TextMatchTagRule ttr = new TextMatchTagRule("実験", "サイエンス");

        AnnotationService annotationService = new AnnotationServiceImpl();

        System.out.println(annotationService.annotate(Arrays.asList(rt1, rt2), Arrays.asList(ttr)));
    }
}
