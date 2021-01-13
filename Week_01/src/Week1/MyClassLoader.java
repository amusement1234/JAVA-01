package Week1;

import java.io.*;
import java.net.URL;

public class MyClassLoader extends ClassLoader {

    public Class<?> findClass(String name) throws ClassNotFoundException {
        URL url = this.getClass().getResource("/");
        String path = url.getPath();
        File file = new File(path + name + ".xlass");
        Class<?> c = null;
        try {
            InputStream inputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            byte[] bytes = bufferedInputStream.readAllBytes();

            for (int i = 0; i < bytes.length; i++)
                bytes[i] = (byte) (255 - bytes[i]);
            bufferedInputStream.close();

            c = defineClass(name, bytes, 0, bytes.length);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return c;
    }
}
