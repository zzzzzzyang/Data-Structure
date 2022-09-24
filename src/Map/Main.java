package Map;

import BST.BST;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();

        int n = 100000;
        ArrayList<String> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String str = usingMath(random.nextInt(10));
            arr.add(str);
        }

        LinkedListMap<String, Integer> linkedListMap = new LinkedListMap<String, Integer>();
        long startTime = System.nanoTime();

        for (String str : arr) {
            if (linkedListMap.contains(str))
                linkedListMap.set(str, linkedListMap.get(str) + 1);
            else
                linkedListMap.add(str, 1);
        }
        for (String str : arr) {
            linkedListMap.contains(str);
        }

        long endTime = System.nanoTime();

        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("total word: " + linkedListMap.getSize());
        System.out.println("LinkedListMap total time: " +  time + "s");

        BSTMap<String, Integer> bstMap = new BSTMap<String, Integer>();
        startTime = System.nanoTime();

        for (String str : arr) {
            if (bstMap.contains(str))
                bstMap.set(str, bstMap.get(str) + 1);
            else
                bstMap.add(str, 1);
        }
        for (String str : arr) {
            bstMap.contains(str);
        }

        endTime = System.nanoTime();

        time = (endTime - startTime) / 1000000000.0;
        System.out.println("total word: " + bstMap.getSize());
        System.out.println("BSTMap total time: " +  time + "s");

        AVLMap<String, Integer> avlMap = new AVLMap<String, Integer>();
        startTime = System.nanoTime();

        for (String str : arr) {
            if (avlMap.contains(str))
                avlMap.set(str, avlMap.get(str) + 1);
            else
                avlMap.add(str, 1);
        }
        for (String str : arr) {
            avlMap.contains(str);
        }

        endTime = System.nanoTime();

        time = (endTime - startTime) / 1000000000.0;
        System.out.println("total word: " + avlMap.getSize());
        System.out.println("AVLMap total time: " +  time + "s");
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



