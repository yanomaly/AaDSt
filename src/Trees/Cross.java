package Trees;

import java.io.*;

public class Cross {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());
        int[] elem = new int[size];
        int[] res = new int[size];
        int temp = 0;
        String a[] = reader.readLine().split(" ");
        for (int i = 0; i < size; i++) {
            elem[i] = Integer.parseInt(a[i]);
            res[i] = 1;
        }
        for (int i = 1; i < size; i++)
            for (int j = 0; j < i; j++)
                if(elem[j] != 0 && elem[i] % elem[j] == 0)
                    res[i] = Math.max(res[i], res[j] + 1);
        for (int i = 0; i < size; i++)
            temp = Math.max(res[i], temp);
        System.out.println(size - temp);
    }
}
