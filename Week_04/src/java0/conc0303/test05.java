package java0.conc0303;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;

public class test05 {
    static int result = 0;

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {

        long start = System.currentTimeMillis();

        Exchanger<Integer> exchanger = new Exchanger<>();
        new Thread(() -> {
            try {
                exchanger.exchange(CalFib.sum());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        result = exchanger.exchange(0);

        CalFib.print(start, result);

    }

}
