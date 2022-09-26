package HashTable;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();

        int n = 1000000;
        ArrayList<String> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String str = usingMath(random.nextInt(10));
            arr.add(str);
        }
//        Collections.sort(arr);

        BST<String, Integer> bst = new BST<String, Integer>();
        long startTime = System.nanoTime();

        for (String str : arr) {
            if (bst.contains(str))
                bst.set(str, bst.get(str) + 1);
            else
                bst.add(str, 1);
        }
        for (String str : arr) {
            bst.contains(str);
        }

        long endTime = System.nanoTime();

        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("total word: " + bst.getSize());
        System.out.println("BST total time: " +  time + "s");

        AVLTree<String, Integer> avlTree = new AVLTree<String, Integer>();
        startTime = System.nanoTime();

        for (String str : arr) {
            if (avlTree.contains(str))
                avlTree.set(str, avlTree.get(str) + 1);
            else
                avlTree.add(str, 1);
        }
        for (String str : arr) {
            avlTree.contains(str);
        }

        endTime = System.nanoTime();

        time = (endTime - startTime) / 1000000000.0;
        System.out.println("total word: " + avlTree.getSize());
        System.out.println("AVLTree total time: " +  time + "s");



    }

    static String usingMath(int length) {
        String alphabetsInUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetsInLowerCase = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        // create a super set of all characters
        String allCharacters = alphabetsInLowerCase + alphabetsInUpperCase + numbers;
        // initialize a string to hold result
        StringBuffer randomString = new StringBuffer();
        // loop for 10 times
        for (int i = 0; i < length; i++) {
            // generate a random number between 0 and length of all characters
            int randomIndex = (int)(Math.random() * allCharacters.length());
            // retrieve character at index and add it to result
            randomString.append(allCharacters.charAt(randomIndex));
        }
        return randomString.toString();
    }
}
