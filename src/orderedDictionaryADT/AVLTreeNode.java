package orderedDictionaryADT;

public class AVLTreeNode<V> {
    public int key;// data item (key)
    public V val;
    public int height;             // height of node
    public AVLTreeNode left;         // this node's left child
    public AVLTreeNode right;        // this node's right child

    public AVLTreeNode(int id, V val) 			// constructor
    {
        this.key = id;
        this.val = val;
        this.height = 0;
        this.left = null;
        this.right = null;
    }

}
