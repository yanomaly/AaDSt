package DataStructures;

import java.io.*;
import java.util.PriorityQueue;

public class Huffaman {

    private static long length;

    static class Tree{
        private Node root;
    }

    static class Node{
        private long key;
        private Node LCh;
        private Node RCh;

        public Node(long key) {
            this.key = key;
        }

        public Node(long key, Node LCh, Node RCh) {
            this.key = key;
            this.LCh = LCh;
            this.RCh = RCh;
        }
    }

    public static void detour(Node node, long count){
        if(node.LCh != null) {
            count++;
            detour(node.LCh, count);
            count--;
        }
        if(node.RCh != null){
            count++;
            detour(node.RCh, count);
            count--;
        }
        if(node.LCh == null && node.RCh == null)
            length += count * node.key;
    }

    public static void main(String[] args) throws IOException {
        long start = System.nanoTime();
        BufferedReader reader = new BufferedReader(new FileReader("huffman.in"));
        int size = Integer.parseInt(reader.readLine());
        Tree tree = new Tree();
        String arr[] = reader.readLine().split(" ");
        PriorityQueue<Node> nodes = new PriorityQueue<>((x, y) -> x.key > y.key ? 1 : x.key < y.key ? -1 : 0);
        for (int i = 0; i < size; i++)
            nodes.add(new Node(Integer.parseInt(arr[i])));
        while(nodes.size() > 1){
            Node min = nodes.poll();
            Node min2 = nodes.poll();
            Node next = new Node(min.key + min2.key, min2, min);
            nodes.add(next);
            tree.root = next;
        }
        Huffaman.detour(tree.root, 0L);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("huffman.out"))){
            writer.write(String.valueOf(length));
        }
        long finish = System.nanoTime();
        System.out.println(finish - start);
    }
}