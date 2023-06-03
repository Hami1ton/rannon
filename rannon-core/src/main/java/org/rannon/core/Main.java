package org.rannon.core;

import org.rannon.core.model.AnnotatedText;
import org.rannon.core.model.RannonText;
import org.rannon.core.service.AnnotationServiceImpl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        AnnotationServiceImpl service = new AnnotationServiceImpl();
        List<RannonText> list = new ArrayList<>();
        RannonText rannonText_1 = new RannonText("天気がいい");
        RannonText rannonText_2 = new RannonText("今日の天気は晴れ");
        RannonText rannonText_3 = new RannonText("明日は雨");
        list.addAll(Arrays.asList(rannonText_1, rannonText_2, rannonText_3));

        // execute
        List<AnnotatedText> result = service.annotate(list);

        System.out.println(result);
    }
}
