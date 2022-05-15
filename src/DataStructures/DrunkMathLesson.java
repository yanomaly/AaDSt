package DataStructures;

import java.io.*;

public class DrunkMathLesson {

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
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")))) {
            int size = Integer.parseInt(reader.readLine());
            int count = 0;
            int numb = read(reader);
            int min = numb/2;
            writer.write(min + "\n");
            count++;
            while(count < size) {
                for (int i = 0; i < count * 2 - 2; i++)
                    read(reader);
                writer.write((read(reader) - min) + "\n");
                count++;
            }
        }
    }
}
