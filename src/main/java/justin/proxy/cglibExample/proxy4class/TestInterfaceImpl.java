package justin.proxy.cglibExample.proxy4class;


import justin.proxy.cglibExample.target.TestInterface;

public class TestInterfaceImpl implements TestInterface {
    @Override
    public Object getHalloWorld() {
        System.out.println("hello Justin world.");
        return null;
    }
}
