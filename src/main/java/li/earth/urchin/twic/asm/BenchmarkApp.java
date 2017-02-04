package li.earth.urchin.twic.asm;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;

@Fork(value = 1)
@Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 20, time = 50, timeUnit = TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class BenchmarkApp {

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(BenchmarkApp.class.getName() + ".*")
                .shouldFailOnError(true)
                .build();

        new Runner(options).run();
    }

    @State(Scope.Benchmark)
    public static class Fixtures {

        public final TestBean bean = new TestBean();

        public final TestStaticInterface staticInterfaceImpl = new TestStaticInterfaceImpl();

        public final TestDynamicInterface dynamicInterfaceImpl;

        {
            try {
                Class<? extends TestDynamicInterface> implClass = Utils.createDynamicImplementation(TestDynamicInterface.class, "asm.li.earth.urchin.twic.asm.TestDynamicInterfaceImplDump");
                dynamicInterfaceImpl = implClass.newInstance();
            } catch (InstantiationException | InvocationTargetException | IllegalAccessException | NoSuchMethodException | ClassNotFoundException e) {
                throw new AssertionError(e);
            }
        }
    }

    @Benchmark
    public void getNoValue(Fixtures fixtures, Blackhole blackhole) {
        TestBean bean = fixtures.bean;
        blackhole.consume(bean);
    }

    @Benchmark
    public void getValueFromBean(Fixtures fixtures, Blackhole blackhole) {
        TestBean object = fixtures.bean;
        String value = object.getValue();
        blackhole.consume(value);
    }

    @Benchmark
    public void getValueFromStaticInterfaceImpl(Fixtures fixtures, Blackhole blackhole) {
        TestStaticInterface object = fixtures.staticInterfaceImpl;
        String value = object.getValue();
        blackhole.consume(value);
    }

    @Benchmark
    public void getValueFromDynamicInterfaceImpl(Fixtures fixtures, Blackhole blackhole) {
        TestDynamicInterface object = fixtures.dynamicInterfaceImpl;
        String value = object.getValue();
        blackhole.consume(value);
    }

}
