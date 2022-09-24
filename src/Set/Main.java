package Set;

import java.util.*;

public class Main {
    public static void main(String[] args) {

//        HashSet<Integer> hashSet = new HashSet<>();
//        TreeSet<Integer> treeSet = new TreeSet<>();
//        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
//        Map<Integer, Integer> hashtable = new Hashtable<>();
//        Map<Integer, Integer> hashmap= new HashMap<>();
//        hashmap.put(null, null);
        Random random = new Random();

        int n = 1000;
        ArrayList<String> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String str = usingMath(random.nextInt(10));
            arr.add(str);
        }
        Collections.sort(arr);

        LinkedListSet<String> linkedListSet = new LinkedListSet<String>();
        long startTime = System.nanoTime();

        for (String str : arr) {
            if (linkedListSet.contains(str))
                continue;
            else
                linkedListSet.add(str);
        }
        for (String str : arr) {
            linkedListSet.contains(str);
            linkedListSet.remove(str);
        }

        long endTime = System.nanoTime();

        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("total word: " + linkedListSet.getSize());
        System.out.println("LinkedListSet total time: " +  time + "s");

        BSTSet<String> bstSet = new BSTSet<String>();
        startTime = System.nanoTime();

        for (String str : arr) {
            if (bstSet.contains(str))
                bstSet.contains(str);
            else
                bstSet.add(str);
        }
        for (String str : arr) {
            bstSet.contains(str);
            bstSet.remove(str);
        }

        endTime = System.nanoTime();

        time = (endTime - startTime) / 1000000000.0;
        System.out.println("total word: " + bstSet.getSize());
        System.out.println("BSTSet total time: " +  time + "s");

        AVLSet<String> avlSet = new AVLSet<String>();
        startTime = System.nanoTime();

        for (String str : arr) {
            if (avlSet.contains(str))
                continue;
            else
                avlSet.add(str);
        }
        for (String str : arr) {
            avlSet.contains(str);
            avlSet.remove(str);
        }

        endTime = System.nanoTime();

        time = (endTime - startTime) / 1000000000.0;
        System.out.println("total word: " + avlSet.getSize());
        System.out.println("AVLSet total time: " +  time + "s");
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
