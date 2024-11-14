import java.util.ArrayList;
import java.util.Scanner;

public class App {

    static int[][] data = {
        {81, 20000}, // Mangga
        {82, 30000}, // Apel
        {83, 20000}, // Rambutan
        {84, 15000}, // Jeruk
        {85, 60000}, // Durian
        {91, 20000}  // Pir
    };

    static String[] buahNama = {
        "Mangga", "Apel", "Rambutan", "Jeruk", "Durian", "Pir"
    };

    public static void start() {
        System.out.println("===================");
        System.out.println("Selamat datang di Superindo!");
        System.out.println("Daftar Buah Tersedia:");
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i][0] + " - " + buahNama[i] + " (Rp " + data[i][1] + " per kg)");
        }
        System.out.println("===================");
    }

    public static int hitungBelanja(Scanner scanner) {
        int totalBelanja = 0;
        boolean lanjutBelanja = true;

        while (lanjutBelanja) {
            System.out.print("Masukkan kode buah yang ingin dibeli: ");
            int kodeBuah = scanner.nextInt();
            int indexBuah = -1;


            for (int i = 0; i < data.length; i++) {
                if (data[i][0] == kodeBuah) {
                    indexBuah = i;
                    break;
                }
            }

            if (indexBuah == -1) {
                System.out.println("Kode buah tidak valid. Silakan coba lagi.");
                continue;
            }


            System.out.print("Masukkan berat " + buahNama[indexBuah] + " (kg): ");
            double berat = scanner.nextDouble();
            int hargaPerKg = data[indexBuah][1];

            int totalHarga = (int) (hargaPerKg * berat);
            totalBelanja += totalHarga;

            System.out.println("Harga " + buahNama[indexBuah] + ": Rp " + totalHarga);

            System.out.print("Apakah masih ada buah yang ingin dibeli? (Y (Ya)/T (Tidak)): ");
            char lanjut = scanner.next().charAt(0);
            if (lanjut != 'y' && lanjut != 'Y') {
                lanjutBelanja = false;
            }
        }

        return totalBelanja;
    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        start();
        int totalBelanja = hitungBelanja(scan);

        System.out.println("Total seluruh belanja Anda adalah: Rp " + totalBelanja);
        System.out.println("Terima kasih telah berbelanja!");
        scan.close();
    }
}
