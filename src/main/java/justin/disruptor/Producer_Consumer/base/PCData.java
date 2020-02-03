package justin.disruptor.Producer_Consumer.base;

public class PCData {
    private final int intData;

    public PCData(int d) {
        intData = d;
    }

    public PCData(String d) {
        intData = Integer.valueOf(d);
    }

    public int getData() {
        return intData;
    }


    public String toString() {
        return String.valueOf(intData);
    }
}
