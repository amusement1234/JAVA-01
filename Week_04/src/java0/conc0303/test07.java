package java0.conc0303;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.FutureTask;

public class test07 {
    static int result = 0;

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {

        long start = System.currentTimeMillis();
        FutureTask<Integer> futureTask = new FutureTask<>(CalFib::sum);

        Thread thread = new Thread(futureTask);
        thread.start();

        CalFib.print(start, result);

    }

}
