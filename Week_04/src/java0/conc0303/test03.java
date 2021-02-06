package java0.conc0303;

import java.util.concurrent.CountDownLatch;

public class test03 {
    static int result = 0;

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread thread = new Thread(() -> {
            result = CalFib.sum();
            countDownLatch.countDown();
        });
        thread.start();
        countDownLatch.await();

        CalFib.print(start, result);

    }

}
