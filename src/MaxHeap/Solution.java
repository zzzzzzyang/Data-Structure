package MaxHeap;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    class Freq implements Comparable<Freq> {
        int e;
        int freq;

        public Freq(int e, int freq){
            this.e = e;
            this.freq = freq;
        }


        // 频率小的优先级更大
        @Override
        public int compareTo(Freq another) {
            if (this.freq < another.freq)
                return 1;
            else if (this.freq > another.freq) {
                return -1;
            }else
                return 0;
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums)
            map.put(num, map.getOrDefault(num, 0) + 1);
        PriorityQueue<Freq> pq = new PriorityQueue<Freq>();
        for(Integer key : map.keySet()) {
            if (pq.getSize() < k)
                pq.enqueue(new Freq(key, map.get(key)));
            else if (pq.getFront().freq < map.get(key)) {
                pq.dequeue();
                pq.enqueue(new Freq(key, map.get(key)));
            }
        }
        int[] res = new int[pq.getSize()];
        int i = 0;
        while (!pq.isEmpty()){
            res[i++] = pq.dequeue().e;
        }
        return res;
    }
}
