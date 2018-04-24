package orderedDictionaryADT;

import java.util.ArrayList;

public class AvlTreeTest {
    public static void main(String[] args) {
        AVLTreeNode root = new AVLTreeNode(12, 55);
        ArrayList<Integer> anotherList = new ArrayList<Integer>();
        AvlTree tree = new AvlTree(root, anotherList);
        tree.root = tree.insertion(tree.root, 30, 22);
        tree.root = tree.insertion(tree.root, 40,88);
        tree.root = tree.insertion(tree.root, 20, 90);
        tree.root = tree.insertion(tree.root, 2, 11);
        tree.root = tree.insertion(tree.root, 12, 80);
        tree.root = tree.insertion(tree.root, 55, 43);
        tree.root = tree.insertion(tree.root, 70, 90);
        tree.root = tree.insertion(tree.root, 80,33);
        tree.root = tree.insertion(tree.root, 90,12);
        tree.InOrderShow(tree.root);
        tree.remove(tree.root, 30);
        tree.InOrderShow(tree.root);
    }
}
