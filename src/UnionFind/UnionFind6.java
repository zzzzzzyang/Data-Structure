package UnionFind;

public class UnionFind6 implements UF{

    // parent[i]表示节点i的父节点
    private int[] parent;
    private int[] rank;   // rank[i]表示以i为根的森林的层数

    public UnionFind6(int size){
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }

        rank = new int[size];
        for (int i = 0; i < size; i++) {
            rank[i] = 1;
        }
    }

    // 查找元素p所对应的集合的编号
    private int find(int p){
        if (p < 0 || p >= parent.length)
            throw new IllegalArgumentException("index is illegal");
        if (parent[p] != p) {
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }

    // 查看元素p和元素q是否在一个集合中 O(h)
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // 合并元素p和元素q所属的集合 O(h)
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;

        // 根据两个元素所在树的rank不同判断合并方向
        // 将rank低的集合合并在rank高的集合上
        if (rank[pRoot] < rank[qRoot]){
            parent[pRoot] = qRoot;
        }else if (rank[pRoot] > rank[qRoot]){
            parent[qRoot] = pRoot;
        }else {
            parent[qRoot] = pRoot;
            rank[pRoot] ++;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }
}
