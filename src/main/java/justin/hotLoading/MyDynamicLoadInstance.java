package justin.hotLoading;

public class MyDynamicLoadInstance implements DynamicLoad {

	public void dynamicUpdate() {
		System.out.println("当前正在测试类热加载: 测试文本123456");
	}

}
