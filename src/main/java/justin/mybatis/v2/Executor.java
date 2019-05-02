package justin.mybatis.v2;

import java.lang.reflect.InvocationTargetException;

public interface Executor {
    <T>T query(MapperRegistory.MapperData mapperData, String parameter) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}
