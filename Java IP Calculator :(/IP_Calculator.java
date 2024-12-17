import java.util.Scanner;

public class Tes {


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("----------------------------------------------------");
        System.out.println("Selamat datang! Program ini akan menghitung IP anda!");
        System.out.println("----------------------------------------------------");
        System.out.print("Masukkan jumlah mata kuliah anda:");
        int jumlah_matkul = scan.nextInt();
        scan.nextLine();
 
        String nama_matkul [] = new String[jumlah_matkul];
        char nilai_matkul[] = new char [jumlah_matkul];
        int bobot_matkul[] = new int [jumlah_matkul];
        double nilai_angka[] = new double[jumlah_matkul];
        double total_bobot = 0, total_nilai = 0;

        for (int i = 0;i < jumlah_matkul; i++){
            System.out.print("Masukkan nama matkul "+ (i+1) + ": ");
            nama_matkul[i] = scan.nextLine();
            System.out.print("Masukkan nilai huruf matkul "+ (i+1) + ": ");
            nilai_matkul[i] = scan.nextLine().toUpperCase().charAt(0);
            System.out.print("Masukkan bobot SKS matkul "+ (i+1) + ": ");
            bobot_matkul[i] = scan.nextInt();
            scan.nextLine();

            switch (nilai_matkul[i]) {
                case 'A': nilai_angka[i] = 4.0; break;
                case 'B': nilai_angka[i] = 3.0; break;
                case 'C': nilai_angka[i] = 2.0; break;
                case 'D': nilai_angka[i] = 1.0; break;
                case 'E': nilai_angka[i] = 0.0; break;
                default:
                    System.out.println("Nilai huruf tidak valid, dianggap E.");
                    nilai_angka[i] = 0.0;
            }

            total_bobot += bobot_matkul[i];
            total_nilai += nilai_angka[i] * bobot_matkul[i];
        }
        
        double ip = total_nilai / total_bobot;



        System.out.println("\nTabel Mata Kuliah");
        System.out.println("┌─────┬──────────────────────┬───────┬─────┐");
        System.out.printf("│ %-3s │ %-20s │ %-5s │ %-3s │\n", "No", "Nama Mata Kuliah", "Nilai", "SKS");
        System.out.println("├─────┼──────────────────────┼───────┼─────┤");
        for (int i = 0; i < jumlah_matkul; i++) {
            System.out.printf("│ %-3d │ %-20s │ %-5s │ %-3d │\n", 
                (i + 1), nama_matkul[i], "  " + nilai_matkul[i], bobot_matkul[i]);
        }
        System.out.println("└─────┴──────────────────────┴───────┴─────┘");


        System.out.printf("Indeks Prestasi (IP) Anda: %.2f\n", ip);

    }
}