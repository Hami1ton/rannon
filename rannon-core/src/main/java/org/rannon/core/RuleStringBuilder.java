package org.rannon.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RuleStringBuilder {

    public static String createRule() {
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

    public static String createRuleFromFile() throws IOException, URISyntaxException {
        Path file = Paths.get(RuleStringBuilder.class.getClassLoader().getResource("template.drl").toURI());
        String text = Files.readString(file);
        System.out.println(text);
        return text;
    }
}
