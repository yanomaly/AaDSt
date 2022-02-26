package Recursive;

import java.io.*;
public class MatrixMultiply {

    private int[][] operations;

    public Integer action(int[][] matrix, int length){
        operations = new int[length + 1][length + 1];
        for (int i = 1; i <= length - 1; i++) {
            for (int j = 1; j <= length - i; j++) {
                int minOperations = Integer.MAX_VALUE;
                int k = i + j;
                for (int l = j; l <= k - 1; l++) {
                    if(minOperations > operations[j][l] + operations[l + 1][k] + matrix[j - 1][0]*matrix[l - 1][1]*matrix[k - 1][1])
                        minOperations = operations[j][l] + operations[l + 1][k] + matrix[j - 1][0]*matrix[l - 1][1]*matrix[k - 1][1];
                }
                operations[j][k] = minOperations;
            }
        }
        return operations[1][length];
    }

    public static void main(String[] args) throws IOException {
        int[][] matrix;
        MatrixMultiply mm = new MatrixMultiply();
        try(BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int size = Integer.parseInt(reader.readLine());
            String data;
            matrix = new int[size][2];
            for (int i = 0; i < size; i++) {
                data = reader.readLine();
                matrix[i][0] = Integer.parseInt(data.split(" ")[0]);
                matrix[i][1] = Integer.parseInt(data.split(" ")[1]);
            }
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))){
            writer.write(mm.action(matrix, matrix.length).toString());
        }
    }
}
