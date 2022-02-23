package Recursive;

import java.math.BigInteger;
import java.util.Scanner;

public class Bin2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BigInteger div = BigInteger.valueOf(1000000007);
        BigInteger div2 = BigInteger.valueOf(1000000005);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        BigInteger upper = BigInteger.valueOf(1);
        BigInteger lower = BigInteger.valueOf(1);
        BigInteger loVer = BigInteger.valueOf(1);
        for (int i = n; i > n - k; --i)
            upper = upper.multiply(BigInteger.valueOf(i)).mod(div);
        for (int i = 1; i <= k; ++i)
            lower = lower.multiply(BigInteger.valueOf(i)).mod(div);
        while(div2.compareTo(BigInteger.valueOf(0)) > 0){
            if(div2.mod(BigInteger.valueOf(2)).equals(BigInteger.valueOf(1)))
                loVer = (loVer.multiply(lower)).mod(div);
            lower = (lower.multiply(lower)).mod(div);
            div2 = div2.shiftRight(1);
        }
        System.out.println((upper.multiply(loVer)).mod(div));
    }
}
