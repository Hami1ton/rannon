package org.rannon.core.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnnotatedTextTest {
    @Test
    void test_equalsのテスト() {
        // set up
        AnnotatedText expect = new AnnotatedText();
        expect.setText("今日の天気は晴れ");
        expect.setTag("天気予報");

        // execute
        AnnotatedText at = new AnnotatedText();
        at.setText("今日の天気は晴れ");
        at.setTag("天気予報");

        // assert
        assertEquals(expect.getText(), at.getText());
        assertEquals(expect.getTag(), at.getTag());
        assertEquals(expect, at);
    }
}
