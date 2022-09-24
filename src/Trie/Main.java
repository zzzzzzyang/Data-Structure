package Trie;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();

        int n = 1000000;
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            String str = usingMath(random.nextInt(8));
            arr[i] = str;
        }

        BSTSet<String> set = new BSTSet<String>();
        long startTime = System.nanoTime();

        for (String str : arr) {
            set.add(str);
        }
        for (String str : arr) {
            set.contains(str);
        }

        long endTime = System.nanoTime();

        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("total word: " + set.getSize());
        System.out.println("BSTSet total time: " +  time + "s");



        TreeTrie treeTrie = new TreeTrie();
        startTime = System.nanoTime();

        for (String str : arr) {
            treeTrie.add(str);
        }
        for (String str : arr) {
            treeTrie.contains(str);
        }

        endTime = System.nanoTime();

        time = (endTime - startTime) / 1000000000.0;
        System.out.println("total word: " + treeTrie.getSize());
        System.out.println("Trie total time: " +  time + "s");

        HashTrie hashTrie = new HashTrie();
        startTime = System.nanoTime();

        for (String str : arr) {
            hashTrie.add(str);
        }
        for (String str : arr) {
            hashTrie.contains(str);
        }

        endTime = System.nanoTime();

        time = (endTime - startTime) / 1000000000.0;
        System.out.println("total word: " + hashTrie.getSize());
        System.out.println("HashTrie total time: " +  time + "s");


        ArrayTrie arrayTrie = new ArrayTrie();
        startTime = System.nanoTime();

        for (String str : arr) {
            arrayTrie.add(str);
        }
        for (String str : arr) {
            arrayTrie.contains(str);
        }

        endTime = System.nanoTime();

        time = (endTime - startTime) / 1000000000.0;
        System.out.println("total word: " + arrayTrie.getSize());
        System.out.println("ArrayTrie total time: " +  time + "s");

    }

    static String usingMath(int length) {
        String alphabetsInUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetsInLowerCase = "abcdefghijklmnopqrstuvwxyz";
//        String numbers = "0123456789";
        // create a super set of all characters
        String allCharacters = alphabetsInLowerCase;// + alphabetsInUpperCase + numbers;
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
