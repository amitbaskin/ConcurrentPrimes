public class PrimesThread implements Runnable{
    private final ConcurrentPrimes buff;

    public PrimesThread(ConcurrentPrimes buff){ this.buff = buff; }

    @Override
    public void run() {
        int curIndex;
        while ((curIndex = buff.getCurIndex()) != ConcurrentPrimes.DONE){
                if (isPrime(curIndex + 2)){
                    buff.setPrime(curIndex);
            }
        }
    }

    private boolean isPrime(Integer num){
        int maxToCheck = (int) Math.floor(Math.sqrt(num));
        for (int i=2; i<=maxToCheck; i++){
            if ( num % i == 0) return false;
        } return true;
    }
}
