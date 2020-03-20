package justin.designPattern.composite;

import java.util.LinkedList;

public class Composite extends Component {

	private LinkedList<Component> children;

	public Composite(String name) {
		super(name);
		this.children = new LinkedList<Component>();
	}

	// 添加一个节点，可能是树枝、叶子
	public void add(Component child) {
		this.children.add(child);
	}

	// 删除一个节点，可能是树枝、叶子
	public void remove(String child) {
		this.children.remove(child);
	}

	// 获取子树
	public LinkedList<Component> getChildren() {
		return this.children;
	}

}
