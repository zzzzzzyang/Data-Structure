package Map;

import java.util.*;

public class helper {

    static public int evalRPN(String[] tokens) {
        Deque<Integer> deque = new LinkedList<>();
        for(String str : tokens){
            if(str == "+"  || str == "-" || str == "*" || str == "/"){
                int op1 = deque.removeFirst();
                int op2 = deque.removeFirst();
                if(str == "+") deque.addFirst(op2 + op1);
                if(str == "-") deque.addFirst(op2 - op1);
                if(str == "*") deque.addFirst(op2 * op1);
                if(str == "/") deque.addFirst(op2 / op1);
            }else{
                deque.addFirst(Integer.valueOf(str));
            }
        }
        return deque.removeFirst();
    }

    public static void main(String[] args) {
        String[] s = {"2", "1", "+", "3", "*"};
        int res = evalRPN(s);
        System.out.println(res);
    }
}
