package Recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Frog {

    int[] mosquito;
    int[] way;

    public void read() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());
        mosquito = new int[size + 1];
        way = new int[size + 1];
        String[] numbers = reader.readLine().split(" ");
        for (int i = 1; i < size + 1; i++)
            mosquito[i] = Integer.parseInt(numbers[i - 1]);
        mosquito[0] = Integer.MIN_VALUE;
        if(size > 1){
            mosquito[2] = Integer.MIN_VALUE;
            way[2] = 1;
        }
        else{
            way[1] = 1;
        }
    }

    public void action(){
        if(mosquito.length < 4 && mosquito.length != 2)
            System.out.println(-1);
        else if(mosquito.length == 2){
            System.out.println(mosquito[1]);
            System.out.println(way[1]);
        }
        else{
            for (int i = 3; i < mosquito.length; i++){
                mosquito[i] = mosquito[i - 3] >= mosquito[i - 2] ? mosquito[i - 3] + mosquito[i] : mosquito[i - 2] + mosquito[i];
                way[i] = mosquito[i - 3] >= mosquito[i - 2] ? i - 3 : i - 2;
            }
            System.out.println(mosquito[mosquito.length - 1]);
            int[] res = new int[way.length];
            int size = 1;
            int pos = way[way.length - 1];
            res[0] = way.length - 1;
            while(pos != 1){
                res[size] = pos;
                pos = way[pos];
                size++;
            }
            res[size] = 1;
            for (int i = size; i >= 0; i--)
                System.out.print(res[i] + " ");

        }
    }

    public static void main(String[] args) throws IOException {
        Frog fr = new Frog();
        fr.read();
        fr.action();
    }
}
