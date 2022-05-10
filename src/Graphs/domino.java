package Graphs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class domino {

    static int[][] matrix = new int[7][7];
    static int[] vertexes = new int[7];
    static int[] visited = new int[7];
    static int count;
    static Queue<Integer> qu = new LinkedList<>();

    static boolean eiler(){
        for (int i = 0; i < 7; i++)
            if (vertexes[i] % 2 == 1)
                return false;

        for(int i = 0; i < 7; i++)
            if(visited[i] == 0 && vertexes[i] != 0){
                dfs(i);
                break;
            }

        for(int i = 0; i < 7; i++)
            if(visited[i] == 0 && vertexes[i] > 0)
                return false;

        return true;
    }

    static void dfs(int start){
        visited[start] = 1;
        qu.add(start);
        while(!qu.isEmpty()){
            int v = qu.poll();
            for (int i = 0; i < 7; i++) {
                if(matrix[v][i] == 1 && visited[i] != 1){
                    visited[i] = 1;
                    qu.add(i);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        count = Integer.parseInt(reader.readLine());
        String temp = reader.readLine();
        while (temp != null){
            matrix[Integer.parseInt(temp.split(" ")[0])][Integer.parseInt(temp.split(" ")[1])] = 1;
            matrix[Integer.parseInt(temp.split(" ")[1])][Integer.parseInt(temp.split(" ")[0])] = 1;
            vertexes[Integer.parseInt(temp.split(" ")[1])]++;
            vertexes[Integer.parseInt(temp.split(" ")[0])]++;
            temp = reader.readLine();
        }
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")))){
            if(eiler())
                writer.write("Yes");
            else
                writer.write("No");
        }
    }
}
