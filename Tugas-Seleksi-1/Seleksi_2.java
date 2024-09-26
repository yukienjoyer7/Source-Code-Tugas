/*
Buatlah program komputer untuk menentukan nilai-huruf dari sebuah matakuliah yang nilainya terdiri dari:  nilai tugas, nilai kuis dan nilai UAS.

Bila nilai kuis > UAS, nilai akhir dihitung dengan rumus:
Nilai akhir = 40% (nilai-kuis) + 40% (UAS) + 20% (tugas).

Bila nilai UAS>= nilai-kuis, nilai akhir dihitung dengan rumus:
Nilai akhir = 30% (nilai-kuis) + 50% (UAS) + 20% (tugas).

Parameter hurufnya adalah sebagai berikut:
85 - 100   A       Sangat Baik
75 - 84    B       Baik
65 - 74    C       Cukup
50 - 64    D       Kurang
0 - 49     E       Gagal
 */

import java.util.*;
public class Seleksi_2 {

    private static final Scanner scan = new Scanner (System.in);

    public static void main(String[] args) {

        //Pesan awal serta pengenalan nilai huruf untuk Pengguna
        System.out.println("Selamat datang! Program ini akan menentukan nilai-huruf dari sebuah mata kuliah yang nilainya terdiri dari:Nilai Tugas, Nilai Kuis dan Nilai UAS Anda!");
        System.out.println("");
        System.out.println("Parameter dari nilai hurufnya adalah sebagai berikut: ");
        System.out.println("A: Sangat Baik");
        System.out.println("B: Baik");
        System.out.println("C: Cukup");
        System.out.println("D: Kurang");
        System.out.println("E: Gagal");
        System.out.println("");

        //Mendapatkan input dari pengguna
        System.out.print("Masukkan nilai Tugas Anda! (e.g 86,4): ");
        double nT = scan.nextDouble();
        System.out.print("Masukkan Nilai Kuis Anda! (e.g 92,0): ");
        double nK = scan.nextDouble();
        System.out.print("Masukkan Nilai UAS Anda! (e.g 76,5): ");
        double nUAS = scan.nextDouble();
        scan.close();

        //Menghitung Nilai Akhir
        double n_Akhir = 0.2 * nT;
        if (nK > nUAS){
            n_Akhir += 0.4 * nK + 0.4 * nUAS; 
        }
        else{
            n_Akhir += 0.3 * nK + 0.5 * nUAS;
        }

        //Membulatkan Nilai Akhir menjadi 1 angka di belakang koma
        n_Akhir = Math.round((n_Akhir * 10)) / 10.0;

        //Printout hasilnya
        if (85 <= n_Akhir){
            System.out.println("Nilai akhir Anda adalah " + n_Akhir + ", Selamat! Anda mendapat nilai A.");
        }
        else if (75 <= n_Akhir && n_Akhir <= 84.9){
            System.out.println("Nilai akhir Anda adalah " + n_Akhir + ", Selamat! Anda mendapat nilai B.");
        }
        else if (65 <= n_Akhir && n_Akhir <= 74.9){
            System.out.println("Nilai akhir Anda adalah " + n_Akhir + ", Anda mendapat nilai C.");
        } 
        else if (n_Akhir <= 49.9){
            System.out.println("Nilai akhir Anda adalah " + n_Akhir + ", Anda mendapat nilai E.");
        }
        else{
            System.out.println("Nilai akhir Anda adalah " + n_Akhir + ", Anda mendapat nilai D.");
        }  
    }
}     
