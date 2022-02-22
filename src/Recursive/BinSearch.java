package Recursive;

import java.util.Scanner;

public class BinSearch {
    private int[] array;
    private int[] requests;
    public String result = "";
    public int have = 0;

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

    public int binSearchL(int request) {
        int left = 0;
        int right = array.length;
        int pos = array.length;
        while(left < right){
            int center =left + (right - left)/2;
            if(request <= array[center])
                right = center;
            else
                left = center + 1;
        }
        pos = left;
        return pos;
    }

    public int binSearchR(int request){
        int left = 0;
        int right = array.length;
        int pos = array.length;
        while(left < right){
            int center = left + (right - left)/2;
            if(request < array[center])
                right = center;
            if(request >= array[center])
                left = center + 1;
            if(request == array[center])
                have = 1;
        }
        pos = left;
        return pos;
    }

    public void search(){
        for (int request: requests) {
            int first = binSearchL(request);
            int last = binSearchR(request);
            result += have + " " + first + " " + last + "\n";
            have = 0;
        }
    }


    public static void main(String[] args) {
        BinSearch bs = new BinSearch();
        bs.read();
        bs.search();
        System.out.print(bs.result);
    }
}