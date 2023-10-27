package ds_hw3.BST;

public class BSTreeTest {
    public static void main(String[] args) {
        Dictionary<Integer, String> bst = new BSTree<>();
        System.out.println("----- Binary Search Tree -----");
        bst.insert(3, "three");
        bst.insert(4, "four");
        bst.insert(5, "five");
        bst.insert(6, "six");
        bst.insert(7, "seven");
        bst.insert(8, "eight");
        bst.insert(10, "ten");
        bst.insert(9, "nine");
        bst.insert(11,"eleven");

        System.out.println("Size Before : " + bst.size());
        System.out.println("RemoveAny : " + bst.removeAny());
        System.out.println("Remove : " + bst.remove(7));

        System.out.println(bst.find(3));
        System.out.println(bst.find(6));
        System.out.println(bst.find(7));
        System.out.println(bst.find(8));
        System.out.println(bst.find(9));
        System.out.println(bst.find(10));
        System.out.println(bst.find(11));

        System.out.println("Size After: " + bst.size());
    }
}
