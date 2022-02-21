package Recursive;

import java.math.BigInteger;
import java.util.Scanner;

public class BinSearch {
    private int[] array;
    private int[] requests;
    public String result = "";

    public void read() {
        Scanner scanner = new Scanner(System.in);
        int args = scanner.nextInt();
        array = new int[args];
        for (int i = 0; i < args; i++)
            array[i] = scanner.nextInt();
        int r = scanner.nextInt();
        requests = new int[r];
        for (int i = 0; i < r; i++)
            requests[i] = scanner.nextInt();
        scanner.close();
    }

    public void binSearch() {
        int left = 0;
        int right = array.length - 1;
        int center = (left + right) / 2;
        for (int request : requests) {
            while (array[center] != request && left <= right) {
                center = (left + right) / 2;
                if (array[center] > request) {
                    right = center - 1;
                } else {
                    left = center + 1;
                }
            }
            if (array[center] == request) {
                int pos = center + 1, cntr = center;
                while (pos < array.length - 1 && array[center] >= array[pos])
                    pos++;
                while (cntr > 1 && array[cntr] == request)
                    cntr--;
                result += 1 + " " + cntr + " " + pos + "\n";
            } else {
                if (array[center] > request)
                    result += 0 + " " + center + " " + center + "\n";
                else if (center + 1 < array.length && array[center + 1] > request)
                    result += 0 + " " + (center + 1) + " " + (center + 1) + "\n";
                else if (center + 2 < array.length && array[center + 2] > request)
                    result += 0 + " " + (center + 2) + " " + (center + 2) + "\n";
                if(array[center] < request){
                    result += 0 + " " + array[0] + " " + array[0] + "\n";
                }
            }
            left = 0;
            right = array.length - 1;
            center = (left + right) / 2;
        }
    }

    public static void main(String[] args) {
        BinSearch bs = new BinSearch();
        bs.read();
        bs.binSearch();
        System.out.print(bs.result);
    }
}
