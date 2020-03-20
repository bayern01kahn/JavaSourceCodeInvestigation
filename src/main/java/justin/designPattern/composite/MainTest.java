package justin.designPattern.composite;

import java.util.LinkedList;

public class MainTest {

	public static void main(String[] args) {

		System.out.println("组合模式：简单结构代码");
		
		
		Composite root = new Composite("树根");

		Composite branch01 = new Composite("树枝01");
		Composite branch02 = new Composite("树枝02");

		root.add(branch01);
		root.add(branch02);

		Component leaf01 = new Leaf("树叶01");
		Component leaf02 = new Leaf("树叶02");
		Component leaf03 = new Leaf("树叶03");
		Component leaf04 = new Leaf("树叶04");
		Component leaf05 = new Leaf("树叶05");

		branch01.add(leaf01);
		branch01.add(leaf02);

		branch02.add(leaf03);
		branch02.add(leaf04);
		branch02.add(leaf05);
		
		displayTree(root);

	}

	// 递归遍历整棵树
	public static void displayTree(Composite root) {
		LinkedList<Component> children = root.getChildren();

		for (Component c : children) {
			if (c instanceof Leaf) {
				System.out.print("\t");
				c.getName();
			} else {
				c.getName();
				// 递归
				displayTree((Composite)c);
			}
		}
	}
}
