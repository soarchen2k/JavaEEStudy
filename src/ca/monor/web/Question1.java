package ca.monor.web;

public class Question1 {
    public static void main(String[] args) {
        for (int 好 = 0; 好 <= 9; 好++) {
            for (int 快乐 = 90; 快乐 <=99 ; 快乐++) {
                for (int 平行线 =100 ; 平行线 <=999 ; 平行线++) {
                    for (int 励志 = 10; 励志 <= 99; 励志++) {
                        int sum = (快乐 * 1000 + 励志 * 1000 + 平行线 * 2);
                        if (sum == (励志 * 1000 + 平行线) * 10 + 好) {
                            System.out.println("快乐 = " + 快乐);
                            System.out.println("平行线 = " + 平行线);
                            System.out.println("励志 = " + 励志);
                            System.out.println("好 = " + 好);
                        }
                    }
                }
            }
        }
    }
}
