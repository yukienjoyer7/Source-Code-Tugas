import java.util.*;
public class Damai_Eatery_Copy {
    public  static int sum = 0;
    private static final Scanner scan  = new Scanner (System.in);


    public static int OrderanMakanan (int orderMak, int jumlahMak){
        while (true){
            System.out.println("Pilih order dan masukkan jumlah:");
            orderMak = scan.nextInt();
            if (orderMak == 0){
                break;
            }
            else if(orderMak > 4){
                System.out.println("Pilihan tidak ada");
                continue;
            }
            jumlahMak = scan.nextInt();

            switch (orderMak){
                case 1: sum += 13000 * jumlahMak;break;
                case 2: sum += 9000 * jumlahMak;break;
                case 3: sum += 10000 * jumlahMak;break;
                case 4: sum += 10000 * jumlahMak;
            }
        }
        return sum;
    }


    public static int OrderanMinuman (int orderMin, int jumlahMin){
        while (true){
            System.out.println("Pilih order dan masukkan jumlah:");
            orderMin = scan.nextInt();
            if (orderMin == 0){
                break;
            }
            else if (orderMin > 3){
                System.out.println("Pilihan tidak ada");
                continue;
            }
            jumlahMin = scan.nextInt();

            switch (orderMin){
                case 1: sum += 5000 * jumlahMin;break;
                case 2: sum += 6000 * jumlahMin;break;
                case 3: sum += 8000 * jumlahMin;break;
            }
        }
        return sum;
    }

    public static void main(String[] args) {

        System.out.println("SELAMAT DATANG DI DAMAI'S EATERY");
        System.out.println("LIST MAKANAN: ");
        System.out.print("1. Nasi Lalapan 13.000\n" +  "2. Mi ayam 9.000\n" +  "3. Nasi Campur 10.000\n" +  "4. Nasi Pecel 10.000\n" + "0. Keluar\n");
        
        //MENGAMBIL ORDERAN MAKANAN
        OrderanMakanan(1,0);


        System.out.print("LIST MINUMAN: \n" + "1. Teh 5.000\n" + "2. Jeruk 6.000\n" + "3. Susu 8.000\n" + "0. Keluar\n");

        // MENGAMBIL ORDERAN MINUMAN
        OrderanMinuman (1,0);

        // MENERIMA JUMLAH UANG PELANGGAN
        System.out.println("Total belanja: " + sum);
        int Uang = 0;
        System.out.println("Masukkan uang untuk membayar:");
        Uang = scan.nextInt();
        while (Uang < sum){
            System.out.println("Uang yang dimasukkan kurang " + (sum-Uang) + ". Masukkan uang lagi:");
            Uang += scan.nextInt();
        }
        scan.close();
        if (Uang > sum){
            System.out.println("Kembalian: " + (Uang - sum));
        }
        System.out.println("TERIMA KASIH TELAH BERBELANJA DI DAMAI'S EATERY");
        
    }
}
