package li.earth.urchin.twic.asm;

public class TestDynamicInterfaceImpl implements TestDynamicInterface {

    private final String value;

    public TestDynamicInterfaceImpl(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

}
