package justin.ioc.implMyself.baseSpringIOCSet;

public class StudentServiceImp implements StudentService {

	StudentDao studentDao=null;    
    
    public void setStudentDao(StudentDao studentDao) {    
        this.studentDao = studentDao;    
    }   
      
    public void add(Student student) {  
        studentDao.add(student);    
          
    }  

}
