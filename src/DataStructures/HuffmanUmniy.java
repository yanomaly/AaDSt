package DataStructures;

import java.io.*;

public class HuffmanUmniy {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("huffman.in"), "UTF-8"));
        int size = Integer.parseInt(reader.readLine());
        long[] lesssgo, iuuuuuu;
        String arr[] = reader.readLine().split(" ");
        long result = 0;
        lesssgo = new long[size + 2];
        iuuuuuu = new long[size + 2];
        int pos_lesssgo = 0, pos_iuuuuu = 0;
        for (int i = 0; i < size; i++) {
            lesssgo[i] = (Integer.parseInt(arr[i]));
            iuuuuuu[i] = Long.MAX_VALUE / 2;
        }
        lesssgo[size] = Long.MAX_VALUE / 2;
        iuuuuuu[size] = Long.MAX_VALUE / 2;
        lesssgo[size + 1] = Long.MAX_VALUE / 2;
        iuuuuuu[size + 1] = Long.MAX_VALUE / 2;
        for (int i = 0; i < size - 1; i++) {
            if(lesssgo[pos_lesssgo] + lesssgo[pos_lesssgo + 1] <= lesssgo[pos_lesssgo] + iuuuuuu[pos_iuuuuu] && lesssgo[pos_lesssgo] + lesssgo[pos_lesssgo + 1] <= iuuuuuu[pos_iuuuuu] + iuuuuuu[pos_iuuuuu + 1]){
                iuuuuuu[i] = lesssgo[pos_lesssgo] + lesssgo[pos_lesssgo + 1];
                pos_lesssgo += 2;
            }
            else if(lesssgo[pos_lesssgo] + iuuuuuu[pos_iuuuuu] <= lesssgo[pos_lesssgo] + lesssgo[pos_lesssgo + 1] && lesssgo[pos_lesssgo] + iuuuuuu[pos_iuuuuu] <= iuuuuuu[pos_iuuuuu] + iuuuuuu[pos_iuuuuu + 1]){
                iuuuuuu[i] = lesssgo[pos_lesssgo] + iuuuuuu[pos_iuuuuu];
                pos_iuuuuu++;
                pos_lesssgo++;
            }
            else if(iuuuuuu[pos_iuuuuu] + iuuuuuu[pos_iuuuuu + 1] <= lesssgo[pos_lesssgo] + lesssgo[pos_lesssgo + 1] && iuuuuuu[pos_iuuuuu] + iuuuuuu[pos_iuuuuu + 1] <= lesssgo[pos_lesssgo] + iuuuuuu[pos_iuuuuu]){
                iuuuuuu[i] = iuuuuuu[pos_iuuuuu] + iuuuuuu[pos_iuuuuu + 1];
                pos_iuuuuu += 2;
            }
            result += iuuuuuu[i];
        }
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("huffman.out"), "UTF-8"))){
            writer.write(String.valueOf(result));
        }
    }
}