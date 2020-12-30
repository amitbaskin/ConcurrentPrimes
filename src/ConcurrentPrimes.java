import java.util.concurrent.*;

import static java.lang.System.exit;

public class ConcurrentPrimes {
    public static final int DONE = -1;
    public static final String TOO_LONG_MSG = "Calculation took too long...";
    public static final String PRIMES_FORMATTED_MSG = "The primes up to %d that were calculated:\n";
    public static final String PRIMES_UP_TO_NUM_ERR =
            "The number to get the primes up to must be greater than or equal to 1. Terminating program.";
    public static final String THREADS_AMOUNT_ERR =
            "The threads amount must be greater than or equal to 1. Terminating program.";
    private final int primesUptoNum;
    private final int threadsAmount;
    private int curIndex;
    private final boolean[] lst;

    public ConcurrentPrimes(int primesUpToNum, int threadsAmount){
        this.primesUptoNum = primesUpToNum;
        validatePrimesUpToNum(primesUpToNum);
        this.threadsAmount = threadsAmount;
        validateThreadsAmount(threadsAmount);
        this.lst = new boolean[primesUpToNum - 1];
    }

    public void run() throws InterruptedException {
        boolean isEnded;
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < threadsAmount; i++) {
            executorService.execute(new PrimesThread(this));
        } executorService.shutdown();
        isEnded = executorService.awaitTermination(1, TimeUnit.MINUTES);
        if (!isEnded) System.out.println(TOO_LONG_MSG);
        printPrimes();
    }

    private void printPrimes() {
        System.out.printf(PRIMES_FORMATTED_MSG, primesUptoNum);
        for (int i = 0; i < primesUptoNum - 1; i++) {
            if (lst[i]) System.out.println(i + 2);
        }
    }

    public synchronized int getCurIndex() { return curIndex < primesUptoNum - 1 ? curIndex++ : DONE; }

    public synchronized void setPrime(int index) { lst[index] = true; }

    private void validatePrimesUpToNum(int primesUptoNum){
        if (primesUptoNum < 1) {
            System.out.println(PRIMES_UP_TO_NUM_ERR);
            exit(0);
        }
    }

    private void validateThreadsAmount(int threadsAmount){
        if (threadsAmount < 1) {
            System.out.println(THREADS_AMOUNT_ERR);
            exit(0);
        }
    }
}

