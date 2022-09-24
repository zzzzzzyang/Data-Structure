package Map;

import java.util.Scanner;

public class test {
    static int [] father;

    private static int Find_Set(int x){
        while (x != father[x])
            x = father[x];
        return x;
    }

    static void Union(int x, int y){
        int f1 = Find_Set(x);
        int f2 = Find_Set(y);
        if(f1!=f2){
            father[x] = y;
        }
    }

    static int path(int x, int y){
        int count = 0;
        while (x != father[x]) {
            x = father[x];
            count++;
        }
        while (y != father[y]) {
            y = father[y];
            count++;
        }
        return count;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        father = new int[n+1];
        for (int i = 0; i < father.length; i++) {
            father[i] = i;//根据实际情况指定的父节点可变化
        }
        for(int i = 1; i <= m; i++){
            sc.nextLine();
            String s = sc.nextLine();
            String[] strIn = s.trim().split(" ");  // 以空格分割
            int a = Integer.parseInt(strIn[0]);
            for(int j=1;j<strIn.length;j++){
                //int x = Find_Set(a);
                int b = Integer.parseInt(strIn[j]);
                //int y = Find_Set(b);
                Union(b, a);
            }
        }
        int p = sc.nextInt();
        int q = sc.nextInt();
        if (Find_Set(p)==Find_Set(q)){
            int path = path(p, q);
            System.out.println(path);
        }else
            System.out.println(-1);
    }
}
