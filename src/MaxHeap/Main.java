package MaxHeap;

import java.util.PriorityQueue;
import java.util.Random;

public class Main {
    private static double test(Integer[] testData, boolean isHeapify){
        long startTime = System.nanoTime();

        MaxHeap<Integer> maxHeap;
        if(isHeapify){
            maxHeap = new MaxHeap<Integer>(testData);
        }else {
            maxHeap = new MaxHeap<Integer>();
            for (int i = 0; i < testData.length; i++) {
                maxHeap.add(testData[i]);
            }
        }

        int[] arr = new int[testData.length];
        for (int i = 0; i < testData.length; i++) {
            arr[i] = maxHeap.extractMax();
        }
        for (int i = 1; i < testData.length; i++) {
            if(arr[i-1] < arr[i])
                throw new IllegalArgumentException("error");
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1e9;
    }

    public static void main(String[] args) {
        int n = 2000000;
        MaxHeap<Integer> maxHeap = new MaxHeap<Integer>();
        Random random = new Random();
        Integer[] testData = new Integer[n];
        for (int i = 0; i < n; i++) {
            testData[i] = random.nextInt(Integer.MAX_VALUE);
        }
        double time1 = test(testData, false);
        System.out.println("without heapify: " + time1 + "s");

        double time2 = test(testData, true);
        System.out.println("with heapify: " + time2 + "s");

    }
}
