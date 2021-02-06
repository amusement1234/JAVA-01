package java0.conc0303;

import java.util.concurrent.Callable;

public class test09 {
    static int result = 0;

    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            result = CalFib.sum();
        });
        t1.start();
        Thread.sleep(500);

        CalFib.print(start, result);

    }

}
