package Recursive;

import java.math.BigInteger;
import java.util.Scanner;

public class Bin implements Runnable {
    public BigInteger factor(BigInteger n){
        if(n.equals(BigInteger.valueOf(0))) return BigInteger.valueOf(1);
        else return n.multiply(factor(n.subtract(BigInteger.valueOf(1))));
    }

    public static void main(String[] args) {
        new Thread(null, new Bin(), "", 64 * 1024 * 1024).start();
    }

    @Override
    public void run() {
        Bin b = new Bin();
        Scanner scanner = new Scanner(System.in);
        BigInteger div = BigInteger.valueOf(1000000007);
        BigInteger n = scanner.nextBigInteger();
        BigInteger k = scanner.nextBigInteger();
        BigInteger result = (b.factor(n).divide(b.factor(n.subtract(k)).multiply(b.factor(k))));
        System.out.println(result);
    }
}
