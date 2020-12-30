public class Main {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentPrimes app = new ConcurrentPrimes(100, 10);
        app.run();
    }
}
