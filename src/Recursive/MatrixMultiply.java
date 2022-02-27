package Recursive;

import java.io.*;
public class MatrixMultiply {

    private int[][] operations;

    public Integer action(int[][] matrix, int length){
        operations = new int[length][length];
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                int minOperations = Integer.MAX_VALUE;
                int k = i + j + 1;
                for (int l = j; l <= k - 1; l++) {
                    if(minOperations > operations[j][l] + operations[l + 1][k] + matrix[j][0]*matrix[l][1]*matrix[k][1])
                        minOperations = operations[j][l] + operations[l + 1][k] + matrix[j][0]*matrix[l][1]*matrix[k][1];
                }
                operations[j][k] = minOperations;
            }
        }
        return operations[0][length - 1];
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
