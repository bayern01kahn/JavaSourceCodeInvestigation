package justin.proxy;

import justin.proxy.cglib.CGLibProxy;
import justin.proxy.jdk.JDKProxy;

/**
 * 代理模式[[ 客户端--》代理对象--》目标对象 ]]
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("**********************CGLibProxy**********************");
        CGLibProxy cgLibProxy = new CGLibProxy();
        IUserManager userManager = (IUserManager) cgLibProxy.createProxyObject(new UserManagerImpl());
        userManager.addUser("justin", "123456");

        System.out.println("**********************JDKProxy**********************");
        JDKProxy jdkPrpxy = new JDKProxy();
        IUserManager userManagerJDK = (IUserManager) jdkPrpxy.newProxy(new UserManagerImpl());
        userManagerJDK.addUser("justin", "123456");
    }
}
