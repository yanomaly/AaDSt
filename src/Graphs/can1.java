package Graphs;

import java.io.*;
import java.util.HashMap;

public class can1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt"), "UTF-8"));
        int n = Integer.parseInt(reader.readLine());

        HashMap<Integer, Integer> graph = new HashMap<>();
        for (int i = 0; i < n; i++)
            graph.put(i, 0);

        String row = reader.readLine();
        while(row != null) {
            int parent = Integer.parseInt(row.split(" ")[0]);
            int child = Integer.parseInt(row.split(" ")[1]);
            graph.put(child - 1, parent);
            row = reader.readLine();
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"), "UTF-8"));
        for (int i = 0; i < n; i++)
            writer.write(graph.get(i) + " ");

        writer.close();
    }
}
