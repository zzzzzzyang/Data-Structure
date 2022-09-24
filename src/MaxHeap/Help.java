package MaxHeap;

public class Help {

    public static void main(String[] args) {
        int a = (int) (3 * Math.pow(10, 20));
        System.out.println(a);

        // -2^31 + (3e9 - 2^31) - 1
        int b = (int) (3 * Math.pow(10, 8)) * 10;
        System.out.println(b);

        short c = (short) (3 * Math.pow(10, 8));
        System.out.println(c);
    }
}
