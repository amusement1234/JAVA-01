package java0.conc0303;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class test01 {
    static int result = 0;

    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();

        Thread thread = new Thread(() -> {
            result = CalFib.sum();
        });
        thread.start();
        thread.join();


        CalFib.print(start, result);

    }

}
