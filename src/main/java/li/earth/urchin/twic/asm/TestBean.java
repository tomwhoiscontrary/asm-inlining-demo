package li.earth.urchin.twic.asm;

import java.util.Objects;

public class TestBean {

    private final String value = Objects.requireNonNull("what the hell");

    public String getValue() {
        return value;
    }

}
