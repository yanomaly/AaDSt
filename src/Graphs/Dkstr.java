package Graphs;

import java.io.*;
import java.util.Arrays;

public class Dkstr {

    static int[] dist;
    static boolean[] visited;
    static int[][] matrix;
    static int vertexes;
    static int edges;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt"), "UTF-8"));
        String row[] = reader.readLine().split(" ");
        vertexes = Integer.parseInt(row[0]);
        edges = Integer.parseInt(row[1]);
        dist = new int[vertexes];
        visited = new boolean[vertexes];
        matrix = new int[vertexes][vertexes];
        for (int i = 0; i < vertexes; i++)
            Arrays.fill(matrix[i], -1);
        dist[0] = 0;
        for (int i = 1; i < vertexes; i++)
            dist[i] = (int)(0.8 *Integer.MAX_VALUE);
        for (int i = 0; i < edges; i++) {
            row = reader.readLine().split(" ");
            int u = Integer.parseInt(row[0]) - 1;
            int v = Integer.parseInt(row[1]) - 1;
            int weight = Integer.parseInt(row[2]);
            if(u != v) {
                if (matrix[u][v] == -1) {
                    matrix[u][v] = weight;
                    matrix[v][u] = matrix[u][v];
                } else if (weight < matrix[u][v]) {
                    matrix[u][v] = weight;
                    matrix[v][u] = matrix[u][v];
                }
            }
        }

        for (int i = 0; i < vertexes; i++)
            if(matrix[0][i] != -1)
                dist[i] = matrix[0][i];

        while(true){
            int min_dist = -1;
            for (int i = 1; i < vertexes; i++)
                if(!visited[i] && (min_dist == -1 || dist[i] < dist[min_dist]))
                    min_dist = i;
            if(min_dist == -1 || dist[min_dist] == (int)(0.8 *Integer.MAX_VALUE))
                break;
            visited[min_dist] = true;
            for (int i = 0; i < vertexes; i++) {
                if(matrix[i][min_dist] != -1)
                    dist[i] = Math.min(dist[i], dist[min_dist] + matrix[i][min_dist]);
            }
        }

        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")))){
            writer.write(String.valueOf(dist[vertexes -1 ]));
        }
    }
}
