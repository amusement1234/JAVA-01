import java.lang.reflect.Method;

public class app {
    public static void main(String[] args) {
        try {
            Class o = new MyClassLoader().findClass("Hello");
            Method method = o.getMethod("hello");
            method.invoke(o.newInstance());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}