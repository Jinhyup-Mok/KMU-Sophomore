package ds_hw2.tree;

public class TreeTest {
    public static void main(String[] args) {
        System.out.println("Test your trees here!");

        /* Implements your preorder, inorder and, postorder traversal functions here! */
        BinNode<String> d = new LeafNode<>("D");
        BinNode<String> g = new LeafNode<>("G");
        BinNode<String> h = new LeafNode<>("H");
        BinNode<String> i =new LeafNode<>("I");
        BinNode<String> e = new InternalNode<>("E", null, g);
        BinNode<String> b = new InternalNode<>("B", d, e);
        BinNode<String> f = new InternalNode("F", h, i);
        BinNode<String> c = new InternalNode<>("C", null, f);
        BinNode<String> a = new InternalNode<>("A", b, c);

        preorder(a);
        System.out.println();
        inorder(a);
        System.out.println();
        postorder(a);
        System.out.println();
    }

    public static <E> void preorder (BinNode<E> root) {
        if (root == null) return;
        System.out.print(root.element()); // visit
        preorder(root.left());
        preorder(root.right());
    }

    public static <E > void inorder (BinNode <E> root) {
        if (root == null) return;
        inorder(root.left());
        System.out.print(root.element()); // visit
        inorder(root.right());
    }

    public static <E > void postorder (BinNode <E> root) {
        if (root == null) return;
        postorder(root.left());
        postorder(root.right());
        System.out.print(root.element());
    }
}
