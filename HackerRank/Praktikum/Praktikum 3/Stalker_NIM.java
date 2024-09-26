import java.util.Scanner;

public class Stalker_NIM {
       private static final Scanner scan = new Scanner (System.in);

    public static void main(String[] args) {
        String NIM = scan.next();
        scan.close();

        String Tahun = NIM.substring(0,2);
        String KodeF = NIM.substring(2, 5);
        String KodePS = NIM.substring(5,8);
        String KodeJM = NIM.substring(8, 12);
        String NomorMahasiswa = NIM.substring (12, NIM.length());

        System.out.print("Angkatan: ");System.out.print("20");System.out.println(Tahun);

        switch (KodeF){
            case "515" :System.out.println("Fakultas: FILKOM" );
            break;
            case "514" : System.out.println("Fakultas: FMIPA" );
            break;
            default : System.out.println("Fakultas: Unknown" );;

        }

        if (KodeF.equals("515")){
            switch (KodePS){
                case "020" : System.out.println("Program studi: Teknik Informatika" );;break;
                case "030" : System.out.println("Program studi: Teknik Komputer" );;break;
                case "040" : System.out.println("Program studi: Sistem Informasi" );;break;
                case "060" : System.out.println("Program studi: Pendidikan Teknologi Informasi" );;break;
                case "070" : System.out.println("Program studi: Teknologi Informasi" );break;
                default : System.out.println("Program studi: Unknown" );
            }
        }
        else{
            switch (KodePS){
                case "050" : System.out.println("Program studi: Biologi" );;break;
                case "051" : System.out.println("Program studi: Kimia" );;break;
                case "060" : System.out.println("Program studi: Fisika" );; break;
                case "061" : System.out.println("Program studi: Matematika" );;break;
                default : System.out.println("Program studi: Unknown" );
            }
        }
        
        System.out.print("Jalur masuk: ");
        switch (KodeJM){
            case "1111" : System.out.println("SNBP");break;   
            case "0111" : System.out.println("SNBT");break;
            case "7111" : System.out.println("Mandiri");break;
            default : System.out.println("Unknown");
        }

        System.out.print("Nomor mahasiswa: "); System.out.print(NomorMahasiswa);







}
}
