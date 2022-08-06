package Array;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Array_test<Integer> arr = new Array_test<Integer>();
        for(int i=0;i<10;i++){
            arr.addLast(i);
        }
        System.out.println(arr);
        arr.addFirst(1);
        System.out.println(arr);
    }

}
