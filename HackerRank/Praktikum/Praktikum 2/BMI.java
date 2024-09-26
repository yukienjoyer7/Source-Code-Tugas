import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class BMI {

    private static final Scanner scan = new Scanner (System.in);
    public static void main(String[] args) {
        double weight = scan.nextDouble();
        double height = scan.nextDouble();
        scan.close();

        height /= 100;

        double BMI = weight / (height * height);

        if (BMI <= 18){
            System.out.println("Underweight");
        }
        else if (BMI > 18 && BMI <= 25){
            System.out.println("Normal");
        }
        else if (BMI > 40){
            System.out.println("Obese");
        }
        else {
            System.out.println("Overweight");
        }
    }
}