package justin.test;

/**
 * 是的，刚开始触发类加载之后的一系列操作完成之后，开始进行初始化，赋初值， counter1 = counter2 = 0，instance = null，
 * 然后开始执行用户的初始化，instance = new Singleton()，然后执行构造器，counter1 = counter2 = 1，
 * 然后初始化counter1，因为counter1无用户初始化的值，然后执行counter2 = 0，所有counter2从0变化1再变化到0。
 * @author justin
 *
 */
class SingletonLoadTest {
    private static SingletonLoadTest intsance = new SingletonLoadTest();
    public static int counter1;
    public static int counter2 = 0;
    private SingletonLoadTest() {
    	System.out.println("构造函数中： counter1 = " + counter1 + ", counter2 = " + counter2);
        counter1++;
        counter2++;
    }
    public static SingletonLoadTest getInstance() {
        return intsance;
    }
    
    public static void main(String args[]){
    	SingletonLoadTest instance = SingletonLoadTest.getInstance();
    	System.out.println("counter1 = " + SingletonLoadTest.counter1 + ", counter2 = " + SingletonLoadTest.counter2);
    }
}

