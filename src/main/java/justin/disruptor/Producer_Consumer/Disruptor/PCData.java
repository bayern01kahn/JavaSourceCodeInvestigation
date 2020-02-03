package justin.disruptor.Producer_Consumer.Disruptor;

public class PCData {
    public long get() {
        return value;
    }

    public void set(long value) {
        this.value = value;
    }

    private long value;
}
