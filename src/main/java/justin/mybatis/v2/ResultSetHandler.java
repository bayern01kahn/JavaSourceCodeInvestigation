package justin.mybatis.v2;

import com.mysql.cj.protocol.Resultset;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetHandler {
    Configuration configuration;

    public ResultSetHandler(Configuration configuration) {
        this.configuration = configuration;

    }

    public <E> E handle(ResultSet rs, Class type) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        //这里 使用 mybatis 包里的DefaultObjectFactory来完成 类型获取
        Object resultObj = new DefaultObjectFactory().create(type);

        if(rs.next()){
            int i =0;
            for(Field field: resultObj.getClass().getDeclaredFields()){
                setValue(resultObj, field, rs, i);
            }
        }
        return (E) resultObj;
    }

    private void setValue(Object resultObj, Field field, ResultSet rs, int i) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException {
        Method setMethod = resultObj.getClass().getMethod("set"+ upperCapital(field.getName()), field.getType());
        setMethod.invoke(resultObj, getResult(field, rs));
    }

    private Object getResult(Field field, ResultSet rs) throws SQLException {
        //TODO type handles
        Class<?> type = field.getType();
        if(Integer.class == type){
            return rs.getInt(field.getName());
        }

        if(String.class == type){
            return rs.getString(field.getName());
        }

        return rs.getString(field.getName());
    }

    private String upperCapital(String name) {
        String first = name.substring(0,1);
        String tail = name.substring(1);
        return  first.toUpperCase() + tail;
    }
}
