package justin.mybatis.v2;


import java.lang.reflect.InvocationTargetException;

public class SimpleExecutor implements Executor {



    private Configuration configuration;

    public SimpleExecutor(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public <E> E query(MapperRegistory.MapperData mapperData, String parameter) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        StatementHandler statementHandler = new StatementHandler(configuration);
        return (E) statementHandler.query(mapperData,parameter);
    }
}
