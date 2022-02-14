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
                int size = Integer.parseInt(reader.readLine());
                int tree[] = new int[size];
                int leftVmi[] = new int[size];
                int leftVma[] = new int[size];
                int rightVmi[] = new int[size];
                int rightVma[] = new int[size];
                tree[0] = Integer.parseInt(reader.readLine());
                leftVmi[0] = Integer.MIN_VALUE;
                leftVma[0] = tree[0];
                rightVmi[0] = tree[0];
                rightVma[0] = Integer.MAX_VALUE;
                int pos = 1;
                String temp = reader.readLine();
                while(temp != null){
                    String[] sep = temp.split(" ");
                    int val = Integer.parseInt(sep[0]);
                    int par = Integer.parseInt(sep[1]) - 1;
                    int par2 = par;
                    String side = sep[2];
                    if(side.equals("L")){
                        leftVmi[pos] = leftVmi[par];
                        leftVma[pos] = val;
                        if(val < leftVmi[par] || val >= leftVma[par]){
                            flag = false;
                            break;
                        }
                    }
                    else{
                        rightVma[pos] = rightVma[par];
                        leftVmi[pos] = val;
                        if(val < rightVmi[par] || val >= rightVma[par]){
                            flag = false;
                            break;
                        }
                    }
                    tree[pos] = val;
                    temp = reader.readLine();
                    pos++;
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