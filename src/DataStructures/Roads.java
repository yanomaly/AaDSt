package DataStructures;

import java.io.*;

public class Roads {

    private static int[] parent;
    private static int[] size;

    public static int belong(int elem){
        if(parent[elem - 1] == elem)
            return elem;
        else{
            parent[elem - 1] = belong(parent[elem - 1]);
            return parent[elem - 1];
        }
    }

    public static boolean unite(int x, int y){
        x = belong(x);
        y = belong(y);
        if(x != y) {
            if (size[x - 1] > size[y - 1]) {
                parent[y - 1] = x;
                size[x - 1] += size[y - 1];
            }
            else{
                parent[x - 1] = y;
                size[y - 1] += size[x - 1];
            }
            return true;
        }
        else
            return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt"), "UTF-8"));
        String[] row = reader.readLine().split(" ");
        int count = Integer.parseInt(row[0]);
        int req = Integer.parseInt(row[1]);
        parent = new int[count];
        size = new int[count];
        int coherency = count;
        for (int i = 0; i < count; i++) {
            parent[i] = i + 1;
            size[i] = 1;
        }
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"), "UTF-8"))) {
            for (int i = 0; i < req; i++) {
                row = reader.readLine().split(" ");
                int node = Integer.parseInt(row[0]);
                int node1 = Integer.parseInt(row[1]);
                if(unite(node, node1))
                    coherency--;
                writer.write(coherency + "\n");
            }
        }
    }
}