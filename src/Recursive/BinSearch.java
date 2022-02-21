package Recursive;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BinSearch {
    private String[] array;
    private String[] requests;
    private int requests_count;
    private String result = "";

    public void read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        reader.readLine();
        array = reader.readLine().split(" ");
        requests_count = Integer.parseInt(reader.readLine());
        requests = reader.readLine().split(" ");
    }

    public void binSearch(){
        int left = 0;
        int right = array.length - 1;
        int center = (left + right)/2;
        for (String request: requests) {
            while(!array[center].equals(request) && left < right){
                if (Integer.parseInt(array[center]) > Integer.parseInt(request)) {
                    right = center - 1;
                } else {
                    left = center + 1;
                }
                center = (left + right)/2;
            }
            if(left <= right){
                int pos = center + 1;
                while(Integer.parseInt(array[center]) >= Integer.parseInt(array[pos]) && pos < array.length)
                    pos++;
                result += 1 + " " + (center + 1) + " " + pos + "\n";
            }
            else{
                if (Integer.parseInt(array[array.length]) < Integer.parseInt(request))
                        result += 0 + " " + array.length + " " + array.length + "\n";
                else{
                    
                }
            }
            }
        }
    }
