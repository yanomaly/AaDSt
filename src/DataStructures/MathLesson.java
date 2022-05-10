package DataStructures;

import java.io.*;

class ku4a extends MathLesson{

    int[] data;
    int size;

    public ku4a(int maxSize) {
        this.size = 0;
        this.data = new int[maxSize];
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int poll(){
        int key = data[0];
        data[0] = data[--size];
        heapDown(0);
        return key;
    }
    public int peek(){
        return data[0];
    }
    public void add(int elem){
        data[size++] = elem;
        heapUp(size - 1);
    }
    private int parent(int i){
        return (i - 1)/2;
    }
    private int kthChild(int i, int k){
        return 2 * i + k;
    }
    private void heapUp(int i) {
        int temp = data[i];
        while(i>0 && result[temp] + result[count[temp]] < result[data[parent(i)]] + result[count[data[parent(i)]]]){
            data[i] = data[parent(i)];
            i = parent(i);
        }
        data[i] = temp;
    }
    private void heapDown(int i){
        int child;
        int temp = data[i];
        while(kthChild(i, 1) < size){
            child = maxChild(i);
            if(result[temp] + result[count[temp]] >= result[data[parent(i)]] + result[count[data[parent(i)]]]) {
                data[i] = data[child];
            }
            else break;
            i = child;
        }
        data[i] = temp;
    }
    private int maxChild(int i) {
        int leftChild = kthChild(i, 1);
        int rightChild = kthChild(i, 2);
        return result[data[leftChild]] + result[count[data[leftChild]]] < result[data[rightChild]] + result[count[data[rightChild]]] ? leftChild :rightChild;
    }

}

public class MathLesson {

    static int[] result;
    static int[] count;
    static int count_num = 0;

    public static int read(BufferedReader reader) throws IOException {
        String temp = "";
        int tmp = reader.read();
        while (48 <= tmp && tmp <= 57) {
            temp += (char) tmp;
            tmp = reader.read();
        }
        tmp = Integer.parseInt(temp);
        return tmp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")))) {
            int size = Integer.parseInt(reader.readLine());
            result = new int[size];
            count = new int[size];
            ku4a active = new ku4a(size);
            ku4a non_active = new ku4a(size);
            int numb = read(reader);
            result[count_num] = numb / 2;
            count[count_num] = 1;
            writer.write(result[count_num] + "\n");
            count_num++;
            active.add(0);
            while(count_num < size){
                numb = read(reader);
                if(active.isEmpty() || count[active.peek()] >= count_num || result[active.peek()] + result[count[active.peek()]] > numb){
                    result[count_num] = numb - result[0];
                    writer.write(result[count_num] + "\n");
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