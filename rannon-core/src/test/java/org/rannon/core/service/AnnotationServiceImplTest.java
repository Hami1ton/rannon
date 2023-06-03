package org.rannon.core.service;

import org.junit.jupiter.api.Test;
import org.rannon.core.AnnotationService;
import org.rannon.core.model.AnnotatedText;
import org.rannon.core.model.RannonText;
import org.rannon.core.model.TextMatchTagRule;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnnotationServiceImplTest {

    @Test
    void test_annotate_単数ワードのタグ付けのテスト() {
        // set up
        List<AnnotatedText> expect = new ArrayList<>();
        AnnotatedText expectAnnotatedText = new AnnotatedText();
        expectAnnotatedText.setText("今日の天気は晴れ");
        expectAnnotatedText.setTag("天気予報");
        expect.add(expectAnnotatedText);

        // execute
        AnnotationService service = new AnnotationServiceImpl();
        List<RannonText> rannonTexts = new ArrayList<>();
        rannonTexts.add(new RannonText("今日の天気は晴れ"));
        List<TextMatchTagRule> textMatchTagRules = new ArrayList<>();
        textMatchTagRules.add(new TextMatchTagRule("天気", "天気予報"));
        List<AnnotatedText> annotatedTexts
                = service.annotate(rannonTexts, textMatchTagRules);

        // assert
        assertEquals(expect, annotatedTexts);
    }

    @Test
    void test_annotate_複数ワードのタグ付けのテスト() {
        // set up
        List<AnnotatedText> expect = new ArrayList<>();
        AnnotatedText expectAnnotatedText1 = new AnnotatedText();
        expectAnnotatedText1.setText("今日の天気は晴れ");
        expectAnnotatedText1.setTag("天気予報");
        AnnotatedText expectAnnotatedText2 = new AnnotatedText();
        expectAnnotatedText2.setText("今日は晴れ");
        expectAnnotatedText2.setTag("");
        expect.add(expectAnnotatedText1);
        expect.add(expectAnnotatedText2);

        // execute
        AnnotationService service = new AnnotationServiceImpl();
        List<RannonText> rannonTexts = new ArrayList<>();
        rannonTexts.add(new RannonText("今日の天気は晴れ"));
        rannonTexts.add(new RannonText("今日は晴れ"));
        List<TextMatchTagRule> textMatchTagRules = new ArrayList<>();
        textMatchTagRules.add(new TextMatchTagRule("天気", "天気予報"));
        List<AnnotatedText> annotatedTexts
                = service.annotate(rannonTexts, textMatchTagRules);

        // assert
        assertEquals(expect, annotatedTexts);
    }
}
