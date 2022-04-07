package DataStructures;
import java.io.*;

public class BinHeap {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int size = Integer.parseInt(reader.readLine());
        String sarr[] = reader.readLine().split(" ");
        int[] arr = new int[size + 1];
        for (int i = 1; i <= size; i++)
            arr[i] = Integer.parseInt(sarr[i - 1]);
        boolean flag = true;
        int i = 1;
        while(2*i + 1 <= size || 2*i <= size) {
            if (arr[i] > arr[2 * i] || (2*i + 1 <= size && arr[i] > arr[2 * i + 1])) {
                flag = false;
                break;
            }
            i++;
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))){
            if(flag)
                writer.write("Yes");
            else
                writer.write("No");
        }
    }
}

