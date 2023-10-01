import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.io.IOException;


public class Driver {
    public static void main(String [] args) {
        Polynomial p = new Polynomial();
        System.out.println("hello world");
        double [] c1 = {11, -2, 5};
        int[] c11 = {8, 1, 3};
        Polynomial p1 = new Polynomial(c1, c11);
        double [] c2 = {2, 4, 7, 9};
        int [] c22 = {1, 2, 4, 3};
        Polynomial p2 = new Polynomial(c2, c22);
        Polynomial s = p1.multiply(p2);
        for (int i = 0; i < s.coefficients.length; i++) {
            System.out.println(s.coefficients[i]);
        }
        for (int i = 0; i < s.exponents.length; i++) {
            System.out.println(s.exponents[i]);
        }
        System.out.println("s(0.1) = " + s.evaluate(0.1));
        if(s.hasRoot(1)) {
            System.out.println("1 is a root of s");
        }
        else {
            System.out.println("1 is not a root of s");
        }

        File file = new File("new.txt");

        Polynomial p8 = new Polynomial();
        try {
            p8 = new Polynomial(file);

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();

        }
        for (int i = 0; i < p8.exponents.length; i++) {
            System.out.println(p8.exponents[i]);
        }

        try {
            s.saveToFile("new.txt");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();

        }
    }
}