package exam;

import java.util.HashMap;
import java.util.Map;

public class test2 {
    public static void main(String[] args) {
        int n = 3;
        int k = 1;
        int[] arr = {1,2,2};

        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        int l = 0;
        int start = 0;
        while (l < n){
            map.put(arr[start], map.getOrDefault(arr[start], 0) + 1);
            int maxNum = map.get(arr[l]);

            for (int i = l+1; i <= start; i++) {
                if (map.get(arr[i]) > maxNum){
                    maxNum = map.get(arr[i]);
                }
            }

            if (maxNum >= k)
                res ++;

            start++;
            if(start >= n){
                map.clear();
                l++;
                start = l;
            }


        }
        System.out.println(res);
    }

}
