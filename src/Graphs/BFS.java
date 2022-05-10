package Graphs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    static int vertex;
    static int [][] matrix;
    static int[] visited;
    static int[] met;
    static int idx = 1;
    static Queue<Integer> qu = new LinkedList<>();

    public static void bfs(int start){
        visited[start] = 1;
        met[start] = idx;
        qu.add(start);
        idx++;
        while(!qu.isEmpty()){
            int v = qu.poll(); //?
            for (int i = 0; i < vertex; i++) {
                if(matrix[v][i] == 1 && visited[i] != 1){
                    visited[i] = 1;
                    met[i] = idx;
                    idx++;
                    qu.add(i);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt"), "UTF-8"));
        vertex = Integer.parseInt(reader.readLine());
        matrix = new int [vertex][vertex];
        String temp = reader.readLine();
        int pos = 0;
        visited = new int[vertex];
        while(temp != null){
            String[] a = temp.split(" ");
            for (int i = 0; i < a.length; i++)
                matrix[pos][i] = Integer.parseInt(a[i]);
            pos++;
            temp = reader.readLine();
        }
        met = new int[vertex];
        for (int i = 0; i < vertex; i++) {
            if(visited[i] == 0)
                bfs(i);
        }
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")))){
            for (int i = 0; i < vertex; i++) {
                writer.write(met[i] + " ");
            }
        }
    }
}
