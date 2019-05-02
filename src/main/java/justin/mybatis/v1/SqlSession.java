package justin.mybatis.v1;


public class SqlSession {

    private Configuration configuration;
    private Executor executor;

    public SqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    public <T>T getMapper(Class clazz){
        return  (T) configuration.getMapper(clazz, this);
    }

    public <T>T selectOne(String statement, String parameter){
        return (T) executor.query(statement, parameter);
    }

}
