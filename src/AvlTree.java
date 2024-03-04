import cu.edu.cujae.ceis.sectree.node.Node;
import cu.edu.cujae.ceis.tree.TreeNode;
import cu.edu.cujae.ceis.tree.binary.BinaryTree;
import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;

public class AvlTree<E extends Comparable<E>> extends BinaryTree<E> implements Balanceable {
    public AvlTree(E root) {
        super(root);
        this.root = new AvlNode<>(root);
    }

    public AvlTree() {
        super();

    }

    public static class AvlNode<E> extends BinaryTreeNode<E> implements PrintableNode {
        public int height = 0;

        public AvlNode(E valor) {
            super(valor);

        }

        @Override
        public PrintableNode getIzquierdo(){
            return (PrintableNode) getLeft();
        }

        @Override
        public PrintableNode getDerecho(){
            return (PrintableNode) getRight();
        }

        @Override
        public String toString() {
            return info.toString();
        }
    }

    // Método para insertar un valor en el árbol
    public void insert(E valor) {
        // Llamar al método recursivo que inserta el valor y devuelve la nueva raíz
        root = insert((AvlNode<E>) root, valor);
    }

    // Método recursivo que inserta un valor en el subárbol con raíz dada y devuelve la nueva raíz
    private AvlNode<E> insert(AvlNode<E> node, E valor) {

        if (node == null) {
            return new AvlNode<>(valor);
        }

        // Comparar el valor con el dato del nodo
        int cmp = valor.compareTo(node.getInfo());

        // Si el valor es menor que el dato del nodo, insertarlo en el subárbol izquierdo
        if (cmp < 0) {
            node.setLeft(insert((AvlNode<E>)node.getLeft(), valor));
        }
        // Si el valor es mayor que el dato del nodo, insertarlo en el subárbol derecho
        else if (cmp > 0) {
            node.setRight(insert((AvlNode<E>)node.getRight(), valor));

        }
        // Si el valor es igual que el dato del nodo, no hacer nada (no se permiten valores duplicados)

        // Actualizar la altura del nodo
        node.height = Math.max(height((AvlNode<E>)node.getLeft()), height((AvlNode<E>)node.getRight())) + 1;

        // Calcular el factor de equilibrio del nodo
        int balance = balance(node);

        // Si el nodo está desbalanceado hacia la izquierda y el valor es menor que el dato del hijo izquierdo, rotar a la derecha
        if (balance < -1 && valor.compareTo(node.getLeft().getInfo()) < 0) {
            return rightRotate(node);
        }

        // Si el nodo está desbalanceado hacia la derecha y el valor es mayor que el dato del hijo derecho, rotar a la izquierda
        if (balance > 1 && valor.compareTo(node.getRight().getInfo()) > 0) {
            return leftRotate(node);
        }

        // Si el nodo está desbalanceado hacia la izquierda y el valor es mayor que el dato del hijo izquierdo, rotar a la izquierda el hijo izquierdo y luego rotar a la derecha el nodo
        if (balance < -1 && valor.compareTo(node.getLeft().getInfo()) > 0) {
            node.setLeft(leftRotate((AvlNode<E>)node.getLeft()));
            return rightRotate(node);
        }

        // Si el nodo está desbalanceado hacia la derecha y el valor es menor que el dato del hijo derecho, rotar a la derecha el hijo derecho y luego rotar a la izquierda el nodo
        if (balance > 1 && valor.compareTo(node.getRight().getInfo()) < 0) {
            node.setRight(rightRotate((AvlNode<E>)node.getRight()));
            return leftRotate(node);
        }

        // Si el nodo está balanceado, devolverlo sin cambios
        return node;
    }

    // Método para rotar a la derecha un subárbol con raíz dada y devolver la nueva raíz
    private AvlNode<E> rightRotate(AvlNode<E> node) {
        // Guardar una referencia al hijo izquierdo del nodo
        AvlNode left = (AvlNode<E>)node.getLeft();

        // Guardar una referencia al subárbol derecho del hijo izquierdo
        AvlNode right = (AvlNode<E>)left.getRight();

        // Hacer que el hijo izquierdo sea la nueva raíz
        left.setRight(node);

        // Hacer que el subárbol derecho del hijo izquierdo sea el nuevo subárbol izquierdo del nodo
        node.setLeft(right);

        // Actualizar las alturas de los nodos afectados
        node.height = Math.max(height((AvlNode<E>)node.getLeft()), height((AvlNode<E>)node.getRight())) + 1;
        left.height = Math.max(height((AvlNode<E>)left.getLeft()), height((AvlNode<E>)left.getRight())) + 1;

        // Devolver la nueva raíz
        return left;
    }

    // Método para rotar a la izquierda un subárbol con raíz dada y devolver la nueva raíz
    private AvlNode leftRotate(AvlNode<E> node) {
        // Guardar una referencia al hijo derecho del nodo
        AvlNode right = (AvlNode<E>)node.getRight();

        // Guardar una referencia al subárbol izquierdo del hijo derecho
        AvlNode left = (AvlNode<E>)right.getLeft();

        // Hacer que el hijo derecho sea la nueva raíz
        right.setLeft(node);

        // Hacer que el subárbol izquierdo del hijo derecho sea el nuevo subárbol derecho del nodo
        node.setRight(left);

        // Actualizar las alturas de los nodos afectados
        node.height = Math.max(height((AvlNode<E>)node.getLeft()), height((AvlNode<E>)node.getRight())) + 1;
        right.height = Math.max(height((AvlNode<E>)right.getLeft()), height((AvlNode<E>)right.getRight())) + 1;

        // Devolver la nueva raíz
        return right;
    }

    // Método para obtener la altura de un nodo (si el nodo es nulo, devuelve -1)
    private int height(AvlNode<E> node) {
        return node == null ? -1 : node.height;
    }

    // Método para obtener el factor de equilibrio de un nodo (si el nodo es nulo, devuelve 0)
    private int balance(AvlNode<E> node) {
        return node == null ? 0 : height((AvlNode<E>)node.getRight()) - height((AvlNode<E>)node.getLeft());
    }

    // Método para eliminar un valor del árbol
    public void deleteValue(E value) {
        // Llamar al método recursivo que elimina el valor y devuelve la nueva raíz
        root = delete((AvlNode<E>) root, value);
    }

    // Método recursivo que elimina un valor del subárbol con raíz dada y devuelve la nueva raíz
    private AvlNode<E> delete(AvlNode<E> node, E value) {
        // Si el nodo es nulo, no hacer nada
        if (node == null) {
            return node;
        }

        // Comparar el valor con el dato del nodo
        int cmp = value.compareTo(node.getInfo());

        // Si el valor es menor que el dato del nodo, eliminarlo del subárbol izquierdo
        if (cmp < 0) {
            node.setLeft(delete((AvlNode<E>) node.getLeft(), value));;
        }
        // Si el valor es mayor que el dato del nodo, eliminarlo del subárbol derecho
        else if (cmp > 0) {
            node.setRight(delete((AvlNode<E>) node.getRight(), value)); 
        }
        // Si el valor es igual que el dato del nodo, eliminar el nodo
        else {
            // Si el nodo es una hoja, devolver nulo
            if (node.getLeft() == null && node.getRight()== null) {
                return null;
            }
            // Si el nodo tiene un solo hijo, devolver ese hijo
            else if (node.getLeft() == null) {
                return (AvlNode<E>) node.getRight();
            }
            else if (node.getRight()== null) {
                return (AvlNode<E>) node.getLeft();
            }
            // Si el nodo tiene dos hijos, reemplazar el dato del nodo por el mayor valor del subárbol izquierdo
            else {
                node.setInfo(getMax((AvlNode<E>)node.getLeft()));
                // Eliminar el nodo que contiene el mayor valor del subárbol izquierdo
                node.setLeft(delete((AvlNode<E>) node.getLeft(), node.getInfo()));
            }
        }

        // Actualizar la altura del nodo
        node.height = Math.max(height((AvlNode<E>) node.getLeft()), height((AvlNode<E>)node.getRight())) + 1;

        // Calcular el factor de equilibrio del nodo
        int balance = balance(node);

        // Si el nodo está desbalanceado hacia la izquierda y el factor de equilibrio del hijo izquierdo es 0 o -1, rotar a la derecha
        if (balance < -1 && balance((AvlNode<E>) node.getLeft()) <= 0) {
            return rightRotate(node);
        }

        // Si el nodo está desbalanceado hacia la derecha y el factor de equilibrio del hijo derecho es 0 o 1, rotar a la izquierda
        if (balance > 1 && balance((AvlNode<E>) node.getRight()) >= 0) {
            return leftRotate(node);
        }

        // Si el nodo está desbalanceado hacia la izquierda y el factor de equilibrio del hijo izquierdo es 1, rotar a la izquierda el hijo izquierdo y luego rotar a la derecha el nodo
        if (balance < -1 && balance((AvlNode<E>) node.getLeft()) > 0) {
            node.setLeft(leftRotate((AvlNode<E>) node.getLeft()));
            return rightRotate(node);
        }

        // Si el nodo está desbalanceado hacia la derecha y el factor de equilibrio del hijo derecho es -1, rotar a la derecha el hijo derecho y luego rotar a la izquierda el nodo
        if (balance > 1 && balance((AvlNode<E>) node.getRight()) < 0) {
            node.setRight(rightRotate((AvlNode<E>) node.getRight()));
            return leftRotate(node);
        }

        // Si el nodo está balanceado, devolverlo sin cambios
        return node;
    }

    // Método para obtener el mayor valor de un subárbol con raíz dada
    private E getMax(AvlNode<E> node) {
        // Si el nodo es nulo, devolver nulo
        if (node == null) {
            return null;
        }
        // Si el nodo no tiene hijo derecho, devolver su dato
        if (node.getRight() == null) {
            return node.getInfo();
        }
        // Si el nodo tiene hijo derecho, seguir buscando el mayor valor en el subárbol derecho
        return getMax((AvlNode<E>) node.getRight());
    }



    @Override
    public String toString() {
        // Se crea un StringBuilder vacío
        StringBuilder sb = new StringBuilder();
        // Se llama al método auxiliar con la raíz y el StringBuilder
        recorrerEntreorden((AvlNode<E>) root, sb);
        // Se retorna el contenido del StringBuilder como una cadena
        return sb.toString();
    }


    private void recorrerEntreorden(AvlNode<E> nodo, StringBuilder sb) {
        if (nodo != null) {
            // Se recorre el subárbol izquierdo
            recorrerEntreorden((AvlNode<E>) nodo.getLeft(), sb);
            // Se agrega el elemento del nodo al StringBuilder
            sb.append(nodo.getInfo());
            // Se agrega un espacio al StringBuilder
            sb.append(" ");
            // Se recorre el subárbol derecho
            recorrerEntreorden((AvlNode<E>)nodo.getRight(), sb);
        }
    }
}


