package Recursive;

import java.io.*;

public class Str {
    private static int del;
    private static int ins;
    private static int swp;
    private static String str1;
    private static String str2;
    private static int kf;

    private static int[][] penalty;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("in.txt"));
        del = Integer.parseInt(reader.readLine());
        ins = Integer.parseInt(reader.readLine());
        swp = Integer.parseInt(reader.readLine());
        str1 = reader.readLine();
        str2 = reader.readLine();
        reader.close();
        kf = swp <= del + ins ? swp : del + ins;
        penalty = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 1; i <= str1.length(); i++)
            penalty[i][0] = i * del;
        for (int i = 1; i <= str2.length(); i++)
            penalty[0][i] = i * ins;
        penalty[0][0] = 0;

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                int delta = str1.charAt(i - 1) == str2.charAt(j - 1) ? 0 : 1;
                if(penalty[i - 1][j] + del <= penalty[i][j - 1] + ins && penalty[i - 1][j] + del <= penalty[i - 1][j - 1] + delta * kf)
                    penalty[i][j] = penalty[i - 1][j] + del;
                else if(penalty[i][j - 1] + ins <= penalty[i - 1][j] + del && penalty[i][j - 1] + ins <= penalty[i - 1][j - 1] + delta * kf)
                    penalty[i][j] = penalty[i][j - 1] + ins;
                else if(penalty[i - 1][j - 1] + delta * kf <= penalty[i][j - 1] + ins && penalty[i - 1][j - 1] + delta * kf <= penalty[i - 1][j] + del)
                    penalty[i][j] = penalty[i - 1][j - 1] + delta * kf;
            }
        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("out.txt"))){
            writer.write(Integer.toString(penalty[str1.length()][str2.length()]));
        }
    }
}