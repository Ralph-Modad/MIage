package fr.unice.abr;

public class BinarySearch {
    private BinaryNode root;

    public BinarySearch(int valueRoot) {
        root = new BinaryNode(valueRoot);
    }

    public void insert(int value) {
        root = insert(value, root);
    }

    public boolean test(int value) {
        return contains(value, root);
    }


    private boolean contains(int value, BinaryNode node) {
        if (node == null) {
            return false;
        }
        if (node.getValue() == value) {
            return true;
        }
        if (node.getValue() > value) {
            return contains(value, node.getLeft());
        }
        return contains(value, node.getRight());
    }

    private BinaryNode insert(int value, BinaryNode node) {
        if (node == null) {
            return new BinaryNode(value);
        }
        if (node.getValue() > value) {
            node.setLeft(insert(value, node.getLeft()));
        } else {
            node.setRight(insert(value, node.getRight()));
        }
        return node;
    }

}
