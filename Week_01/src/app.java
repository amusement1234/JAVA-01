import java.lang.reflect.Method;
import Week1.MyClassLoader;
public class app {
    public static void main(String[] args) {

        try {
            Class c = new MyClassLoader().findClass("Hello");
            Method method = c.getMethod("hello");
            method.invoke(c.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}