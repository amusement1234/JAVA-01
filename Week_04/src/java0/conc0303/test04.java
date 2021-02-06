package java0.conc0303;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class test04 {
    static int result = 0;

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {

        long start = System.currentTimeMillis();

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        Thread thread = new Thread(()->{
            result =CalFib.sum();
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        cyclicBarrier.await();

        CalFib.print(start, result);

    }

}
