package Array;

import java.util.ArrayList;
import java.util.List;

public class Array_test<E> {
    private E[] data;
    private int size;

    public Array_test(int capacity){
        data = (E[])new Object[capacity];
    }

    public Array_test(){
        this(10);
    }

    public int getSize(){
        return size;
    }

    public int getCapacity(){
        return data.length;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void addLast(E e){ //O(1)
        add(e, size); // 连续执行n+1次，触发一次resize()，共执行n+1+n = 2n+1次操作 均摊时间复杂度为o(1)
    }

    public void addFirst(E e){ //O(n)
        add(e, 0);
    }

    public void add(E e, int idx){  //平均移动n+1/2次，O(n)
        if(idx < 0 || idx > size){
            throw new IllegalArgumentException("index Illegal");
        }
        if(size == data.length) {
            resize(data.length * 2);
        }
        for(int i=size-1; i>=idx; i--){
            data[i+1] = data[i];
        }
        data[idx] = e;
        size++;
    }

    public void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    public E remove(int idx){ //O(n)
        if(idx < 0 || idx >= size){
            throw new IllegalArgumentException("index Illegal");
        }
        E e = data[idx];
        for(int i=idx+1; i<size; i++){
            data[i-1] = data[i];
        }
        size--;

        if(size == data.length / 4 && data.length / 2 != 0){
            resize(data.length / 2);
        }
        return e;
    }

    public E removeLast(){
        return remove(size-1);
    }

    public E removeFirst(){
        return remove(0);
    }

    public E get(int idx){
        if(idx < 0 || idx >= size){
            throw new IllegalArgumentException("index Illegal");
        }
        return data[idx];
    }

    public void set(E e, int idx){
        if(idx < 0 || idx >= size){
            throw new IllegalArgumentException("index Illegal");
        }
        data[idx] = e;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
        res.append("[");
        for (int i = 0; i < size; i++){
            res.append(data[i]);
            if(i != size-1){
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }

    public boolean contains(E e){
        for (E ele: data) {
            if(ele.equals(e)){
                return true;
            }
        }
        return false;
    }

    public int find(E e){
        for (int i=0; i< size; i++) {
            if (data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    public List<Integer> findAll(E e){
        List<Integer> res = new ArrayList<>();
        for (int i=0; i< size; i++) {
            if (data[i].equals(e)){
                res.add(i);
            }
        }
        if(res.size()==0){
            res = null;
        }
        return res;
    }
}
