package li.earth.urchin.twic.asm;

public class TestStaticInterfaceImpl implements TestStaticInterface {

    private final String value;

    public TestStaticInterfaceImpl(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

}
