import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLOutput;
import java.util.Arrays;

public class MyObject {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        MyObject mo = new MyObject("Hello");
        System.out.println(mo.getValue());

        Object o = mo;

        if (o instanceof MyObject) {
            System.out.println(((MyObject) o).getValue());
        }

        System.out.println(MyObject.class);
        Class c = o.getClass();
        System.out.println(Arrays.toString(c.getDeclaredMethods()));
        Method m = c.getDeclaredMethod("getValue");
        System.out.println(m.invoke(o));
        Field f = c.getDeclaredField("value");
        System.out.println(f.getType());
        System.out.println(f.get(o));
        f.setAccessible(true);
        f.set(o, "World");

        System.out.println(mo.getValue());

        String s = "Hello";
        Class cs = s.getClass();
        Field fs = cs.getDeclaredField("value");
        fs.setAccessible(true);
        ((char[])fs.get(s))[0] = 'W';
        System.out.println(Arrays.toString((char[])fs.get(s)));
        System.out.println(fs);
        System.out.println(mo.getValue());

        System.out.println(f.getDeclaredAnnotation(NewAnnotationType.class));
    }
    @NewAnnotationType
    private final String value;

    public MyObject(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
