package justin.designPattern.singleton.enumSingleton_recommend;


/**
 * ------------------------------------------------------------20190531--------------------------------------------------------------------
 * 最佳单例, (Effective java 作者推荐)
 * 不会存在 被反序列化破坏单例, jdk源码底层 针对针对枚举不被序列化和反射所破坏来保驾护航. (枚举类型不能被反射的方式来创建枚举类型的对象)
 *    反射时 会直接抛出枚举类型不能反射创建的异常.java.lang.IllegalArgumentException: Cannot reflectively create enum objects
 *
 *    测试类见, 同目录下的 EnumSingletonTest
 *
 * 不会存在 线程安全问题
 * 不会存在 指令重排-初始化未完成就被其他使用而导致空指针异常
 * ----------------------------------------------------------------------------------------------------------------------------------------
 */
public enum EnumSingleton {
    INSTANCE;
    private Object data;
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public static EnumSingleton getInstance(){
        return INSTANCE;
    }
}
