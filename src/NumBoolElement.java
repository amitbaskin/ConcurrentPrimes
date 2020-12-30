public class NumBoolElement {
    private final int value;
    private final boolean isPrime;

    public boolean isPrime() {
        return isPrime;
    }

    public int getValue() {
        return value;
    }

    public NumBoolElement(int value, boolean isPrime){
        this.value = value;
        this.isPrime = isPrime;
    }
}
