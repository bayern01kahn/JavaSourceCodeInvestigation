package justin.classloader;


/**
 * 主要是为了 考察和理解  类加载 的 三个阶段  加载-链接-初始化中
 *
 * 链接-准备 阶段的 所做的事情:   为类的静态变量分配内存, 并将其初始化为该类型对应的默认值:    int x  =>   x =0
 *
 */
public class classLoaderTest {

    /**
     * 情景1:  正常情况
     * 创建单例在 赋值之后
     *
     * 执行情况:
     * 1.链接-准备阶段:
     *   x = 0
     *   y = 0
     *   instance = null;
     *
     * 2.初始化
     *   x = 0
     *   y = 0
     *   instance = new classLoaderTest() {
     *       x++  =>  x=1
     *       y++  =>  y=1
     *   }
     *
     *   结果:
     *   x = 1
     *   y = 1
     */

//    private  static int x=0;
//    private  static int y;
//    private static classLoaderTest instance = new classLoaderTest();

    /**
     * 情景2:   特殊情况
     * 创建单例在 赋值之后
     *
     * 执行情况:
     * 1.链接-准备阶段:
     *   instance = null;
     *   x = 0
     *   y = 0
     *
     * 2.初始化
     *   instance = new classLoaderTest() {
     *       x++  =>  x=1
     *       y++  =>  y=1
     *   }
     *   x = 0  (!!!  x 重新被赋值为0 )
     *   y = 1
     *
     *   结果:
     *   x = 0
     *   y = 1
     */
    private static classLoaderTest instance = new classLoaderTest();
    private  static int x=0;
    private  static int y;

    public classLoaderTest() {
        x++;
        y++;
    }

    public static classLoaderTest getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        classLoaderTest instance = getInstance();
        System.out.println(x);
        System.out.println(y);

    }
}
