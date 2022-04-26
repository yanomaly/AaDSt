package Graphs;

//import java.io.*;
//import static java.lang.Math.*;
//
//public class Walk {
//
//    static int[][] human;
//    static int[][] dog;
//    static int[][] place_row;
//    static int[] share_dog;
//    static int[] visit;
//    static int[] share_human;
//
//    static boolean link(int human_pos, int dog_pos){
//        return 0.5 * (sqrt(pow(human[human_pos][0] - dog[dog_pos][0], 2) + pow(human[human_pos][1] - dog[dog_pos][1], 2)) +
//                sqrt(pow(human[human_pos][2] - dog[dog_pos][0], 2) + pow(human[human_pos][3] - dog[dog_pos][1], 2)))
//                <=
//                sqrt(pow(human[human_pos][0] - human[human_pos][2], 2) + pow(human[human_pos][1] - human[human_pos][3], 2));
//    } //успеет ли собака забежать в выбранную точку, пока человек идёт по выбранному переходу
//    static boolean dfs(int numb){ //поиск наибольшего паросочетания
//        if(visit[numb] == 1)
//            return false;
//        visit[numb] = 1;
//        for (int i = 0; i < place_row[numb].length; i++) {
//            if((place_row[numb][i] == 1) && (share_dog[i] == -1 || dfs(i))){
//                share_dog[i] = numb;
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt"), "UTF-8"));
//        String row = reader.readLine();
//        int n = Integer.parseInt(row.split(" ")[0]), m = Integer.parseInt(row.split(" ")[1]);
//        row = reader.readLine();
//        int x1 = Integer.parseInt(row.split(" ")[0]), y1 = Integer.parseInt(row.split(" ")[1]);
//        int x2, y2;
//        int res = 0;
//        human = new int[n - 1][4];  // переходы, по которым идёт человек (каждое ребро = одна точка)
//        dog = new int[m][2]; //любимые места собаки
//        place_row = new int[n - 1][m]; //переходы и все места, которые может посетить собака
//        share_dog = new int[m]; //номер перехода, на котором собака успеет посетить i место
//        visit = new int[m >= n ? m : n - 1]; //какие точки были посещены в данном обходе
//        share_human = new int[n - 1]; //идёт ли в своё любимое место собака на i переходе
//
//        for (int i = 0; i < n - 1; i++) { //в массив заносятся все переходы как координаты концевых точек
//            row = reader.readLine();
//            x2 = Integer.parseInt(row.split(" ")[0]);
//            y2 = Integer.parseInt(row.split(" ")[1]);
//            human[i][0] = x1;
//            human[i][1] = y1;
//            human[i][2] = x2;
//            human[i][3] = y2;
//            x1 = x2;
//            y1 = y2;
//        }
//        for (int i = 0; i < m; i++) { //в любимые места собаки
//            row = reader.readLine();
//            x1 = Integer.parseInt(row.split(" ")[0]);
//            y1 = Integer.parseInt(row.split(" ")[1]);
//            dog[i][0] = x1;
//            dog[i][1] = y1;
//            share_dog[i] = -1;
//        }
//
//        for (int i = 0; i < n - 1; i++)
//            for (int j = 0; j < m; j++) //места, которые может посетить собака на каждом переходе
//                if(link(i, j))
//                    place_row[i][j] = 1;
//
//        for (int i = 0; i < n - 1; i++) { //начальный первое доступное ребро
//            for (int j = 0; j < m; j++) {
//                if(place_row[i][j] == 1 && share_dog[j] == -1){
//                    share_human[i] = 1;
//                    share_dog[j] = i;
//                    break;
//                }
//            }
//            if(share_human[i] == 1)
//                break;
//        }
//        for (int i = 0; i < n - 1; i++) { //обход графа
//            if(share_human[i] != 1 && dfs(i))
//                for (int j = 0; j < visit.length; j++)
//                    visit[j] = 0;
//        }
//
//        for (int i = 0; i < m; i++) //считаем, сколько точек в итоге посетит
//            if(share_dog[i] != -1)
//                res++;
//
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"), "UTF-8"));
//        writer.write((n + res) + " " + res);
//        writer.close();
//    }
//}
//import java.io.*;
//import java.util.ArrayList;
//
//import static java.lang.Math.*;
//
//public class Walk {
//
//    static int[][] human;
//    static int[][] dog;
//    static ArrayList<ArrayList<Integer>> place_row;
//    static int[] share_dog;
//    static int[] visit;
//    static int[] share_human;
//
//    static boolean link(int human_pos, int dog_pos){
//        return 0.5 * (sqrt(pow(human[human_pos][0] - dog[dog_pos][0], 2) + pow(human[human_pos][1] - dog[dog_pos][1], 2)) +
//                sqrt(pow(human[human_pos][2] - dog[dog_pos][0], 2) + pow(human[human_pos][3] - dog[dog_pos][1], 2)))
//                <=
//                sqrt(pow(human[human_pos][0] - human[human_pos][2], 2) + pow(human[human_pos][1] - human[human_pos][3], 2));
//    }
//    static boolean dfs(int numb){
//        if(visit[numb] == 1)
//            return false;
//        visit[numb] = 1;
//        for (int i = 0; i < place_row[numb].length; i++) {
//            if((place_row[numb][i] == 1) && (share_dog[i] == -1 || dfs(i))){
//                share_dog[i] = numb;
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt"), "UTF-8"));
//        String row = reader.readLine();
//        int n = Integer.parseInt(row.split(" ")[0]), m = Integer.parseInt(row.split(" ")[1]);
//        row = reader.readLine();
//        int x1 = Integer.parseInt(row.split(" ")[0]), y1 = Integer.parseInt(row.split(" ")[1]);
//        int x2, y2;
//        int res = 0;
//        human = new int[n - 1][4];
//        dog = new int[m][2];
//        place_row = new ArrayList<>(n - 1);
//        share_dog = new int[m];
//        visit = new int[n - 1];
//        share_human = new int[n];
//
//        for (int i = 0; i < n - 1; i++) {
//            row = reader.readLine();
//            x2 = Integer.parseInt(row.split(" ")[0]);
//            y2 = Integer.parseInt(row.split(" ")[1]);
//            human[i][0] = x1;
//            human[i][1] = y1;
//            human[i][2] = x2;
//            human[i][3] = y2;
//            x1 = x2;
//            y1 = y2;
//        }
//        for (int i = 0; i < m; i++) {
//            row = reader.readLine();
//            x1 = Integer.parseInt(row.split(" ")[0]);
//            y1 = Integer.parseInt(row.split(" ")[1]);
//            dog[i][0] = x1;
//            dog[i][1] = y1;
//            share_dog[i] = -1;
//        }
//        for (int i = 0; i < n - 1; i++)
//            for (int j = 0; j < m; j++)
//                if(link(i, j))
//                    place_row.get(i).add(j);
//        for (int i = 0; i < n - 1; i++) {
//            for (int j = 0; j < place_row.get(i).size(); j++) {
//                if(share_dog[j] == -1){
//                    share_human[i] = 1;
//                    share_dog[j] = i;
//                    break;
//                }
//            }
//            if(share_human[i] == 1)
//                break;
//        }
//        for (int i = 0; i < n - 1; i++) {
//            if(share_human[i] != 1 && dfs(i))
//                for (int j = 0; j < visit.length; j++)
//                    visit[j] = 0;
//        }
//
//        for (int i = 0; i < m; i++)
//            if(share_dog[i] != -1)
//                res++;
//
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"), "UTF-8"));
//        writer.write((n + res) + " " + res);
//        writer.close();
//    }
//}
