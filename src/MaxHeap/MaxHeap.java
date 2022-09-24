package MaxHeap;

public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MaxHeap(int capacity){
        data = new Array<E>(capacity);
    }

    public MaxHeap(){
        data = new Array<E>();
    }

    public MaxHeap(E[] arr){
        data = new Array<E>(arr);
        for (int i = parent(arr.length-1); i >= 0; i--) {
            shiftDown(i);
        }
    }

    public int size(){
        return data.getSize();
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的父节点的索引
    private int parent(int index){
        if(index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent");
        return (index-1) / 2;
    }

    private int leftChild(int index){
        return index * 2 + 1;
    }

    private int rightChild(int index){
        return index * 2 + 2;
    }

    public void add(E e){
        data.addLast(e);
        shiftUp(data.getSize()-1);
    }

    private void shiftUp(int idx){
        while (idx > 0 && data.get(parent(idx)).compareTo(data.get(idx)) < 0){
            data.swap(idx, parent(idx));
            idx = parent(idx);
        }
    }


    public E findMax(){
        if(data.isEmpty())
            throw new IllegalArgumentException("Heap is empty");
        return data.get(0);
    }

    public E extractMax(){
        E max = findMax();
        data.swap(0, data.getSize()-1);
        data.removeLast();
        shiftDown(0);
        return max;
    }

    private void shiftDown(int idx){
        int half = data.getSize() >>> 1;
        //while (leftChild(idx) < data.getSize()){
        while (idx < half){
            int leftChild = leftChild(idx);
            if(leftChild + 1 < data.getSize() && data.get(leftChild).compareTo(data.get(leftChild+1)) < 0)
                leftChild ++;

            if (data.get(leftChild).compareTo(data.get(idx)) <= 0)
                break;

            data.swap(idx, leftChild);
            idx = leftChild;
        }
    }

    // 取出堆中的最大元素，并添加元素e
    // 组合思路：extractMax() + add()  时间复杂度O(2*logn)
    // 优化思路：直接将e替换根节点，再执行shiftDown() 时间复杂度O(logn)
    public E replace(E e){
        E max = findMax();
        data.set(0, e);
        shiftDown(0);
        return max;
    }

}
