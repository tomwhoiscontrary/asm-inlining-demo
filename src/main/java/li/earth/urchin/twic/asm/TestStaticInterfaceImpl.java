package li.earth.urchin.twic.asm;

public class TestStaticInterfaceImpl implements TestStaticInterface {

    private final String value = "what the hell";

    @Override
    public String getValue() {
        return value;
    }

}
