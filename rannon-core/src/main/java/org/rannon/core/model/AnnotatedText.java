package org.rannon.core.model;

import lombok.*;

@EqualsAndHashCode
@Setter
@Getter
public class AnnotatedText {
    public String text;

    public String tag;

    @Override
    public String toString() {
        return "AnnotatedText=[text=" + this.text + ", tag=" + this.tag + "]";
    }
}
