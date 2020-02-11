package justin.juc.threadPool.sourceCode;

public class ThreadStatusDemo {private final static int COUNT_BITS = Integer.SIZE - 3;
    private final static int RUNNING    = -1 << COUNT_BITS;
    private final static int SHUTDOWN   =  0 << COUNT_BITS;
    private final static int STOP       =  1 << COUNT_BITS;
    private final static int TIDYING    =  2 << COUNT_BITS;
    private final static int TERMINATED =  3 << COUNT_BITS;
    private final static int CAPACITY   = (1 << COUNT_BITS) - 1;

    /**
     * 高三位表示线程池状态, 低29位表示线程池有效线程数量
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("状态位===");
        System.out.println("RUNNING    => "+getFormatStr(RUNNING));
        System.out.println("SHUTDOWN   => "+getFormatStr(SHUTDOWN));
        System.out.println("STOP       => "+getFormatStr(STOP));
        System.out.println("TIDYING    => "+getFormatStr(TIDYING));
        System.out.println("TERMINATED => "+getFormatStr(TERMINATED));
        System.out.println("CAPACITY   => "+getFormatStr(CAPACITY));
    }

    private static String getFormatStr(int n) {
        String integerMaxValueStr = Integer.toBinaryString(n);
        int a = 32;
        StringBuilder sb = new StringBuilder();
        int l = integerMaxValueStr.length();
        int i = 0;
        for (; a > 0; --a) {
            if (--l >= 0) {
                sb.append(integerMaxValueStr.charAt(l));
            } else {
                sb.append("0");
            }
            if (++i % 4 == 0) {
                if (a > 1) {
                    sb.append("-");
                }
                i = 0;
            }
        }
        return sb.reverse().toString();
    }
}