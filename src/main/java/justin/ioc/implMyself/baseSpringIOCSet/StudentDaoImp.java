package justin.ioc.implMyself.baseSpringIOCSet;

public class StudentDaoImp implements StudentDao {

	public void add(Student student) {
		
		System.out.println("StudentDao add(): " + student.getName()+" is saved");  
	}

}
