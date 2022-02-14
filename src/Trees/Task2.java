    package Trees;

    import java.io.*;

    public class Task2 implements Runnable {

        public static void main(String[] args){
            new Thread(null, new Task2(), "", 64 * 1024 * 1024).start();
        }

        @Override
        public void run() {
            boolean flag = true;
            try(BufferedWriter write = new BufferedWriter(new FileWriter("bst.out"))){
                BufferedReader reader = new BufferedReader(new FileReader("bst.in"));
                int size = Integer.parseInt(reader.readLine());
                int tree[] = new int[size];
                int parents[] = new int[size];
                String LR[] = new String[size];
                tree[0] = Integer.parseInt(reader.readLine());
                parents[0] = -1;
                int pos = 1, prt;
                String temp = reader.readLine();
                while(temp != null){
                    String[] sep = temp.split(" ");
                    int val = Integer.parseInt(sep[0]);
                    int par = Integer.parseInt(sep[1]) - 1;
                    int par2 = par;
                    String side = sep[2];
                    if(side.equals("L")) {
                        if (tree[par] <= val) {
                            flag = false;
                            break;
                        }
                        prt = parents[par];
                        while (prt != -1) {
                            if (LR[par].equals("L")) { //тут хуёво
                                if (tree[prt] <= val) {
                                    flag = false;
                                    break;
                                }
                            } else {
                                if (tree[prt] > val) {
                                    flag = false;
                                    break;
                                }
                            }
                            par = prt;
                            prt = parents[prt];
                        }
                    }
                    else{
                        if(tree[par] > val){
                            flag = false;
                            break;
                        }
                        prt = parents[par];
                        while (prt != -1) {
                            if (LR[par].equals("L")) { //и тут хуёво
                                if (tree[prt] <= val) {
                                    flag = false;
                                    break;
                                }
                            } else {
                                if (tree[prt] > val) {
                                    flag = false;
                                    break;
                                }
                            }
                            par = prt;
                            prt = parents[prt];
                        }
                    }
                    tree[pos] = val;
                    parents[pos] = par2;
                    LR[pos] = side;
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