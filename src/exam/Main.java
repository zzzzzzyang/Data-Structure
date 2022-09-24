package exam;

public class Main {

    public static void main(String[] args) {
        int[] temperatureA = new int[]{1,-15,3,14,-1,4,35,36};
        int[] temperatureB = new int[]{-15,32,20,9,33,4,-1,-5};
        System.out.println(temperatureTrend(temperatureA, temperatureB));
    }

    public static int temperatureTrend(int[] temperatureA, int[] temperatureB) {
        int left = 0;
        int right = 1;
        int res = 0;
        while(right < temperatureA.length){
            int flagAN = getFlag(temperatureA[right], temperatureA[right-1]);
            int flagBN = getFlag(temperatureB[right], temperatureB[right-1]);
            if (flagAN != flagBN) {
                res = Math.max(res, right-1-left);
                if (right - left <= 1){
                    left = right;
                    right += 1;
                }else
                    left = right - 1;
            }else
                right ++;

        }
        return Math.max(res, right-1-left);
    }

    private static int getFlag(int a, int b){
        if (a==b)
            return 0;
        else if(a > b)
            return 1;
        else
            return -1;
    }
}
