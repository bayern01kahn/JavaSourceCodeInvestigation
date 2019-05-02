package justin.mybatis.v1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SimpleExecutor implements Executor {
    @Override
    public <T> T query(String statement, String parameter) {
        Connection con = null;
        PreparedStatement ps = null;
        String username = "root";
        String password = "footballer";
        Test test = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?usdUnicode=true", username, password);
            String sql  = String.format(statement, Integer.parseInt(parameter));
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                test = new Test();
                test.setId(rs.getInt(1));
                test.setName(rs.getString(2));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return (T) test;
    }
}
