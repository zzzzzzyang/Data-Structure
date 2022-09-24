package exam;

import java.util.LinkedList;
import java.util.List;

public class test3 {

    private static int count = 0;

    public static void main(String[] args) {
        String str = "1212";
        int k = 23;
        backtrack(str, k, 0);
        System.out.println(count);
    }

    private static void backtrack(String str, int k, int index){
        if (index >= str.length()) {
            return;
        }
        for (int i=index; i<str.length(); i++){
            int val = Integer.parseInt(str.substring(index, i+1));
            if(val < k){
                System.out.println(val);
                count ++;
            }
        }
        backtrack(str, k, index+1);
    }
}
