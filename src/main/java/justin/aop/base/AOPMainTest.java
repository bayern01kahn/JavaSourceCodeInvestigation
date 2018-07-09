package justin.aop.base;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPMainTest {

	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

		AOPInterface aClass = (AOPInterface) ctx.getBean("aopA");
		AOPInterface bClass = (AOPInterface) ctx.getBean("aopB");
		aClass.job1();

		System.out.println();
		bClass.work1();
	}

}
