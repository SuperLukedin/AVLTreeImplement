package orderedDictionaryADT;

public class OrderedDictionaryAvlTreeTest {
    public static void main(String[] args) {
        AVLTreeNode root = new AVLTreeNode(40, 22);
        OrderedDictionaryAvlTree tree = new OrderedDictionaryAvlTree(root);
        tree.insertElement(33,22);
        tree.insertElement(43,32);
        tree.insertElement(7,12);
        tree.insertElement(88,13);
        tree.insertElement(78,66);
        tree.insertElement(7,12);
        tree.insertElement(47,12);
        tree.insertElement(71,12);
        tree.insertElement(28,12);
        tree.InOrderShow(root);
        tree.removeElement(4);
        tree.removeElement(94);
        tree.removeElement(43);
        tree.InOrderShow(root);
    }
}
