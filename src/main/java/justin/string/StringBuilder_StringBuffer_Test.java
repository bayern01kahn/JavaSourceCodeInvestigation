package justin.string;

import org.junit.Test;

public class StringBuilder_StringBuffer_Test {

    public static void main(String[] args)  {

    }

    /**
     * StringBuilder 问题出在 append() 是使用的父类 AbstractStringBuilder的 append(). 其中 count += len;
     * 非原子操作.多线程情况下 非线程安全
     */
    @Test
    public void StringBuidlerTest() throws InterruptedException{
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++){
            new Thread(() -> {
                for (int j = 0; j < 1000; j++){
                    stringBuilder.append("a");
                }
            }).start();
        }

        Thread.sleep(100);
        System.out.println(stringBuilder.length());
    }


    /**
     *  而 StringBuffer 使用了 synchronized  在append() 锁住了
     */
    @Test
    public void StringBufferTest() throws InterruptedException{
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 10; i++){
            new Thread(() -> {
                for (int j = 0; j < 1000; j++){
                    stringBuffer.append("a");
                }
            }).start();
        }

        Thread.sleep(100);
        System.out.println(stringBuffer.length());
    }

}
