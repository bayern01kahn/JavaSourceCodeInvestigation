package justin.ioc.instanceOfUnderStandIOC;

public class MainTest {

	public static void main(String args[]){
		
		//1. Example既依赖于接口DataFinder，又同时依赖实现
		Example example = new Example();
        example.doStuff();
         
        
        //2. 现在代码变得简单了，但Example类依然依赖具体的实现
        Example e1 = new Example(1);
        e1.doStuff();
         
        Example e2 = new Example(2);
        e2.doStuff();
        
        
        //3.这样Example不用依赖具体的实现了，不用管到底是用哪种类型的DataFinder。换句话说，就是将调用类Example类对于选择哪个具体DataFinder的控制权从其中移除，转交给Client决定，实现了“控制”的“反转”，这也就是我们所说的IoC。
        Example example1 = new Example(new MysqlDataFinder());
        example1.doStuff();
         
        Example example2 = new Example(new OracleDataFinder());
        example2.doStuff();
	}
}
