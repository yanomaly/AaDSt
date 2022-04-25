package DataStructures;

import java.io.*;
import java.util.PriorityQueue;

public class MathLesson {

    private static int[] result;
    private static int[] count;
    private static int count_num = 0;
    private static PriorityQueue<Integer> active = new PriorityQueue<>();
    private static PriorityQueue<Integer> non_active = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt"), "UTF-8"));
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"), "UTF-8"))) {
            int size = Integer.parseInt(reader.readLine());
            result = new int[size];
            count = new int[size];
            String temp = "";
            int tmp = reader.read();
            while (48 <= tmp && tmp <= 57) {
                temp += (char) tmp;
                tmp = reader.read();
            }
            int numb = Integer.parseInt(temp);
            result[count_num] = numb / 2;
            count[count_num] = 1;
            writer.write(String.valueOf(result[count_num]) + '\n');
            count_num++;
            active.add(0);
            while(count_num < size){
                temp = "";
                tmp = reader.read();
                while (48 <= tmp && tmp <= 57) {
                    temp += (char) tmp;
                    tmp = reader.read();
                }
                numb = Integer.parseInt(temp);
                if(active.isEmpty() || count[active.peek()] >= count_num){
                    result[count_num] = numb - result[0];
                    writer.write(String.valueOf(result[count_num]) + '\n');
                    count[count_num] = 1;
                    active.add(count_num);
                    count_num++;
                    while(!non_active.isEmpty())
                        active.add(non_active.poll());
                }
                else{
                    int top = active.poll();
                    count[top]++;
                    if(count[top] < count_num)
                        active.add(top);
                    else
                        non_active.add(top);
                }
            }
        }
    }
}