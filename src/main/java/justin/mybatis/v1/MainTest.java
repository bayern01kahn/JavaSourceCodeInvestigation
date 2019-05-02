package justin.mybatis.v1;

public class MainTest {

    public static void main(String[] args) {

        SqlSession sqlSession = new SqlSession(new Configuration(), new SimpleExecutor());

        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);

        Test test = testMapper.selectByPrimaryKey(1);

        System.out.println(test);

    }
}
