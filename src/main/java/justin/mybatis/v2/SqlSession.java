package justin.mybatis.v2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

public class SqlSession {

    public SqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }


    private Configuration configuration;
    private Executor executor;


    public Configuration getConfiguration() {
        return configuration;
    }


    public <T>T getMapper(Class clazz){
        return  (T) Proxy.newProxyInstance(clazz.getClassLoader(),new Class[] {clazz}, new MapperProxy(this, clazz));
    }

    public <T>T selectOne(MapperRegistory.MapperData mapperData, String parameter) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return (T) executor.query(mapperData, parameter);
    }

}
