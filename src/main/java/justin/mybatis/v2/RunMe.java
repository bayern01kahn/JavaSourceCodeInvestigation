package justin.mybatis.v2;

public class RunMe {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();

        // 无缓存调用
        SqlSession sqlSession = new SqlSession(configuration, ExecutorFactory.get(ExecutorFactory.ExecutorType.SIMPLE.name(),configuration));

        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);

        long start = System.currentTimeMillis();
        Test test = testMapper.selectByPrimaryKey(1);
        System.out.println("直接查询结果: "+ test+ "  || cost:"+ (System.currentTimeMillis() -start));
        System.out.println("\n\n\n"+"-----------------------华丽分隔符----------------------\n\n\n");

        // 启用缓存方式
        SqlSession sqlSessionWithCache = new SqlSession(configuration, ExecutorFactory.get(ExecutorFactory.ExecutorType.CACHING.name(),configuration));
        TestMapper testMapperCache = sqlSessionWithCache.getMapper(TestMapper.class);
        start = System.currentTimeMillis();

        Test test2 = testMapperCache.selectByPrimaryKey(1);
        System.out.println("第一次最终结果: "+ test2+ "  || cost:"+ (System.currentTimeMillis() -start));

        start = System.currentTimeMillis();
        Test test3 = testMapperCache.selectByPrimaryKey(1);
        System.out.println("启用缓存最终结果: "+ test3+ "  || cost:"+ (System.currentTimeMillis() -start));
    }
}
