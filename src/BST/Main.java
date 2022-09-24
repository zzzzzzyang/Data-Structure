package BST;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        char[] chars = s.toCharArray();

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            if(stack.isEmpty() || chars[i] != stack.peek()){
                stack.push(chars[i]);
            }else if(chars[i] == stack.peek())
                stack.pop();
        }
        if(stack.isEmpty())
            System.out.println("0");
        else{
            Stack<Character> stack2 = new Stack<>();
            while (!stack.isEmpty()){
                stack2.push(stack.pop());
            }
            while(!stack2.isEmpty()){
                System.out.println(stack2.pop());
            }
        }

//
//        BST<Integer> bst = new BST<Integer>();
//
//        Random random = new Random();
//        int n = 5;
//        for (int i = 0; i < n; i++) {
//            bst.add_(random.nextInt(20));
//        }
//
//        bst.postOrder();
//        bst.postOrderNR();

//        List<Integer> list = new ArrayList<>();
//        while (!bst.isEmpty())
//            list.add(bst.removeMax());
//        System.out.println(list);
//        int[] nums = {5, 3, 6, 8, 4, 2};
//        for (int num: nums) {
//            bst.add_(num);
//        }
//        System.out.println(bst.floor(1));
//        System.out.println(bst.ceil(7));
//        bst.preOrder();
//        System.out.println();
//
//        bst.preOderNR1();
//        System.out.println();
//
//        bst.preOderNR2();
////        System.out.println(bst);

        
    }

}
