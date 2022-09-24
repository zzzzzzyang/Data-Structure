package exam;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {

    public static void main(String[] args) {
        int[][] path = new int[][]{
                new int[]{0,3},
                new int[]{1,0},
                new int[]{1,3},
                new int[]{2,0},
                new int[]{3,0},
                new int[]{3,2},
        };
        System.out.println(transportationHub(path));
    }

    public static int transportationHub(int[][] path) {
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int i = 0; i < path.length; i++) {
            set1.add(path[i][0]);
            set2.add(path[i][1]);
            map.put(path[i][1], map.getOrDefault(path[i][1],0) +1);
        }
        int target = -1;
        for (Integer num : set2)
            if (!set1.contains(num)) {
                target = num;
            }
        if (target == -1) return -1;
        for (int i = 0; i < path.length; i++) {
            if (path[i][1] == target)
                set1.remove(path[i][0]);
        }
        return set1.isEmpty() ? target : -1;
    }
}
