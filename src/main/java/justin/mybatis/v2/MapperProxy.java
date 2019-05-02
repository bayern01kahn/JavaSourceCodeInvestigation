package justin.mybatis.v2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MapperProxy<T> implements InvocationHandler {

    private SqlSession sqlSession;
    private final Class<T> mapperInterface;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        MapperRegistory.MapperData mapperData = sqlSession.getConfiguration().getMapperRegistory().get(method.getDeclaringClass().getName()+"."+method.getName());

        if(null != mapperData){
            System.out.println(String.format("SQL [%s], parameter [%s]", mapperData.getSql(), args[0]));
            return sqlSession.selectOne(mapperData, String.valueOf(args[0]));
        }

        return method.invoke(this, args);
    }
}
