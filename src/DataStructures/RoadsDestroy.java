package DataStructures;

import java.io.*;

public class RoadsDestroy {
    private static int[] parent;
    private static int[] size;
    private static int[][] roads;
    private static int[] earthquakes;
    private static int[] saveRoads;
    private static char[] result;

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
        int road = Integer.parseInt(row[1]);
        int earthquake = Integer.parseInt(row[2]);
        parent = new int[count];
        size = new int[count];
        roads = new int[road][2];
        earthquakes = new int[earthquake];
        saveRoads = new int[road];
        result = new char[earthquake];
        int coherency = count;
        for (int i = 0; i < count; i++) {
            parent[i] = i + 1;
            size[i] = 1;
        }
        for (int i = 0; i < road; i++) {
            row = reader.readLine().split(" ");
            int node = Integer.parseInt(row[0]);
            int node1 = Integer.parseInt(row[1]);
            roads[i][0] = node;
            roads[i][1] = node1;
        }
        for (int i = 0; i < earthquake; i++) {
            earthquakes[i] = Integer.parseInt(reader.readLine());
            saveRoads[earthquakes[i] - 1] = 1;
        }
        for (int i = 0; i < road; i++) {
            if(saveRoads[i] == 0){
                if(unite(roads[i][0], roads[i][1]))
                    coherency--;
            }
        }
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"), "UTF-8"))) {
            for (int i = earthquake - 1; i > -1; i--) {
                if(coherency > 1) {
                    if (unite(roads[earthquakes[i] - 1][0], roads[earthquakes[i] - 1][1]))
                        coherency--;
                    result[i] = '0';
                }
                else
                    result[i] = '1';
            }
            writer.write(result);
        }
    }
}
