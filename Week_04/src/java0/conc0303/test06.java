package java0.conc0303;

import java.util.concurrent.*;

public class test06 {
    static int result = 0;

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException, ExecutionException {

        long start = System.currentTimeMillis();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> result = executorService.submit(CalFib::sum);
        executorService.shutdown();

        CalFib.print(start, result.get());

    }

}
