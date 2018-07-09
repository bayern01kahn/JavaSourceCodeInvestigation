package justin.aop.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationAOPMainTest {
	

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		AOPTargetClass atc = (AOPTargetClass) ctx.getBean("AOPTargetClass");
		atc.add();

		System.out.println();
		
		atc.delete();
	}
}
