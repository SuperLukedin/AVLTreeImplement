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

    public void show(AVLTreeNode node) {
        if (node != null)
        {
            System.out.print(node.key + " ");
            show(node.left);
            show(node.right);
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

     public AVLTreeNode getInorderSuccessor(AVLTreeNode targetNode) {
        AVLTreeNode curr = targetNode;
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr;
     }

     public AVLTreeNode remove(AVLTreeNode root, int key) {
        if (root == null) {
            return root;
        }
        if (root.key < key) {
            root.right = remove(root.right, key);
        } else if (root.key > key) {
            root.left = remove(root.left, key);
        } else {
            if ((root.left != null) && (root.right) != null) {
                AVLTreeNode newRoot = getInorderSuccessor(root.right);
                root.key = newRoot.key;
                root.right = remove(root.right, newRoot.key);
            } else {
                AVLTreeNode newTempNode = null;
                if (root.left == newTempNode) {
                    newTempNode = root.right;
                } else {
                    newTempNode = root.left;
                }
                if (newTempNode == null) {
                    newTempNode = root;
                    root = null;
                } else {
                    root = newTempNode;
                }
            }
        }
        if (root == null) {
            return root;
        }
        root.height = 1 + Math.max(height(root.left), height(root.right));
         if (getBalanceFactor(root) > 1 && getBalanceFactor(root.left) >= 0)
             return rotateRight(root);
         if (getBalanceFactor(root) < -1 && getBalanceFactor(root.right) <= 0)
             return rotateLeft(root);
         if (getBalanceFactor(root) > 1 && getBalanceFactor(root.left) < 0)
         {
             root.left = rotateLeft(root.left);
             return rotateRight(root);
         }
         if (getBalanceFactor(root) < -1 && getBalanceFactor(root.right) > 0)
         {
             root.right = rotateRight(root.right);
             return rotateLeft(root);
         }
         return root;
     }
     public int closestKeyAfter (int key) {
        int goal;
        int min = Integer.MAX_VALUE;
        if (root == null) {
            return  -1;
        }

     }
}
