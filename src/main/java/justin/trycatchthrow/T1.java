package justin.trycatchthrow;

public class T1 {

    public static void main(String[] args) {
        T1 t1 = new T1();

        //t1.m0();
        //m0 start
        //m1 start
        //m1 throw

        t1.m0Catch();
        //m0 start
        //m1 start
        //m1 throw
        //m0 catch
        //m1 end
        //m0 end
    }

    private void m0Catch() {
        System.out.println("m0 start");
        try{
            m1();
        } catch (Exception e){
            System.out.println("m0.catch ");
            //e.printStackTrace();
        }
        System.out.println("m0 end");
    }

    private void m0() {

        System.out.println("m0 start");
        m1();
        System.out.println("m0 end");
    }

    private void m1() {

        System.out.println("m1 start");
        try {
            int i = 10/0;
        } catch(Exception e){
            System.out.println("m1 throw");
            throw e;
        }
        System.out.println("m1 end");
    }

}
