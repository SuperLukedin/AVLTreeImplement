package orderedDictionaryADT;

public class AvlTree {
    private AVLTreeNode root;

    public AvlTree(AVLTreeNode root) {    //Constructor
        this.root = null;
    }

    public AVLTreeNode getRoot() {
        return root;
    }

    public AVLTreeNode find (int key, AVLTreeNode Node) {
        if (Node == null) {
            return null;
        } else if (Node.key == key){
            return Node;
        } else if (Node.key < key) {
            return find(key, Node.right);
        } else {
            return find(key, Node.left);
        }
    }

    public int height (AVLTreeNode node) {
        if (node == null) {
            return -1;
        } else {
            return node.height;
        }
    }
     public AVLTreeNode rotateRight (AVLTreeNode targetNode) {
        AVLTreeNode a = targetNode.left;
        targetNode.left = a.right;
        a.right = targetNode;
         //Update height for targetNode and a with the highest child node(either left or right) + 1;
        targetNode.height = Math.max(height(targetNode.left), height(targetNode.right)) + 1;
        a.height = Math.max(height(a.left), height(a.right)) + 1;
        return a;    // position of targetNode was replaced by a.
     }

     public AVLTreeNode rotateLeft (AVLTreeNode targetNode) {
        AVLTreeNode a = targetNode.right;
        targetNode.right = a.left;
        a.left = targetNode;
        //Update height for targetNode and a with the highest child node(either left or right) + 1;
        targetNode.height = Math.max(height(targetNode.left), height(targetNode.right)) + 1;
        a.height = Math.max(height(a.left), height(a.right)) + 1;
        return a;
     }

     public int getBalanceFactor (AVLTreeNode targetNode) {
        if (targetNode == null) {
            return 0;
        }
        return height(targetNode.left) - height(targetNode.right);
     }

     public AVLTreeNode insertion (AVLTreeNode targetNode, int key) {
        if (targetNode == null) {
            return new AVLTreeNode(key);
        }
        if (targetNode.key > key) {
            targetNode.left = insertion(targetNode.left, key);
        } else if (targetNode.key < key) {
            targetNode.right = insertion(targetNode.right, key);
        } else {
            System.out.println("This is a duplicate key.");
        }
        targetNode.height = Math.max(height(targetNode.left), height(targetNode.right)) + 1;

        if ((getBalanceFactor(targetNode) > 1) && (targetNode.left.key > key)) {
            return rotateRight(targetNode);
         }
         if ((getBalanceFactor(targetNode) > 1) && (targetNode.left.key < key)) {
            targetNode.left = rotateLeft(targetNode.left);
            return rotateRight(targetNode);
         }
         if ((getBalanceFactor(targetNode) < -1) && (targetNode.right.key < key)) {
            return rotateLeft(targetNode);
         }
         if ((getBalanceFactor(targetNode) < -1) && (targetNode.right.key > key)) {
             targetNode.right = rotateRight(targetNode.right);
             return rotateLeft(targetNode);
         }
        return targetNode;
        // When the targetNode.key == key, return the targetNode itself.
     }
}
