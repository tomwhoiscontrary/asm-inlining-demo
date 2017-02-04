package li.earth.urchin.twic.asm;

import java.util.Objects;

public class TestBean {

    private final String value = Objects.requireNonNull(System.getProperty("java.version"));

    public String getValue() {
        return value;
    }

}
