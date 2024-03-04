public class Main {
    public static void main(String[] args) {


        AvlTree<Integer> testTree = new AvlTree<>(0);
        for (int i = 20; i > 14; i--) {
            testTree.insert(i);
        }
        for (int i = 1; i < 12; i+=3) {
            testTree.insert(i);
        }
        for (int i = 15; i < 400; i*=3) {
            testTree.insert(i);
        }

        TreePrinter.print((PrintableNode) testTree.getNodeRoot());

        testTree.deleteValue(17);
        testTree.deleteValue(1);
        testTree.deleteValue(15);

        TreePrinter.print((PrintableNode) testTree.getNodeRoot());


        AvlTree<String> arbolS = new AvlTree<>("Ernesto");
        arbolS.insert("Pedro");
        arbolS.insert("Nestor");
        arbolS.insert("Alejandro");
        arbolS.insert("Xavier");
        arbolS.insert("Carlos");
        arbolS.insert("Valentin");
        arbolS.insert("Manuel");


        TreePrinter.print((PrintableNode) arbolS.getNodeRoot());
        arbolS.deleteValue("Pedro");
        arbolS.deleteValue("Xavier");
        arbolS.deleteValue("Valentin");
        TreePrinter.print((PrintableNode) arbolS.getNodeRoot());


    }
}