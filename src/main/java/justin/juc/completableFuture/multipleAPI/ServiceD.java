package justin.juc.completableFuture.multipleAPI;

public class ServiceD implements Api {
    private String className = this.getClass().getSimpleName();

    @Override
    public int getValue(){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();//调用的方法名
        return handle(className + "->" +methodName);
    }

    @Override
    public int getSecondValue(){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();//调用的方法名
        return handle(className + "->" +methodName);
    }
}
