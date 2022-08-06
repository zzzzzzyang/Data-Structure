package Queue;

public class Main {
    public static void main(String[] args) {
//        ArrayQueue<Integer> arrayQueue = new ArrayQueue<Integer>();
//
//        for (int i = 0; i < 10; i++) {
//            arrayQueue.enqueue(i);
//            System.out.println(arrayQueue);
//            if(i % 3 == 2){
//                arrayQueue.dequeue();
//                System.out.println(arrayQueue);
//            }
//        }

        LoopQueue1<Integer> loopQueue = new LoopQueue1<Integer>();
        for (int i = 0; i < 10; i++) {
            loopQueue.enqueue(i);
            System.out.println(loopQueue);

            if(i % 3 == 2){
                loopQueue.dequeue();
                System.out.println(loopQueue);
            }
        }
    }
}
