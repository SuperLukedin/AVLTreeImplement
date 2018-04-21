package orderedDictionaryADT;

public class AVLTreeNode {
    public int key;              // data item (key)
    public int height;             // height of node
    public AVLTreeNode left;         // this node's left child
    public AVLTreeNode right;        // this node's right child

    public AVLTreeNode(int id) 			// constructor
    {
        this.key = id;
        this.height = 0;
        this.left = null;
        this.right = null;
    }

}
