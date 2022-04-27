package Graphs;

import java.io.*;
import java.util.HashMap;

public class can2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt"), "UTF-8"));
        int n = Integer.parseInt(reader.readLine());

        HashMap<Integer, Integer> graph = new HashMap<>();
        for (int i = 0; i < n; i++)
            graph.put(i, 0);

        String row;
        String[] elem = new String[n];
        for (int i = 0; i < n; i++) {
            row = reader.readLine();
            elem = row.split(" ");
            for (int j = 0; j < n; j++) {
                if(elem[j].equals("1"))
                    graph.put(j, i + 1);
            }
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"), "UTF-8"));
        for (int i = 0; i < n; i++)
            writer.write(graph.get(i) + " ");

        writer.close();
    }
}
