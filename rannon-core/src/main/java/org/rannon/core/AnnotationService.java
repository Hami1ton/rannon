package org.rannon.core;

import org.rannon.core.model.AnnotatedText;
import org.rannon.core.model.RannonText;
import org.rannon.core.model.TextMatchTagRule;

import java.util.List;

public interface AnnotationService {

    public List<AnnotatedText> annotate(List<RannonText> rannonTexts, List<TextMatchTagRule> textMatchTagRules);
}
