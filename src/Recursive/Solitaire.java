package Recursive;

import java.io.*;

public class Solitaire {

    private int[][] penatly;

    public Integer action(int[] cards, int[] pos, int length){
        penatly = new int[length][length];
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                int minPenalty = Integer.MAX_VALUE;
                int k = i + j + 1;
                for (int l = j; l <= k - 1; l++) {
                    if(minPenalty > penatly[j][l] + penatly[l + 1][k] + Math.abs(pos[l] - pos[k]))
                        minPenalty = penatly[j][l] + penatly[l + 1][k] + Math.abs(pos[l] - pos[k]);
                }
                penatly[j][k] = minPenalty;
            }
        }
        return penatly[0][length - 1];
    }

    public static void main(String[] args) throws IOException {
        int[] cards;
        int[] pos;
        String[] cardsInp;
        Solitaire solitaire = new Solitaire();
        try(BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int size = Integer.parseInt(reader.readLine());
            cardsInp = reader.readLine().split(" ");
            cards = new int[size];
            pos = new int[size];
            for (int i = 0; i < size; i++) {
                cards[i] = Integer.parseInt(cardsInp[i]);
                pos[cards[i] - 1] = i;
            }
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))){
            writer.write(solitaire.action(cards, pos, cards.length).toString());
        }
    }
}
