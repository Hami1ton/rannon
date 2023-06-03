package org.rannon.core.model;

import lombok.*;

@EqualsAndHashCode
public class AnnotatedText {
    public String text;

    public String tag;

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
        return "AnnotatedText=[text=" + this.text + ", tag=" + this.tag + "]";
    }
}
