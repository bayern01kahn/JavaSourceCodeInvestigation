package justin.ioc.implMyself.baseSpringIOCSet;

public class Student {
	 private int id;    
	    private String name;   
	    private String memo;  
	    private String address;  
	      
	    public Student(int id, String name, String memo, String address) {  
	        super();  
	        this.id = id;  
	        this.name = name;  
	        this.memo = memo;  
	        this.address = address;  
	    }  
	    public int getId() {  
	        return id;  
	    }  
	    public void setId(int id) {  
	        this.id = id;  
	    }  
	    public String getName() {  
	        return name;  
	    }  
	    public void setName(String name) {  
	        this.name = name;  
	    }  
	    public String getMemo() {  
	        return memo;  
	    }  
	    public void setMemo(String memo) {  
	        this.memo = memo;  
	    }  
	    public String getAddress() {  
	        return address;  
	    }  
	    public void setAddress(String address) {  
	        this.address = address;  
	    }  
}
