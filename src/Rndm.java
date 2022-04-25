import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Rndm {
    public static void main(String[] args) {
        Random r = new Random();
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("input.txt"))) {
            writer.write(500000 + "\n");
            for (int i = 0; i < 500000; i++)
                writer.write(1000000000 + " ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
