package Graphs;

import java.io.*;

public class dfs {

    static int vertex;
    static int [][] matrix;
    static int[] visited;
    static int[] met;
    static int idx = 1;

    public static void dfs(int start){
        visited[start] = 1;
        met[start] = idx++;
        for (int i = 0; i < vertex; i++)
            if (matrix[start][i] == 1 && visited[i] == 0)
                dfs(i);
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
                dfs(i);
        }
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")))){
            for (int i = 0; i < vertex; i++) {
                writer.write(met[i] + " ");
            }
        }
    }

}
