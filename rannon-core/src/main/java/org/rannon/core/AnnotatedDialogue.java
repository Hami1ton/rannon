package org.rannon.core;

public class AnnotatedDialogue {
    private String dialogue;

    private  String tag;

    public String getDialogue() {
        return dialogue;
    }

    public String getTag() {
        return tag;
    }

    public void setDialogue(String dialogue) {
        this.dialogue = dialogue;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "AnnotatedDialogue=[dialogue=" + this.dialogue + ", tag=" + this.tag + "]";
    }
}
