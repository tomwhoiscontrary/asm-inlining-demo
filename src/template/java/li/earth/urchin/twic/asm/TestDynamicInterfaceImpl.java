package li.earth.urchin.twic.asm;

public class TestDynamicInterfaceImpl implements TestDynamicInterface {

    private final String value = "what the hell";

    @Override
    public String getValue() {
        return value;
    }

}
