package li.earth.urchin.twic.asm;

import java.util.Objects;

public class TestDynamicInterfaceImpl implements TestDynamicInterface {

    private final String value = Objects.requireNonNull("what the hell");

    @Override
    public String getValue() {
        return value;
    }

}
