package Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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

//        int optCount = 100000;
//        ArrayStack<Integer> arrayStack = new ArrayStack<Integer>();
//        double time1 = testStack(arrayStack, optCount);
//        System.out.println("ArrayStack, time:" + time1 + " s");
//
//        LinkedListStack<Integer> linkedListStack = new LinkedListStack<Integer>();
//        double time2 = testStack(linkedListStack, optCount);
//        System.out.println("LinkedListStack, time:" + time2 + " s");
//        System.out.println(10%3*2);
        Scanner input=new Scanner(System.in);
        int n = input.nextInt();
        String a = input.next();
        String b = input.next();

        System.out.println("n=" + n);
        System.out.println("a=" + a);
        System.out.println("b=" + b);

        char p[] = a.toCharArray();
        char q[] = b.toCharArray();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <n; i++) {
            sb.append(p[i]);
            sb.append(q[i]);
        }
        System.out.println(sb.toString());
    }
}
