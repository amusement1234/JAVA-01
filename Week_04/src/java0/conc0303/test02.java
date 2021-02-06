package java0.conc0303;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class test02 {


    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        int result = CompletableFuture.supplyAsync(() -> CalFib.sum()).join();
        CalFib.print(start, result);

    }

}
