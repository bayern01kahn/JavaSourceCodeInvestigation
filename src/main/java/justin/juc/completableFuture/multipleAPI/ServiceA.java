package justin.juc.completableFuture.multipleAPI;

public class ServiceA implements Api {

    private String className = this.getClass().getSimpleName();
    @Override
    public int getValue(){
        //这将获得当前线程的堆栈跟踪，并在其顶部返回方法的名称。
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();//调用的方法名
        return handle(className + "->" +methodName);
    }

    @Override
    public int getSecondValue(){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();//调用的方法名
        return handle(className + "->" +methodName);
    }
}
