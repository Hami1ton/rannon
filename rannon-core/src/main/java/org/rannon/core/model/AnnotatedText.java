package org.rannon.core.model;

public class AnnotatedText {
    private String text;

    private  String tag;

    public String getText() {
        return text;
    }

    public String getTag() {
        return tag;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "AnnotatedDialogue=[text=" + this.text + ", tag=" + this.tag + "]";
    }
}
