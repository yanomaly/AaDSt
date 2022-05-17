package Graphs;

import java.io.*;
import java.util.ArrayList;

public class Dkstr {

    static class edge{
        public int vertex;
        public int weight;

        public edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    static int[] dist;
    static boolean[] visited;
    static ArrayList<ArrayList<edge>> adjacency = new ArrayList<>();
    static int vertexes;
    static int edges;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt"), "UTF-8"));
        String row[] = reader.readLine().split(" ");
        vertexes = Integer.parseInt(row[0]);
        edges = Integer.parseInt(row[1]);
        dist = new int[vertexes];
        visited = new boolean[vertexes];
        for (int i = 0; i < vertexes; i++)
            adjacency.add(new ArrayList<>());
        dist[0] = 0;
        for (int i = 1; i < vertexes; i++)
            dist[i] = (int)(0.8 *Integer.MAX_VALUE);
        for (int i = 0; i < edges; i++) {
            row = reader.readLine().split(" ");
            int u = Integer.parseInt(row[0]) - 1;
            int v = Integer.parseInt(row[1]) - 1;
            int weight = Integer.parseInt(row[2]);
            if(u != v) {
                int pos = 0;
                boolean flag = false;
                for (int j = 0; j < adjacency.get(u).size(); j++)
                    if(adjacency.get(u).get(j).vertex == v) {
                        flag = true;
                        pos = j;
                    }
                if (!flag) {
                    adjacency.get(u).add(new edge(v, weight));
                    adjacency.get(v).add(new edge(v, weight));
                } else if (weight < adjacency.get(u).get(pos).weight) {
                    adjacency.get(u).remove(pos);
                    adjacency.get(u).add(new edge(v, weight));
                    for (int j = 0; j < adjacency.get(v).size(); j++)
                        if(adjacency.get(v).get(j).vertex == u) {
                            adjacency.get(v).remove(j);
                            adjacency.get(v).add(new edge(v, weight));
                        }
                }
            }
        }

        for (int i = 0; i < adjacency.get(0).size(); i++)
                dist[adjacency.get(0).get(i).vertex] = adjacency.get(0).get(i).weight;

        while(true){
            int min_dist = -1;
            for (int i = 1; i < vertexes; i++)
                if(!visited[i] && (min_dist == -1 || dist[i] < dist[min_dist]))
                    min_dist = i;
            if(min_dist == -1 || dist[min_dist] == (int)(0.8 *Integer.MAX_VALUE))
                break;
            visited[min_dist] = true;
            for (int i = 0; i < adjacency.get(min_dist).size(); i++)
                dist[adjacency.get(min_dist).get(i).vertex] = Math.min(dist[adjacency.get(min_dist).get(i).vertex], dist[min_dist] + adjacency.get(min_dist).get(i).weight);
        }

        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")))){
            writer.write(String.valueOf(dist[vertexes - 1]));
        }
    }
}
