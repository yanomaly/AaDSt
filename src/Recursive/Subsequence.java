package Recursive;

import java.io.*;

public class Subsequence {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int size = Integer.parseInt(reader.readLine());
        int[] sequence = new int[size];
        int[] rise = new int[size + 1];
        String[] sequenceStr = reader.readLine().split(" ");
        for (int i = 0; i < size; i++) {
            sequence[i] = Integer.parseInt(sequenceStr[i]);
            rise[i] = Integer.MAX_VALUE;
        }
        rise[0] = Integer.MIN_VALUE;
        rise[size] = Integer.MAX_VALUE;

        int maxSeq = 1;
        for (int i = 0; i < size; i++) {
            int j = binSearchR(sequence[i], rise.length, rise);
            if(j > 0 && rise[j - 1] < sequence[i] && sequence[i] < rise[j])
                rise[j] = sequence[i];
            else if(j == 0 && sequence[i] < rise[j])
                rise[j] = sequence[i];
        }
        for (int i = rise.length - 1; i >= 0; i--)
            if(rise[i] != Integer.MAX_VALUE && rise[i] != Integer.MIN_VALUE){
                maxSeq = i;
                break;
            }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            writer.write(Integer.toString(maxSeq));
        }
    }

    public static int binSearchR(int request, int size, int[] array){
        int left = 0;
        int right = size;
        while(left < right){
            int center = left + (right - left)/2;
            if(request < array[center])
                right = center;
            if(request >= array[center])
                left = center + 1;
        }
        if(left != size)
        return left;
        else
        return --left;
    }
}