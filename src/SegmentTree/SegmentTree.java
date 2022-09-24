package SegmentTree;


// 线段树（将线性的区间转化成一棵树）
// 查询时间复杂度由O(n)转化为O(logn)
public class SegmentTree<E>{
    private E[] tree;
    private E[] data;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger){
        this.merger = merger;
        data = (E[])new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        tree = (E[]) new Object[4 * arr.length];
        buildSegmentTree(0, 0, data.length-1);
    }

    private void buildSegmentTree(int treeIndex, int l, int r){
        if(l == r){
            tree[treeIndex] = data[l];
            return;
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid+1, r);
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public int getSize(){
        return data.length;
    }

    public E get(int index){
        if(index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is Illegal");
        return data[index];
    }

    private int leftChild(int index){
        return 2*index + 1;
    }

    private int rightChild(int index){
        return 2*index + 2;
    }

    // 返回区间[queryL, queryR]的值
    public E query(int queryL, int queryR){
        if(queryL < 0 || queryL >= data.length
            || queryR < 0 || queryR >= data.length || queryL > queryR)
            throw new IllegalArgumentException("Index is illegal!");

        return query(0, 0, data.length-1, queryL, queryR);
    }

    private E query(int treeIndex, int l, int r, int queryL, int queryR){
        if (l == queryL && r == queryR){
            return tree[treeIndex];
        }
        int mid = l + (r - l) / 2;
        if (mid >= queryR)
            return query(leftChild(treeIndex), l, mid, queryL, queryR);
        else if (mid < queryL)
            return query(rightChild(treeIndex), mid+1, r, queryL, queryR);
        else{
            E left = query(leftChild(treeIndex), l, mid, queryL, mid);
            E right = query(rightChild(treeIndex), mid+1, r, mid+1, queryR);
            return merger.merge(left, right);
        }
    }

    // 将index位置的值更新为e
    public void set(int index, E e){
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal");
        data[index] = e;
        set(0, 0, data.length-1, index, e);
    }

    private void set(int treeIndex, int l, int r, int index, E e){
        if (l == r){
            tree[treeIndex] = e;
            return;
        }
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if (index <= mid)
            set(leftTreeIndex, l, mid, index, e);
        else
            set(rightTreeIndex, mid+1, r, index, e);
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null)
                sb.append(tree[i]);
            else
                sb.append("null");
            if(i != tree.length - 1)
                sb.append(", ");
        }
        sb.append(']');
        return sb.toString();
    }
}
