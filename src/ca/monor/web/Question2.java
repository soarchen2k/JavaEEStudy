package ca.monor.web;

import java.util.HashSet;
import java.util.Set;

public class Question2 {
    public static void main(String[] args) {

        Set<Integer> Shuxuehuayuan = new HashSet<>();
        for (int huayuan = 10; huayuan <= 99; huayuan++) {
            for (int tan = 1; tan <= 9; tan++) {
                for (int mi = 1; mi <=9 ; mi++) {
                    for (int shuxue = 10; shuxue <= 99; shuxue++) {
                        if (isPrime(shuxue)
                                && tan != mi
                                && (shuxue + huayuan * tan * mi) == 2015) {
                            Shuxuehuayuan.add(shuxue * 100 + huayuan);
                            System.out.println("shuxue = " + shuxue + " huayuan = " + huayuan + " tan = " + tan + " mi = " + mi);
                        }
                    }
                }
            }
        }
        for (int a : Shuxuehuayuan
        ) {
            System.out.println(a);
        }
    }

    public static boolean isPrime(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
