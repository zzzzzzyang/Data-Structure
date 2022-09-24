package MaxHeap;

import java.util.ArrayList;
import java.util.List;

public class Array<E> {
    private E[] data;
    private int size;

    public Array(int capacity){
        data = (E[])new Object[capacity];
    }

    public Array(){
        this(10);
    }

    public Array(E[] arr){
        data = (E[])new Object[arr.length];
        for(int i=0;i< data.length;i++)
            data[i] = arr[i];
        size = data.length;
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

    public void addLast(E e){
        add(size, e);
    }

    public void addFirst(E e){
        add(0, e);
    }

    public void add(int index, E e){
        if(index<0 || index>size){
            throw new IllegalArgumentException("index Illegal");
        }
        if(size == data.length) {
            resize(2 * data.length);
        }
        for(int i=size-1;i>=index;i--){
            data[i+1] = data[i];
        }
        data[index] = e;
        size++;
    }

    private void resize(int newCapacity){
        E[] newData= (E[]) new Object[newCapacity];
        for(int i=0;i<size;i++){
            newData[i] = data[i];
        }
        data = newData;
    }

    public void swap(int i, int j){
        if(i < 0 || i >= size || j < 0 || j >= size)
            throw new IllegalArgumentException("Index is illegal");
        E temp = data[i];
        data[i] = data[j];
        data[j] = temp;
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

    public E get(int index){
        if(index<0 || index>=size){
            throw new IllegalArgumentException("index Illegal");
        }
        return data[index];
    }

    public void set(int index, E e){
        if(index<0 || index>=size){
            throw new IllegalArgumentException("index Illegal");
        }
        data[index] = e;
    }

    public boolean contains(E e){
        for(int i=0;i<size;i++){
            if(data[i].equals(e)){
                return true;
            }
        }
        return false;
    }

    public int find(E e){
        for(int i=0;i<size;i++){
            if(data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    public List<Integer> findAll(E e){
        List<Integer> indexList = new ArrayList<>();
        for(int i=0;i<size;i++){
            if(data[i].equals(e)){
                indexList.add(i);
            }
        }
        if (indexList.size() == 0){
            return null;
        }
        return indexList;
    }

    public E remove(int index){
        if(index<0 || index>=size){
            throw new IllegalArgumentException("index Illegal");
        }
        E res = data[index];
        for(int i=index+1;i<size;i++){
            data[i-1] = data[i];
        }
        size--;

        if(size == data.length /4 && data.length /2 != 0){
            resize(data.length / 2);
        }
        return res;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size-1);
    }

    public boolean removeEle(E e){
        int index = find(e);
        if(index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    public boolean removeAllEle(E e){
        boolean res = removeEle(e);
        if(res) {
            while (res) {
                res = removeEle(e);
            }
            return true;
        }
        return false;
    }
}
