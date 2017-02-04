package li.earth.urchin.twic.asm;

import java.util.Objects;

public class TestDynamicInterfaceImpl implements TestDynamicInterface {

    private final String value = Magic.bless("what the hell");

    @Override
    public String getValue() {
        return value;
    }

}
