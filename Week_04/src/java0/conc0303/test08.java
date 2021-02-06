package java0.conc0303;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;

public class test08 {
    static int result = 0;

    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();

        Callable<Integer> result = CalFib::sum;

        CalFib.print(start, result.call());

    }

}
