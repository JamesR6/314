public class test {
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(5);
        tree.iterativeAdd(3);
        tree.add(7);
        tree.iterativeAdd(6);
        tree.add(2);
        tree.add(4);

        tree.remove(3);

        tree.printTree();



        System.out.println(tree.size());

    }
}
