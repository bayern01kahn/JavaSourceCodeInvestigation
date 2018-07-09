package justin.ioc.implMyself.baseSpringIOCSet;

public class BaseSpringIOCSetTest {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
		StudentService service = (StudentService) context.getBean("studentService");
		Student student = new Student(1, "justin", "springtest", "Muchen");
		service.add(student);

	}

}
