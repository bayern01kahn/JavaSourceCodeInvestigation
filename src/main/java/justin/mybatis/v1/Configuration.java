package justin.mybatis.v1;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class Configuration {
    public <T>T getMapper(Class<T> clazz,SqlSession sqlSession) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[] {clazz}, new MapperProxy(sqlSession));
    }

    //默认完成了 xml 解析,通过 key-value  就拿到了 sql
    static class TestMapperXml{
        public static final String namespace = "justin.mybatis.v1.TestMapper";
        public static final Map<String,String> methodSqlMapping = new HashMap<>();

        static {
            methodSqlMapping.put("selectByPrimaryKey","select * from test where id = %d");
        }

    }
}
