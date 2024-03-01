public class Main {
    public static void main(String[] args) {

        AvlTree<Double> testTree = new AvlTree<>(6.0);
        testTree.insert(7.0);
        testTree.insert(9.0);
        testTree.insert(8.5);
        testTree.insert(10.0);
        testTree.insert(7.5);
        testTree.insert(13.0);
        testTree.insert(8.0);



        System.out.println(testTree);
        System.out.println(((AvlTree.AvlNode<Double>)testTree.getNodeRoot().getRight()).height);
        System.out.println(((AvlTree.AvlNode<Double>)testTree.getNodeRoot().getLeft()).height);
    }
}