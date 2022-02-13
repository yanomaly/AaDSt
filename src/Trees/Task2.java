package Trees;

import java.io.*;

public class Task2 implements Runnable {

    public static void main(String[] args){
        new Thread(null, new Task2(), "", 64 * 1024 * 1024).start();
    }

    @Override
    public void run() {
        boolean flag = true;
        try(BufferedWriter write = new BufferedWriter(new FileWriter("tst.out"))){
            BufferedReader reader = new BufferedReader(new FileReader("tst.in"));
            int tree[] = new int[Integer.parseInt(reader.readLine())];
            tree[0] = Integer.parseInt(reader.readLine());
            int pos = 1;
            String temp = reader.readLine();
            while(temp != null){
                String[] sep = temp.split(" ");
                int val = Integer.parseInt(sep[0]);
                int par = Integer.parseInt(sep[1]) - 1;
                String side = sep[2];
                if(side.equals("L")){
                    if(tree[par] <= val){
                        flag = false;
                        break;
                    }
                }
                else{
                    if(tree[par] > val){
                        flag = false;
                        break;
                    }
                }
                tree[pos] = val;
                pos++;
                temp = reader.readLine();
            }
            if(flag)
                write.write("YES");
            else
                write.write("NO");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}