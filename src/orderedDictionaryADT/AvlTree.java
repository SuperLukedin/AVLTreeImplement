package orderedDictionaryADT;

import java.util.ArrayList;

public class AvlTree<V> {
    public AVLTreeNode<V> root;
    public ArrayList<Integer> visitedList;

    public AvlTree(AVLTreeNode root, ArrayList<Integer> theList) {    //Constructor
        this.root = root;
        this.visitedList = theList;
    }

    public AVLTreeNode getRoot() {
        return root;
    }

    public AVLTreeNode find (AVLTreeNode node, int key) {
        if (node == null) {
            return null;
        } else if (node.key == key){
            return node;
        } else if (node.key < key) {
            return find(node.right, key);
        } else {
            return find(node.left, key);
        }
    }
    public AVLTreeNode findAndVisitedArray (AVLTreeNode node, int key) {
        if (node == null) {
            return null;
        } else if (node.key == key){
            System.out.println("Nodes had visted: " + visitedList);
            return node;
        } else if (node.key < key) {
            visitedList.add(node.key);
            return findAndVisitedArray(node.right, key);
        } else {
            visitedList.add(node.key);
            return findAndVisitedArray(node.left, key);
        }
    }
    public void showList (ArrayList visitedList) {
        System.out.println(visitedList);
    }

    public boolean isFound (AVLTreeNode node, int key) {
        if (find(node, key) != null) {
            return true;
        } else {
            return false;
        }
    }

    public int height (AVLTreeNode node) {
        if (node == null) {
            return -1;
        } else {
            return node.height;
        }
    }

    public void InOrderShow (AVLTreeNode node) {
        System.out.println("Inorder traversal of the tree:");
        show(node);
        System.out.printf("\n");
    }

    public void show(AVLTreeNode node) {
        if (node != null)
        {
            show(node.left);
            System.out.print(node.key + " ");
            show(node.right);
        }
    }

     public AVLTreeNode rotateRight (AVLTreeNode targetNode) {
        //AVLTreeNode a = targetNode.left;
//        targetNode.left = a.right;
//        a.right = targetNode;
         AVLTreeNode tempNode1 = targetNode.left;
         AVLTreeNode tempNode2 = tempNode1.right;
         tempNode1.right = targetNode;
         targetNode.left = tempNode2;

         //Update height for targetNode and a with the highest child node(either left or right) + 1;
        targetNode.height = Math.max(height(targetNode.left), height(targetNode.right)) + 1;
        tempNode1.height = Math.max(height(tempNode1.left), height(tempNode1.right)) + 1;
        return tempNode1;    // position of targetNode was replaced by tempNode1.
     }

     public AVLTreeNode rotateLeft (AVLTreeNode targetNode) {
//        AVLTreeNode a = targetNode.right;
//        targetNode.right = a.left;
//        a.left = targetNode;
         AVLTreeNode tempNode3 = targetNode.right;
         AVLTreeNode tempNode4 = tempNode3.left;
         tempNode3.left = targetNode;
         targetNode.right = tempNode4;

        //Update height for targetNode and a with the highest child node(either left or right) + 1;
        targetNode.height = Math.max(height(targetNode.left), height(targetNode.right)) + 1;
        tempNode3.height = Math.max(height(tempNode3.left), height(tempNode3.right)) + 1;
        return tempNode3;
     }

     public int getBalanceFactor (AVLTreeNode targetNode) {
        if (targetNode == null) {
            return 0;
        }
        return height(targetNode.left) - height(targetNode.right);
     }

     public AVLTreeNode insertion (AVLTreeNode targetNode, int key, V elem) {
        if (targetNode == null) {
            return new AVLTreeNode(key, elem);
        }
        if (targetNode.key > key) {
            targetNode.left = insertion(targetNode.left, key, elem);
        } else if (targetNode.key < key) {
            targetNode.right = insertion(targetNode.right, key, elem);
        } else {
            System.out.println("Duplicated key " + "'"+ key + "' cannot be inserted.\n");
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
}
