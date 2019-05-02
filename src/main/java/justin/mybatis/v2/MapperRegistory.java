package justin.mybatis.v2;

import java.util.HashMap;
import java.util.Map;

public class MapperRegistory {
    public static final Map<String,MapperData> methodSqlMapping = new HashMap<>();

    public MapperRegistory() {
        methodSqlMapping.put("justin.mybatis.v2.TestMapper.selectByPrimaryKey", new MapperData("select * from test where id = %d", Test.class));
    }

    public MapperData get(String s) {
        return methodSqlMapping.get(s);
    }

    public class MapperData<T>{

        public MapperData(String sql, Class<T> type) {
            this.sql = sql;
            this.type = type;
        }

        String sql;
        Class<T> type;

        public String getSql() {
            return sql;
        }

        public void setSql(String sql) {
            this.sql = sql;
        }

        public Class<T> getType() {
            return type;
        }

        public void setType(Class<T> type) {
            this.type = type;
        }
    }
}
