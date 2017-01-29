package li.earth.urchin.twic.asm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Utils {

    static <T> Class<? extends T> createDynamicImplementation(Class<T> interfaceClass, String dumpClassName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> dumpClass = Class.forName(dumpClassName);
        Method dumpMethod = dumpClass.getDeclaredMethod("dump");

        byte[] implClassBytes = (byte[]) dumpMethod.invoke(null);

        ClassLoader classLoader = interfaceClass.getClassLoader();
        Method defineClass = getInheritedMethod(classLoader.getClass(), "defineClass", String.class, byte[].class, int.class, int.class);
        defineClass.setAccessible(true);

        Class<?> implClass = (Class<?>) defineClass.invoke(classLoader, null, implClassBytes, 0, implClassBytes.length);

        return implClass.asSubclass(interfaceClass);
    }

    static Method getInheritedMethod(Class<?> clazz, String name, Class... parameterTypes) throws NoSuchMethodException {
        try {
            return clazz.getDeclaredMethod(name, parameterTypes);
        } catch (NoSuchMethodException e) {
            Class<?> superclass = clazz.getSuperclass();
            if (superclass == null) throw e;
            else return getInheritedMethod(superclass, name, parameterTypes);
        }
    }

}
