package justin.mybatis.v1;


public interface Executor {
    public <T>T query(String statement, String parameter);
}
