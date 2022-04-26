package Graphs;

import java.io.*;
import static java.lang.Math.*;

public class Walk {

    static int[][] human;
    static int[][] dog;
    static int[][] place_row;
    static int[] match;
    static int[] visit;
    static int[] activeVisit;

    static boolean link(int human_pos, int dog_pos){
        return 0.5 * (sqrt(pow(human[human_pos][0] - dog[dog_pos][0], 2) + pow(human[human_pos][1] - dog[dog_pos][1], 2)) +
                sqrt(pow(human[human_pos][2] - dog[dog_pos][0], 2) + pow(human[human_pos][3] - dog[dog_pos][1], 2)))
                <=
                sqrt(pow(human[human_pos][0] - human[human_pos][2], 2) + pow(human[human_pos][1] - human[human_pos][3], 2));
    } //успеет ли собака забежать в выбранную точку, пока человек идёт по выбранному переходу

    static boolean dfs(int numb){ //???
        if(visit[numb] == 1)
            return false;
        visit[numb] = 1;
        for (int i = 0; i < place_row[numb].length; i++) {
            if((place_row[numb][i] == 1) && (match[i] == -1 || dfs(i))){
                match[i] = numb;
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
        human = new int[n - 1][4];  // переходы, по которым идёт человек
        dog = new int[m][2]; //любимые места собаки
        place_row = new int[n - 1][m]; //переходы и все места, которые может посетить собака
        match = new int[m]; //номер перехода, на котором собака успеет посетить i место
        visit = new int[n]; //какие места посетит собака в данном варианте
        activeVisit = new int[n]; //идёт ли в своё любимое место собака на i переходе

        for (int i = 0; i < n - 1; i++) { //в массив заносятся все переходы как координаты концевых точек
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

        for (int i = 0; i < m; i++) { //в любимые места собаки
            row = reader.readLine();
            x1 = Integer.parseInt(row.split(" ")[0]);
            y1 = Integer.parseInt(row.split(" ")[1]);
            dog[i][0] = x1;
            dog[i][1] = y1;
            match[i] = -1;
        }

        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < m; j++) //места, которые може посетить собака на каждом переходе
                if(link(i, j))
                    place_row[i][j] = 1;

        for (int i = 0; i < n - 1; i++) { //начальный варик (просто выбираем первые доступные точки)
            for (int j = 0; j < m; j++) {
                if(place_row[i][j] == 1 && match[j] == -1){
                    activeVisit[i] = 1;
                    match[j] = i;
                    break;
                }
            }
        }

        for (int i = 0; i < n - 1; i++) { //обход графа
            if(activeVisit[i] != 1 && dfs(i))
                for (int j = 0; j < m; j++)
                    visit[j] = 0;
        }

        for (int i = 0; i < m; i++) //считаем, сколько точек в итоге посетит
            if(match[i] != -1)
                res++;

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"), "UTF-8"));
        writer.write((n + res) + " " + res);
        writer.close();
    }
}
