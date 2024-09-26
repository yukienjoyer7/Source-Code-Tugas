import java.util.*;
public class DataMahasigmaFilkom{
    private static final Scanner scan = new Scanner (System.in);

    public static void main(String[] args) {
        String Nama = scan.nextLine();
        long Nim = scan.nextLong();
        int Tahun = scan.nextInt();
        scan.nextLine();
        String Prodi = scan.nextLine();
        boolean Status = scan.nextBoolean();
        double IPK = scan.nextDouble();

        System.out.println("== DATA MAHASISWA ==");
        System.out.println("Nama : " + Nama);
        System.out.println("NIM : " + Nim);
        System.out.println("Angkatan : " + Tahun);
        System.out.println("Prodi : " + Prodi);
        System.out.println("Mahasiswa Aktif : " + Status);
        System.out.printf("IPK : %1.2f", IPK);
    }
}