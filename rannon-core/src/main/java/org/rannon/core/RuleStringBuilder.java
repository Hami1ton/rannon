package org.rannon.core;

public class RuleStringBuilder {

    public static String createDrlMessage() {
        StringBuilder aa = new StringBuilder("");
        aa.append("import java.util.List; \n");
        aa.append("import org.rannon.core.Dialogue;\n");
        aa.append("import org.rannon.core.AnnotatedDialogue;\n");
        aa.append("rule \"天気\"\n");
        aa.append("    when\n");
        aa.append("        $annotatedDialogues : List();\n");
        aa.append("        $dialogue : Dialogue( value.contains(\"天気\")  );\n");
        aa.append("    then\n");
        aa.append("        AnnotatedDialogue annotatedDialogue = new AnnotatedDialogue();\n");
        aa.append("        annotatedDialogue.setDialogue($dialogue.value());\n");
        aa.append("        annotatedDialogue.setTag(\"天気予報タグ\");\n");
        aa.append("        $annotatedDialogues.add(annotatedDialogue);\n");
        aa.append("        System.out.println( \"ルール発火\" );\n");
        aa.append("end\n");

        System.out.println(aa.toString());

        return aa.toString();
    }
}
