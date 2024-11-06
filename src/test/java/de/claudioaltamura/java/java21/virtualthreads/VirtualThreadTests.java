package de.claudioaltamura.java.java21.virtualthreads;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class VirtualThreadTests {

    @Disabled
    @Test
    public void testFixedThreadPool() throws ExecutionException, InterruptedException {
        try (ExecutorService executor = Executors.newFixedThreadPool(100)) {
            List<Task> tasks = new ArrayList<>();
            for (int i = 0; i < 1_000; i++) {
                tasks.add(new Task(i));
            }

            long time = System.currentTimeMillis();

            List<Future<Integer>> futures = executor.invokeAll(tasks);

            long sum = 0;
            for (Future<Integer> future : futures) {
                sum += future.get();
            }

            time = System.currentTimeMillis() - time;

            System.out.println("sum = " + sum + "; time = " + time + " ms");
        }
    }

    @Disabled
    @Test
    public void testVirtualThreadPerTaskExecutor() throws ExecutionException, InterruptedException {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Task> tasks = new ArrayList<>();
            for (int i = 0; i < 1_000; i++) {
                tasks.add(new Task(i));
            }

            long time = System.currentTimeMillis();

            List<Future<Integer>> futures = executor.invokeAll(tasks);

            long sum = 0;
            for (Future<Integer> future : futures) {
                sum += future.get();
            }

            time = System.currentTimeMillis() - time;

            System.out.println("sum = " + sum + "; time = " + time + " ms");
        }
    }

}