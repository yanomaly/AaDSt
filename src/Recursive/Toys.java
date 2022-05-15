package Recursive;

import java.io.*;

public class Toys {

    static long[][] result;
    public static int[][] equip;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt"), "UTF-8"));
        int count, n, m;
        String[] row = reader.readLine().split(" ");
        count = Integer.parseInt(row[0]);
        n = Integer.parseInt(row[1]);
        m = Integer.parseInt(row[2]);
        equip = new int[count + 1][2];
        result = new long[count + 1][n * m + 1];
        for (int i = 1; i <= count; i++) {
            row = reader.readLine().split(" ");
            equip[i][0] = Integer.parseInt(row[0]);
            equip[i][1] = Integer.parseInt(row[1]);
        }
        for (int i = 1; i < n * m; i++)
            if (equip[1][0] <= i)
                result[0][i] = equip[0][1];

        for (int i = 1; i <= count; i++)
            for (int j = 1; j <= n * m; j++)
                if(j - equip[i][0] >= 0)
                    result[i][j] = Math.max(result[i - 1][j], equip[i][1] + result[i - 1][j - equip[i][0]]);
                else
                    result[i][j] = result[i - 1][j];
        StringBuilder res = new StringBuilder();
        int p1 = count, p2 = n * m, cnt = 0;
        while(p1 > 0){
            if (result[p1 - 1][p2] != result[p1][p2]) {
                res.append(p1 + " ");
                p2 -= equip[p1][0];
                cnt++;
            }
            p1--;
        }
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")))){
            writer.write(cnt + "\n" + res);
        }
    }
}