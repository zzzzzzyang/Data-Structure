package exam;

import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        int n = 6;
        int k = 2;
        int[] arr = {1,2,1,3,2,3};

        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        int l = 0;
        int start = 0;
        while (l < n){
            map.put(arr[start], map.getOrDefault(arr[start], 0) + 1);
            if(map.get(arr[l]) >= k){
                int num = map.get(arr[l]);
                boolean flag = true;
                for(int i=l+1;i<=start && flag;i++){
                    if (map.get(arr[i]) > num){
                        flag = false;
                    }
                }
                if (flag)
                    res ++;
                else{
                    map.clear();
                    l ++;
                    start = l;
                    continue;
                }
            }
            start ++;
            if(start >= n){
//                map.put(arr[l], map.get(arr[l])-1);
                map.clear();
                l++;
                start = l;

            }
        }
        System.out.println(res);
    }

}
