package DataStructures;

import java.io.*;

public class HashTable {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String[] fl = reader.readLine().split(" ");
        int size = Integer.parseInt(fl[0]);
        int c = Integer.parseInt(fl[1]);
        int n = Integer.parseInt(fl[2]);
        int[] table = new int[size];
        for (int i = 0; i < size; i++)
            table[i] = -1;
        for (int i = 0; i < n; i++) {
            int elem = Integer.parseInt(reader.readLine());
            for (int j = 0; j < size; j++) {
                int pos = (elem % size + c * j) % size;
                if(table[pos] == -1) {
                    table[pos] = elem;
                    break;
                } else if(table[pos] == elem)
                    break;
            }
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            for (int temp: table) {
                writer.write(temp + " ");
            }
        }
    }
}
