package fr.unice.abr;

public class BinaryNode {
    private int value;
    private BinaryNode leftNode;
    private BinaryNode rightNode;

    // Visibilité protected : ne peut être initialisé que dans du code du même paquetage
    protected BinaryNode(int value, BinaryNode left, BinaryNode right) {
        this.value = value;
        leftNode = left;
        rightNode = right;
    }

    protected BinaryNode(int value) {
        this(value, null, null);
    }

    public BinaryNode getLeft() {
        return leftNode;
    }

    public BinaryNode getRight() {
        return rightNode;
    }

    public int getValue() {
        return value;
    }

    public void setLeft(BinaryNode l) {
        this.leftNode = l;
    }

    public void setRight(BinaryNode r) {
        this.rightNode = r;
    }

    public String toString() {
        if(leftNode==null && rightNode==null) return value+"";
        return "("+leftNode+" ["+value+"] "+rightNode+")";
    }
}
