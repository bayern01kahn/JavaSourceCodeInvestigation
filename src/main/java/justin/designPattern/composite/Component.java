package justin.designPattern.composite;

public abstract class Component {

	public String name;

	public Component(String name) {
		this.name = name;
	}

	// 公有操作
	public void getName() {
		System.out.println(this.name);
	}
}
