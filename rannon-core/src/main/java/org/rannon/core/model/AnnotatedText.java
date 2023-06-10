package org.rannon.core.model;

import lombok.*;

import java.util.List;

@EqualsAndHashCode
@Setter
@Getter
public class AnnotatedText {
    public String text;

    public List<Tag> tags;

    @Override
    public String toString() {
        return "AnnotatedText=[text=" + this.text + ", tags=" + this.tags + "]";
    }


    public static AnnotatedText create(RannonText text, List<Tag> tags) {
        AnnotatedText at = new AnnotatedText();
        at.setText(text.value());
        at.setTags(tags);
        return at;
    }
}
