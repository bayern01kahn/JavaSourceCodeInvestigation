package justin.dataStructure.tree.BinarySearchTree;

public class BinarySearchTree {

    private static int[] array = new int[]{15,2,3,4,6,7,9,13,17,18,20};
    private TreeNode root = null;

    //建立二叉搜索树
    public void createBinarySearchTree(int key) {

        TreeNode node = new TreeNode(key);

        if (root == null) {
            root = node;
        } else {
            TreeNode current = root;
            TreeNode parent = null;

            while (current != null) {
                parent = current;
                if (current.getData() < node.getData()) {
                    current = current.getRightChild();
                } else {
                    current = current.getLeftChild();
                }
            }

            node.setParent(parent);

            if (parent == null) {
                root = node;
            } else if (parent.getData() > node.getData()) {
                parent.setLeftChild(node);
            } else {
                parent.setRightChild(node);
            }
        }
    }

    //查找关键字(递归)
    public TreeNode treeSearch(TreeNode node, int key) {

        if (node == null || key == node.getData()) {
            return node;
        }

        if (key < node.getData()) {
            return treeSearch(node.getLeftChild(), key);
        } else {
            return treeSearch(node.getRightChild(), key);
        }
    }

    //查找关键字(非递归)
    public TreeNode nonTreeSearch(TreeNode node, int key) {

        while (node != null && key != node.getData()) {
            if (key < node.getData()) {
                node = node.getLeftChild();
            } else {
                node = node.getRightChild();
            }
        }

        return node;
    }

    //最小关键字
    public TreeNode minimumNode(TreeNode node) {

        while (node.getLeftChild() != null) {
            node = node.getLeftChild();
        }

        return node;
    }

    //最大关键字
    public TreeNode maximumNode(TreeNode node) {

        while (node.getRightChild() != null) {
            node = node.getRightChild();
        }

        return node;
    }

    //node节点的直接后继节点
    public TreeNode successorNode(TreeNode node) {

        if (node.getRightChild() != null) {
            return minimumNode(node.getRightChild());
        }

        TreeNode parent = null;
        node.setParent(parent);

        while (parent != null && node == parent.getRightChild()) {

            node = parent;
            parent = parent.getParent();
        }

        return parent;
    }

    //node节点的直接前驱节点
    public TreeNode predecessorNode(TreeNode node) {

        if (node.getLeftChild() != null) {
            return maximumNode(node.getLeftChild());
        }

        TreeNode parent = null;
        node.setParent(parent);

        while (parent != null && node == parent.getLeftChild()) {

            node = parent;
            parent = parent.getParent();
        }

        return parent;
    }

    //节点的替换
    public void transplantNode(TreeNode node, TreeNode instead) {

        if (node.getParent() == null) {
            root = instead;
        } else if (node == node.getParent().getLeftChild()) {
            node.getParent().setLeftChild(instead);
        } else {
            node.getParent().setRightChild(instead);
        }

        if (instead != null) {
            instead.setParent(node.getParent());
        }
    }

    //删除节点
    public void deleteNode(TreeNode node) {

        if (node.getLeftChild() == null) {
            transplantNode(node, node.getRightChild());
        } else if (node.getRightChild() == null) {
            transplantNode(node, node.getLeftChild());
        } else {
            TreeNode temp = minimumNode(node.getRightChild());
            if (temp.getParent() != node) {
                transplantNode(temp, temp.getRightChild());
                temp.setRightChild(node.getRightChild());
                temp.getRightChild().setParent(temp);
            }
            transplantNode(node, temp);
            temp.setLeftChild(node.getLeftChild());
            temp.getLeftChild().setParent(temp);
        }
    }

    public static void main(String[] args) {

        BinarySearchTree bt = new BinarySearchTree();

        for (int i = 0; i < array.length; i++) {
            bt.createBinarySearchTree(array[i]);
        }
        bt.root.setData(array[0]);
    }

}

class TreeNode {

    private int data = 0;
    private TreeNode leftChild = null;
    private TreeNode rightChild = null;
    private TreeNode parent = null;

    public TreeNode() {

    }

    public TreeNode(int data) {

        this.data = data;
        this.leftChild = null;
        this.rightChild = null;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public TreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode rightChild) {
        this.rightChild = rightChild;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }
}