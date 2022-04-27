package Graphs;

import java.io.*;
import java.util.ArrayList;

import static java.lang.Math.*;

public class Walk {

    static int[][] human;
    static int[][] dog;
    static ArrayList<ArrayList<Integer>> place_row;
    static int[] share_dog;
    static int[] visit;
    static int[] share_human;

    static boolean link(int human_pos, int dog_pos){
        return 0.5 * (sqrt(pow(human[human_pos][0] - dog[dog_pos][0], 2) + pow(human[human_pos][1] - dog[dog_pos][1], 2)) +
                sqrt(pow(human[human_pos][2] - dog[dog_pos][0], 2) + pow(human[human_pos][3] - dog[dog_pos][1], 2)))
                <=
                sqrt(pow(human[human_pos][0] - human[human_pos][2], 2) + pow(human[human_pos][1] - human[human_pos][3], 2));
    }
    static boolean dfs(int numb){
        if(visit[numb] == 1)
            return false;
        visit[numb] = 1;
        for (int i = 0; i < place_row.get(numb).size(); i++) {
            if(share_dog[place_row.get(numb).get(i)] == -1 || dfs(i)){
                share_dog[place_row.get(numb).get(i)] = numb;
                share_human[numb] = 1;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt"), "UTF-8"));
        String row = reader.readLine();
        int n = Integer.parseInt(row.split(" ")[0]), m = Integer.parseInt(row.split(" ")[1]);
        row = reader.readLine();
        int x1 = Integer.parseInt(row.split(" ")[0]), y1 = Integer.parseInt(row.split(" ")[1]);
        int x2, y2;
        int res = 0;
        human = new int[n - 1][4];
        dog = new int[m][2];
        place_row = new ArrayList<>();
        share_dog = new int[m];
        visit = new int[n - 1];
        share_human = new int[n - 1];

        for (int i = 0; i < n - 1; i++) {
            row = reader.readLine();
            x2 = Integer.parseInt(row.split(" ")[0]);
            y2 = Integer.parseInt(row.split(" ")[1]);
            human[i][0] = x1;
            human[i][1] = y1;
            human[i][2] = x2;
            human[i][3] = y2;
            x1 = x2;
            y1 = y2;
        }
        for (int i = 0; i < m; i++) {
            row = reader.readLine();
            x1 = Integer.parseInt(row.split(" ")[0]);
            y1 = Integer.parseInt(row.split(" ")[1]);
            dog[i][0] = x1;
            dog[i][1] = y1;
            share_dog[i] = -1;
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m; j++) {
                if (link(i, j)) {
                    if (place_row.size() == i){
                        ArrayList<Integer> temp = new ArrayList<>();
                        temp.add(j);
                        place_row.add(temp);
                    }
                    else {
                        place_row.get(i).add(j);
                    }
                }
            }
            if (place_row.size() == i)
                place_row.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < place_row.get(i).size(); j++) {
                if(share_dog[j] == -1){
                    share_human[i] = 1;
                    share_dog[j] = i;
                    break;
                }
            }
            if(share_human[i] == 1)
                break;
        }
        for (int i = 0; i < n - 1; i++) {
            if(share_human[i] != 1 && dfs(i))
                for (int j = 0; j < visit.length; j++)
                    visit[j] = 0;
        }

        for (int i = 0; i < m; i++)
            if(share_dog[i] != -1)
                res++;

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"), "UTF-8"));
        writer.write((n + res) + " " + res);
        writer.close();
    }
}