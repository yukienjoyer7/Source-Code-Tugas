import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class LuasBangunDatar {

    public static void main(String[] args) {
        double a,b,luas;
       Scanner scan = new Scanner (System.in);
       
       String s = scan.next();
       
       String sLower = s.toLowerCase();
       
       switch (sLower){

        case "persegi": 
        a = scan.nextDouble();
        luas = a*a;
        System.out.printf("%.2f", luas);
        break;

        case "segitiga":
        a = scan.nextDouble();
        b = scan.nextDouble();
        luas = a * b / 2;
        System.out.printf("%.2f", luas);
        break;

        case "lingkaran":
        a = scan.nextDouble();
        luas = a * a * 3.14;
        System.out.printf("%.2f", luas);
        break;

        default:
        System.out.println("Bangun datar tidak valid");
       }
    }
}