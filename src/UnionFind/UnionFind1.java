package UnionFind;


// QuickFind
// 基于数组模拟并查集的实现
public class UnionFind1 implements UF{

    private int ids[];

    public UnionFind1(int size){
        ids = new int[size];
        // 初始化：每个元素都各自属于一个集合
        for (int i = 0; i < size; i++) {
            ids[i] = i;
        }
    }

    // 查找元素p所对应的集合的编号
    private int find(int p){
        if (p < 0 || p >= ids.length)
            throw new IllegalArgumentException("index is illegal");
        return ids[p];
    }

    // 查看元素p和元素q是否在一个集合中O(1)
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // 合并元素p和元素q所属的集合 O(n)
    @Override
    public void unionElements(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        if (pID == qID)
            return;

        for(int i=0; i < ids.length; i++)
            if (ids[i] == qID)
                ids[i] = pID;
    }

    @Override
    public int getSize() {
        return ids.length;
    }
}
