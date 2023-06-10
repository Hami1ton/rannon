package org.rannon.core.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnnotatedTextTest {
    @Test
    void test_equalsのテスト() {
        // set up
        AnnotatedText expect = new AnnotatedText();
        expect.setText("今日の天気は晴れ");
        expect.setTags(Arrays.asList(new Tag("天気"), new Tag("晴れ")));

        // execute
        AnnotatedText at = new AnnotatedText();
        at.setText("今日の天気は晴れ");
        at.setTags(Arrays.asList(new Tag("天気"), new Tag("晴れ")));

        // assert
        assertEquals(expect, at);
    }
}
