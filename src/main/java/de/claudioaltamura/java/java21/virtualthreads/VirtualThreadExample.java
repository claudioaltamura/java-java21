package de.claudioaltamura.java.java21.virtualthreads;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @see <a href="https://github.com/SvenWoltmann/virtual-threads/tree/main/src/main/java/eu/happycoders/virtualthreads/presentation">virtual example</a>
 */
public class VirtualThreadExample {

    private static final int NUMBER_OF_VIRTUAL_THREADS = 1_000;
    private static final int PRINT_STEP = Math.min(NUMBER_OF_VIRTUAL_THREADS / 10, 100_000);

    private static final long SLEEP_MILLIS = 50;

    static void doSomething() {
        long millis = 0;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(SLEEP_MILLIS);
                millis += SLEEP_MILLIS;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("I died after " + millis + " milliseconds");
    }

    static void waitForVirtualThreadsToCatchUp(
            int startedThreads, AtomicLong runningThreadsCounter, long startTime)
            throws InterruptedException {
        long runningThreads;
        while (startedThreads > (runningThreads = runningThreadsCounter.get())) {
            long time = System.currentTimeMillis() - startTime;
            System.out.printf(
                    "Waiting for virtual threads to catch up: (%,d running after %,d ms)...%n",
                    runningThreads, time);
            Thread.sleep(100);
        }
        long time = System.currentTimeMillis() - startTime;
        System.out.printf(
                "Virtual threads caught up: %,d running after %,d ms%n", runningThreads, time);
    }

    public static void main() throws InterruptedException {
        AtomicLong runningThreadsCounter = new AtomicLong();

        long startTime = System.currentTimeMillis();

        for (int i = 1; i <= NUMBER_OF_VIRTUAL_THREADS; i++) {
            Thread.ofVirtual()
                    .start(
                            () -> {
                                runningThreadsCounter.incrementAndGet();
                                doSomething();
                            });

            if (i % PRINT_STEP == 0) {
                long runningThreads = runningThreadsCounter.get();
                long time = System.currentTimeMillis() - startTime;
                System.out.printf(
                        "%,d virtual threads started, %,d virtual threads running after %,d ms%n",
                        i, runningThreads, time);

                if (i - runningThreads > 200_000) {
                    waitForVirtualThreadsToCatchUp(i, runningThreadsCounter, startTime);
                }
            }
        }

        waitForVirtualThreadsToCatchUp(
                NUMBER_OF_VIRTUAL_THREADS, runningThreadsCounter, startTime);
    }
}
