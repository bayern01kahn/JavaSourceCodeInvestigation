package justin.mybatis.v2;

import justin.mybatis.v1.Test;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatementHandler implements Executor{

    private Configuration configuration;

    private final ResultSetHandler resultSetHandler;

    public StatementHandler(Configuration configuration) {
        this.configuration = configuration;
        resultSetHandler = new ResultSetHandler(configuration);
    }


    @Override
    public <T> T query(MapperRegistory.MapperData mapperData, String parameter) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Connection con = null;
        try{
            con = getConnection();

            //TODO ParameterHandler
            String sql  = String.format(mapperData.getSql(), Integer.parseInt(parameter));
            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();

            //ResultSetHandler
            return resultSetHandler.handle(rs, mapperData.getType());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return (T) null;
    }

    private Connection getConnection() {
        Connection con = null;
        String username = "root";
        String password = "footballer";

        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?usdUnicode=true", username, password);
            return con;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
