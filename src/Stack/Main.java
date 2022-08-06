package Stack;

import java.util.Random;

public class Main {

    private static double testStack(Stack<Integer> stack, int opCount){
        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount; i++)
            stack.push(random.nextInt(Integer.MAX_VALUE));
        for (int i = 0; i < opCount; i++)
            stack.pop();
        long endTime = System.nanoTime();

        return (endTime - startTime) / 1e9;
    }

    public static void main(String[] args) {

        int optCount = 100000000;
        ArrayStack<Integer> arrayStack = new ArrayStack<Integer>();
        double time1 = testStack(arrayStack, optCount);
        System.out.println("ArrayStack, time:" + time1 + " s");

        LinkedListStack<Integer> linkedListStack = new LinkedListStack<Integer>();
        double time2 = testStack(linkedListStack, optCount);
        System.out.println("LinkedListStack, time:" + time2 + " s");

    }
}
