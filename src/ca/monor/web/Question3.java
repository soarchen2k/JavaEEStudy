package ca.monor.web;

public class Question3 {
    public static void main(String[] args) {
        int min = 1987;
        for (int shuxuejieti = 1234; shuxuejieti < 1987; shuxuejieti++) {
            for (int nengli = 12; nengli < 98; nengli++) {
                for (int zhanshi = 12; zhanshi < 98; zhanshi++) {
                    if (shuxuejieti + nengli + zhanshi == 2010 && nengli != zhanshi) {
                        System.out.println(shuxuejieti + " " + nengli + " " + zhanshi);
                        int diff = shuxuejieti - nengli;
                        if (diff < min) {
                            min = diff;
                        }
                    }
                }
            }
        }
        System.out.println(min);
    }
}
