package fr.unice.abr;

import java.util.Random;

public class TestBST {

    public static void main(String[] args) {
        Random gen = new Random(1000);

        BinarySearch bst = new BinarySearch(42);

        for(int i = 1; i<20; i++)
            bst.insert(gen.nextInt(100));

        bst.insert(50);
        bst.insert(2021);

        System.out.println(bst);
        System.out.println("42? should be true as it is root: " + bst.test(42));
        System.out.println("50? should be true: " + bst.test(50));
        System.out.println("2021? should be true: " + bst.test(2021));
        System.out.println("-10? should be false as only positive values: " + bst.test(-10));
        System.out.println("200? should be false as only value < 100: " + bst.test(200));
    }
}
