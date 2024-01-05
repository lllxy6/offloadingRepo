package test;

public class testSyntax {
    public static void main(String[] args) {
        int n = 4;

        for (int i = 0; i < 100; i++) {
            System.out.printf("%04d\n", i);  // can not use variable n to replace width 4
        }
    }
}
