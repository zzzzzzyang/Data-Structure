package LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList<Integer>();

        for (int i = 0; i < 5; i++) {
            linkedList.add_(i, i);
        }
        System.out.println(linkedList);

        linkedList.removeElementR(5);
        System.out.println(linkedList);
    }
}
