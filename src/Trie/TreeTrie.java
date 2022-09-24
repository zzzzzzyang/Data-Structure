package Trie;

import java.util.TreeMap;

// 字典树(前缀树)
public class TreeTrie {
    private class Node{
        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }

    private Node root;
    private int size;

    public TreeTrie(){
        root = new Node();
        size = 0;
    }

    // 获得Trie中存储的单词数量
    public int getSize(){
        return size;
    }

    // 向Trie中添加一个新的单词word
    public void add(String word){
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }
        if (!cur.isWord) {
            cur.isWord = true;
            size ++;
        }
    }

    // 查询单词word是否在Trie中
    public boolean contains(String word){
        Node cur = root;
        for(char c : word.toCharArray()){
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    // 查询单词word是否在Trie中（递归版）
    public boolean contains1(String word){
        return contains1(root, word, 0);
    }

    private boolean contains1(Node root, String word, int index){
        if (index >= word.length())
            return root.isWord;
        if (root == null)
            return false;

        char c = word.charAt(index);
        if (root.next.get(c) == null)
            return false;
        else
            return contains1(root.next.get(c), word, index+1);
    }

    // 查询在trie中是否有单词以prefix为前缀
    public boolean isPrefix(String prefix){
        Node cur = root;
        for(char c : prefix.toCharArray()){
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return true;
    }

    // 查询单词word是否在Trie中 (word中会包含小写字母以及'.' '.'代表通配符，可以匹配任意字符)
    public boolean search(String word){
        return search(root, word, 0);
    }

    private boolean search(Node root, String word, int index){
        if (index == word.length())
            return root.isWord;

        char c = word.charAt(index);
        if (c != '.') {
            if (root.next.get(c) == null)
                return false;
            return search(root.next.get(c), word, index + 1);
        }else {
            for(char nextChar : root.next.keySet())
                if (search(root.next.get(nextChar), word, index+1))
                    return true;
            return false;
        }
    }

    public static void main(String[] args) {
        TreeTrie trie = new TreeTrie();
        trie.add("bad");
        trie.add("bed");
        trie.add("boy");
        System.out.println(trie.search(".o."));
    }
}
