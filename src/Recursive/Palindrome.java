package Recursive;

import java.io.*;

public class Palindrome {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String str = reader.readLine();
        StringBuffer rev = new StringBuffer();
        String[] strArr = str.split("");
        int[][] palindrome = new int[strArr.length][strArr.length];
        for (int i = 0; i < strArr.length; i++)
            palindrome[i][i] = 1;

        for (int i = 1; i < strArr.length; i++) {
            for (int j = 0, k = i; j < strArr.length - i; j++, k++) {
                if(i == 1){
                    if(strArr[k].equals(strArr[j]))
                        palindrome[j][k] = 2;
                    else
                        palindrome[j][k] = 1;
                }
                else if(strArr[k].equals(strArr[j])){
                    palindrome[j][k] = palindrome[j + 1][k - 1] + 2;
                }
                else{
                    palindrome[j][k] = palindrome[j][k - 1] > palindrome[j + 1][k]? palindrome[j][k - 1] : palindrome[j + 1][k];
                }
            }
        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))){
            writer.write(palindrome[0][palindrome.length - 1] + "\n");
            int i = 0, j = str.length() - 1;
            while (j >= i) {
                int k = palindrome[i][j];
                while (i < str.length() - 1 && palindrome[i + 1][j] == k)
                    i++;
                while (j > 0 && palindrome[i][j - 1] == k)
                    j--;
                writer.write(str.charAt(j));
                rev.append(str.charAt(j));
                i++;
                j--;
            }
            rev = rev.reverse();
            writer.write(rev.substring(palindrome[0][palindrome.length - 1] % 2));
      }
    }
}
