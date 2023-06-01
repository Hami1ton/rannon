package org.rannon.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RuleStringBuilderTest {

    @Test
    void test() {

        String aa = RuleStringBuilder.createDrlMessage();

        assertEquals("import java.util.List;\\r\\nimport org.rannon.core.Dialogue;\\r\\n", aa);
    }

}
