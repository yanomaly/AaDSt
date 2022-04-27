package Graphs;

import java.io.*;
import java.util.ArrayList;

public class adjList {
    static ArrayList<ArrayList<Integer>> adjList= new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt"), "UTF-8"));
        String row = reader.readLine();
        int n = Integer.parseInt(row.split(" ")[0]), m = Integer.parseInt(row.split(" ")[1]);
        int x, y;
        for (int i = 0; i < n; i++)
            adjList.add(new ArrayList<>());
        for (int i = 0; i < m; i++) {
            row = reader.readLine();
            x = Integer.parseInt(row.split(" ")[0]);
            y = Integer.parseInt(row.split(" ")[1]);
            adjList.get(x - 1).add(y);
            adjList.get(y - 1).add(x);
        }
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"), "UTF-8"));
        for (int i = 0; i < n; i++) {
            writer.write(adjList.get(i).size() + " ");
            for (int j = 0; j < adjList.get(i).size(); j++) {
                writer.write(adjList.get(i).get(j) + " ");
            }
            writer.write("\n");
        }
        writer.close();
    }
}
