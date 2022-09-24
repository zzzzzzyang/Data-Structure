package UnionFind;

public class UnionFind2 implements UF{

    // parent[i]表示节点i的父节点
    private int[] parent;

    public UnionFind2(int size){
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    // 查找元素p所对应的集合的编号
    private int find(int p){
        if (p < 0 || p >= parent.length)
            throw new IllegalArgumentException("index is illegal");
        while (parent[p] != p)
            p = parent[p];
        return p;
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

        parent[qRoot] = pRoot;
    }

    @Override
    public int getSize() {
        return parent.length;
    }
}
