package Graphs;

import java.io.*;

public class AdjMatrix {

    static int [][] adjMatr;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt"), "UTF-8"));
        String row = reader.readLine();
        int n = Integer.parseInt(row.split(" ")[0]), m = Integer.parseInt(row.split(" ")[1]);
        int x, y;
        adjMatr = new int[n][n];
        for (int i = 0; i < m; i++) {
            row = reader.readLine();
            x = Integer.parseInt(row.split(" ")[0]);
            y = Integer.parseInt(row.split(" ")[1]);
            adjMatr[x - 1][y - 1] = 1;
            adjMatr[y - 1][x - 1] = 1;
        }
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"), "UTF-8"));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                writer.write(adjMatr[i][j] + " ");
            }
            writer.write("\n");
        }
        writer.close();
    }
}
