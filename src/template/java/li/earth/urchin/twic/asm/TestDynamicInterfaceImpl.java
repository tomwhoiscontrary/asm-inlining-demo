package li.earth.urchin.twic.asm;

import java.util.Objects;

public class TestDynamicInterfaceImpl implements TestDynamicInterface {

    private final String value = Objects.requireNonNull(System.getProperty("java.version"));

    @Override
    public String getValue() {
        return value;
    }

}
