import java.util.*;

/**
 * Program TicTacToe dengan opsi permainan Player vs Player (PvP) atau Player vs Bot (PvB).
 * Tersedia tiga tingkat kesulitan untuk bot: Easy, Medium, dan Hard.
 * 
 * Fitur utama:
 * <p>- Menampilkan papan permainan. 
 * <p>- Input langkah pemain.
 * <p>- Bot dengan logika minimax untuk tingkat kesulitan Hard.
 * <p>- Pengecekan kondisi akhir permainan (menang/kalah/seri).
 * 
 * 
 * @author Dionisius Seraf Saputra
 * @version 1.0
 */
public class TicTacToe {

    /** 
    * Matriks 3x3 untuk merepresentasikan papan permainan.
    * Inisialisasi papan dengan karakter kosong (' ').
    */
    static char [][] input = {
    {' ', ' ', ' '},
    {' ', ' ', ' '},
    {' ', ' ', ' '},}; // Mulai dengan ruang kosong

    
    /**
    * Counter giliran, digunakan untuk menentukan giliran pemain.
    */
    static int counter = 1;

    /**
    * Scanner untuk membaca input dari pemain.
    */
    private static Scanner scan = new Scanner(System.in);
    
    /**
    * Menampilkan papan permainan saat ini ke layar.
    */
    public static void PrintBoard() {
        System.out.println("Papan saat ini:");
        System.out.println("            Kol Kol Kol");
        System.out.println("             1   2   3"); // Label kolom
        System.out.println("           -------------");
    
        for (int i = 0; i < 3; i++) {
            System.out.print("Baris " + (i+1) + "    "); // Label baris
            for (int j = 0; j < 3; j++) {
                System.out.print("| " + input[i][j] + " ");
            }
            System.out.println("|");
            System.out.println("           -------------"); // Garis pemisah antar baris
        }
    }

    /**
    * Mengatur langkah bot berdasarkan tingkat kesulitan.
    * 
    * @param Bot karakter bot ('X' atau 'O').
    * @param Player karakter lawan bot.
    * @param difficulty tingkat kesulitan (1 = Easy, 2 = Medium, 3 = Hard).
    */
    public static void inputPlayer (char player){
        int row, column;

        while (true){
            System.out.printf("Masukkan baris (1, 2, atau 3) untuk pemain %c: ", player);
            row = scan.nextInt() - 1;
            System.out.printf("Masukkan kolom (1, 2, atau 3) untuk pemain %c: ", player);
            column = scan.nextInt() - 1;
                
            // Cek apakah input berada di luar batas papan
            if (row < 0 || row > 2 || column < 0 || column > 2) {
                System.out.println("Langkah tidak valid: Baris dan kolom harus antara 1 dan 3. Coba lagi.");
            }
            // Cek apakah posisi sudah ditempati
            else if (input[row][column] != ' ') {
                System.out.println("Langkah tidak valid: Sel sudah ditempati. Coba lagi.");
            }
            // Input valid
            else {
                input[row][column] = player;
                break;
            }
        }
    }

    /**
    * Mengatur langkah bot berdasarkan tingkat kesulitan.
    * 
    * @param Bot karakter bot ('X' atau 'O').
    * @param Player karakter lawan bot.
    * @param difficulty tingkat kesulitan (1 = Easy, 2 = Medium, 3 = Hard).
    */
    public static void inputBot(char Bot, char Player, int difficulty) {
        try {
            System.out.println("Bot sedang berpikir...");
            int RANDOM = (int)(Math.random() + 2) * 1000;
            Thread.sleep(RANDOM);
        } catch (InterruptedException e) {
        }

        switch (difficulty) {
            case 1:
                BotLogic_Easy(Bot);
                break;
            case 2:
                BotLogic_Medium(Bot, Player);
                break;
            case 3:
                BotLogic_Hard(Bot);
                break;
        }
    }   

    /**
    * Logika bot tingkat Easy: memilih langkah secara acak.
    * 
    * @param Bot karakter bot ('X' atau 'O').
    */
    public static void BotLogic_Easy(char Bot){
        while (true){
            int row_random = (int)(Math.random() * 3);
            int column_random = (int)(Math.random() * 3);
    
            if (input[row_random][column_random] == ' '){
                input[row_random][column_random] = Bot;
                break;
            }
        }
    }
     
   /**
    * Logika bot tingkat Medium: memprioritaskan menang atau memblok langkah lawan.
    * 
    * @param Bot karakter bot ('X' atau 'O').
    * @param Player karakter lawan bot.
    */
    public static void BotLogic_Medium(char Bot, char Player){

        if (winOne(Bot, Player)){
            return; 
        }
        else if(blockOne(Bot, Player)){
            return;
        }

        BotLogic_Easy(Bot);
    }

    /**
    * Logika bot tingkat Hard: menggunakan algoritma Minimax untuk memilih langkah terbaik.
    * 
    * @param Bot karakter bot ('X' atau 'O').
    */
    public static void BotLogic_Hard(char Bot) {
        int bestScore = Integer.MIN_VALUE; // Mulai dengan nilai minimum
        int bestRow = -1, bestCol = -1;
    
        // Iterasi semua sel di papan untuk menemukan langkah terbaik
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Jika sel kosong
                if (input[i][j] == ' ') {
                    input[i][j] = Bot; // Coba langkah Bot
                    int score = minimax(false, Bot); // Panggil Minimax untuk pemain lawan
                    input[i][j] = ' '; // Kembalikan papan seperti semula
                    if (score > bestScore) { // Jika skor langkah ini lebih baik
                        bestScore = score;
                        bestRow = i;
                        bestCol = j;
                    }
                }
            }
        }
        // Buat langkah terbaik
        if (bestRow != -1 && bestCol != -1) {
            input[bestRow][bestCol] = Bot;
        }
    }
    
    /**
    * Algoritma Minimax untuk mengevaluasi langkah terbaik bot.
    * 
    * @param isMaximizing apakah giliran untuk memaksimalkan (bot) atau meminimalkan (lawan).
    * @param Bot karakter bot ('X' atau 'O').
    * @return <strong><i>10</i></strong>, Jika bot menang.
    * <li><strong><i>-10</i></strong>, Jika bot kalah.
    * <li><strong><i>0</i></strong>, Jika hasil permainan seri.
    */
    public static int minimax(boolean isMaximizing, char Bot) {
        char Player = (Bot == 'X') ? 'O' : 'X'; // Tentukan lawan
        int score = evaluate(Bot); // Evaluasi skor papan saat ini
    
        // Basis Kasus: Jika permainan selesai
        if (score == 10 || score == -10) return score; // Menang/Kalah
        if (isDraw()) return 0; // Seri
    
        if (isMaximizing) { // Bot mencoba memaksimalkan
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (input[i][j] == ' ') {
                        input[i][j] = Bot; // Coba langkah Bot
                        bestScore = Math.max(bestScore, minimax(false, Bot)); // Rekursi untuk lawan
                        input[i][j] = ' '; // Kembalikan papan
                    }
                }
            }
            return bestScore;
        } else { // Pemain mencoba meminimalkan
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (input[i][j] == ' ') {
                        input[i][j] = Player; // Coba langkah lawan
                        bestScore = Math.min(bestScore, minimax(true, Bot)); // Rekursi untuk Bot
                        input[i][j] = ' '; // Kembalikan papan
                    }
                }
            }
            return bestScore;
        }
    }
    
    /**
    * Mengevaluasi skor papan permainan saat ini.
    * 
    * @param Bot karakter bot ('X' atau 'O').
    * @return 
    * <strong><i>10</i></strong>, Jika bot menang. 
    * <li><strong><i>-10</i></strong>, Jika bot kalah.
    * <li><strong><i>0</i></strong>, Jika hasil permainan seri.
    */
    public static int evaluate(char Bot) {
        char Player = (Bot == 'X') ? 'O' : 'X';
    
        // Periksa baris
        for (int i = 0; i < 3; i++) {
            if (input[i][0] == input[i][1] && input[i][1] == input[i][2]) {
                if (input[i][0] == Bot) return 10;
                if (input[i][0] == Player) return -10;
            }
        }
    
        // Periksa kolom
        for (int j = 0; j < 3; j++) {
            if (input[0][j] == input[1][j] && input[1][j] == input[2][j]) {
                if (input[0][j] == Bot) return 10;
                if (input[0][j] == Player) return -10;
            }
        }
    
        // Periksa diagonal
        if (input[0][0] == input[1][1] && input[1][1] == input[2][2]) {
            if (input[0][0] == Bot) return 10;
            if (input[0][0] == Player) return -10;
        }
        if (input[0][2] == input[1][1] && input[1][1] == input[2][0]) {
            if (input[0][2] == Bot) return 10;
            if (input[0][2] == Player) return -10;
        }
    
        return 0; // Tidak ada pemenang
    }

    /**
    * Mengecek apakah permainan berakhir dengan hasil seri.
    * 
    * @return 
    * <strong><i>True</i></strong> jika ada ruang yang kosong. 
    * <li><strong><i>False</i></strong> jika tidak.
    */
    public static boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (input[i][j] == ' ') return false; // Jika ada ruang kosong, belum seri
            }
        }
        return true;
    }

    /**
    * Logika bot untuk mengambil langkah menang jika memungkinkan.
    * 
    * @param Bot karakter bot ('X' atau 'O').
    * @param Player karakter lawan bot.
    * @return 
    * <strong><i>True</i></strong> jika ada langkah menang yang mungkin. 
    * <li><strong><i>False</i></strong> jika tidak.
    */
    public static Boolean winOne (char Bot, char Player){
        // Cek setiap baris
        for (int i = 0; i < 3; i++) {
            if (input[i][0] == Bot && input[i][1] == Bot && input[i][2] == ' ') {
                input[i][2] = Bot;
                return true;
            } else if (input[i][1] == Bot && input[i][2] == Bot && input[i][0] == ' ') {
                input[i][0] = Bot;
                return true;
            } else if (input[i][0] == Bot && input[i][2] == Bot && input[i][1] == ' ') {
                input[i][1] = Bot;
                return true;
            }
        }
        
        // Cek setiap kolom
        for (int j = 0; j < 3; j++) {
            if (input[0][j] == Bot && input[1][j] == Bot && input[2][j] == ' ') {
                input[2][j] = Bot;
                return true;
            } else if (input[1][j] == Bot && input[2][j] == Bot && input[0][j] == ' ') {
                input[0][j] = Bot;
                return true;
            } else if (input[0][j] == Bot && input[2][j] == Bot && input[1][j] == ' ') {
                input[1][j] = Bot;
                return true;
            }
        }
        
        // Cek diagonal

        // Diagonal Kiri (row 0,0)
        if (input[0][0] == Bot && input[1][1] == Bot && input[2][2] == ' ') {
            input[2][2] = Bot;
            return true;
        } else if (input[1][1] == Bot && input[2][2] == Bot && input[0][0] == ' ') {
            input[0][0] = Bot;
            return true;
        } else if (input[0][0] == Bot && input[2][2] == Bot && input[1][1] == ' ') {
            input[1][1] = Bot;
            return true;
        }
        
        // Diagonal Kanan (row 0,2)
        if (input[0][2] == Bot && input[1][1] == Bot && input[2][0] == ' ') {
            input[2][0] = Bot;
            return true;
        } else if (input[1][1] == Bot && input[2][0] == Bot && input[0][2] == ' ') {
            input[0][2] = Bot;
            return true;
        } else if (input[0][2] == Bot && input[2][0] == Bot && input[1][1] == ' ') {
            input[1][1] = Bot;
            return true;
        }
        
        return false;
    }

    /**
    * Logika bot untuk memblok langkah lawan yang dapat menang.
    * 
    * @param Bot karakter bot ('X' atau 'O').
    * @param Player karakter lawan bot.
    * @return 
    * <strong><i>True</i></strong> jika ada langkah blok yang mungkin. 
    * <li><strong><i>False</i></strong> jika tidak.
    */
    public static Boolean blockOne (char Bot, char Player){
        // Cek setiap baris
        for (int i = 0; i < 3; i++) {
            if (input[i][0] == Player && input[i][1] == Player && input[i][2] == ' ') {
                input[i][2] = Bot;
                return true;
            } else if (input[i][1] == Player && input[i][2] == Player && input[i][0] == ' ') {
                input[i][0] = Bot;
                return true;
            } else if (input[i][0] == Player && input[i][2] == Player && input[i][1] == ' ') {
                input[i][1] = Bot;
                return true;
            }
        }
        
        // Cek setiap kolom
        for (int j = 0; j < 3; j++) {
            if (input[0][j] == Player && input[1][j] == Player && input[2][j] == ' ') {
                input[2][j] = Bot;
                return true;
            } else if (input[1][j] == Player && input[2][j] == Player && input[0][j] == ' ') {
                input[0][j] = Bot;
                return true;
            } else if (input[0][j] == Player && input[2][j] == Player && input[1][j] == ' ') {
                input[1][j] = Bot;
                return true;
            }
        }
        
        // Cek diagonal

        // Diagonal Kiri (row 0,0)
        if (input[0][0] == Player && input[1][1] == Player && input[2][2] == ' ') {
            input[2][2] = Bot;
            return true;
        } else if (input[1][1] == Player && input[2][2] == Player && input[0][0] == ' ') {
            input[0][0] = Bot;
            return true;
        } else if (input[0][0] == Player && input[2][2] == Player && input[1][1] == ' ') {
            input[1][1] = Bot;
            return true;
        }
        
        // Diagonal Kanan (row 0,2)
        if (input[0][2] == Player && input[1][1] == Player && input[2][0] == ' ') {
            input[2][0] = Bot;
            return true;
        } else if (input[1][1] == Player && input[2][0] == Player && input[0][2] == ' ') {
            input[0][2] = Bot;
            return true;
        } else if (input[0][2] == Player && input[2][0] == Player && input[1][1] == ' ') {
            input[1][1] = Bot;
            return true;
        }
        
        return false;
    }


    /**
    * Pengaturan permainan Player vs Player (PvP).
    * Mengatur giliran pemain secara bergantian.
    */
    public static void SettingPvP() {
        inputPlayer((counter % 2 != 0) ? 'X' : 'O');
        ++counter;
    }

    /**
    * Pengaturan permainan Player vs Bot (PvB).
    * Menentukan giliran berdasarkan pilihan pemain dan tingkat kesulitan bot.
    * 
    * @param state status giliran dari pengguna (1 = pemain pertama (X), 2 = pemain kedua (O)).
    * @param difficulty tingkat kesulitan bot.
    */
    public static void SettingPvB(int state, int difficulty) {

        // State 1, Player = X, Bot = O
        if (state == 1) {
            if (counter % 2 != 0) {
                inputPlayer('X');
            } else {
                inputBot('O', 'X', difficulty);
            }
        }
        // State 2, Player = O, Bot = X
        else if (state == 2) {
            if (counter % 2 != 0) {
                inputBot('X', 'O', difficulty);
            } else {
                inputPlayer('O');
            }
            
        }
        counter++;
    }

    /**
    * Mengecek apakah permainan sudah berakhir (menang, kalah, atau seri).
    * 
    * @return 
    * <strong><i>True</i></strong> jika permainan masih berlanjut. 
    * <li><strong><i>False</i></strong> jika tidak.
    */
    public static boolean checkIfEnd(){
        boolean vertical = checkVertical();
        boolean horizontal = checkHorizontal();
        boolean diagonal = checkDiagonal();

        if (vertical || horizontal || diagonal){
            PrintBoard();
            System.out.println("======================= Game Over =====================");
            System.out.println("                  Pemenangnya adalah " + ((counter % 2 == 0) ? 'X' : 'O') + "!");
            return true;
        }
        else if (isDraw()){
            PrintBoard();
            System.out.println("======================= Game Over =====================");
            System.out.println("                Permainan berakhir seri!");
            return true;
        }
        else{
            return false;
        }
    }

    /**
    * Mengecek apakah ada pemenang pada diagonal.
    * 
    * @return 
    * <strong><i>True</i></strong> jika ada pemenang pada diagonal. 
    * <li><strong><i>False</i></strong> jika tidak.
    */
    public static boolean checkDiagonal(){
        boolean diagonal_kiri = ((input[0][0] == input[1][1] && input[1][1] == input[2][2]) && input[0][0] != ' ');
        boolean diagonal_kanan = ((input[0][2] == input[1][1] && input[1][1] == input[2][0]) && input[0][2] != ' ');
        return diagonal_kiri || diagonal_kanan;
    }

    /**
    * Mengecek apakah ada pemenang pada baris.
    * 
    * @return 
    * <strong><i>True</i></strong> jika ada pemenang pada baris. 
    * <li><strong><i>False</i></strong> jika tidak.
    */
    public static boolean checkHorizontal() {
        for (int row = 0; row < 3; row++) {
            if (input[row][0] == input[row][1] && input[row][1] == input[row][2] && input[row][0] != ' ') {
                return true; 
            }
        }
        return false; 
    }
    
    /**
    * Mengecek apakah ada pemenang pada kolom.
    * 
    * @return 
    * <strong><i>True</i></strong> jika ada pemenang pada kolom. 
    * <li><strong><i>False</i></strong> jika tidak.
    */
    public static boolean checkVertical() {
        for (int columnLoop = 0; columnLoop < 3; columnLoop++) {
            if (input[0][columnLoop] == input[1][columnLoop] && input[1][columnLoop] == input[2][columnLoop] && input[0][columnLoop] != ' ') {
                return true; 
            }
        }
        return false; 
    }
    
    /**
    * Memulai permainan TicTacToe.
    * Pemain dapat memilih mode PvP atau PvB, serta tingkat kesulitan bot.
    */
    public static void start(){       
        boolean end = false;  
        System.out.println("Selamat datang di permainan Tic Tac Toe!");
        System.out.println("Silakan pilih mode permainan:");
        System.out.println("1. Player vs Player");
        System.out.println("2. Player vs Bot");
        System.out.print("Mode yang Anda pilih (1/2): ");
        int mode = scan.nextInt();

        if(mode == 1){
            while (!end){
                PrintBoard();
                SettingPvP();
                end = checkIfEnd();
            }
        }
        if(mode == 2){
            System.out.println("Silakan pilih pion Anda:");
            System.out.println("1. X (Giliran pertama)");
            System.out.println("2. O (Giliran kedua)");
            System.out.print("Ketikkan angka dari pion yang anda mau (1/2): ");
            int side = scan.nextInt();
            int difficulty = -1;

            while(difficulty >= 4 || difficulty <= 0){
                System.out.println("Pilih tingkat kesulitan Bot:");
                System.out.println("1. Easy");
                System.out.println("2. Medium");
                System.out.println("3. Hard");
                System.out.print("Tingkat kesulitan yang Anda pilih (1/2/3): ");
                difficulty = scan.nextInt();
                if (difficulty >= 4 || difficulty <= 0){
                    System.out.println("Tingkat kesulitan tidak valid. Mohon masukkan opsi yang ada (1/2/3): ");
                }
            }        
            while (!end){
                PrintBoard();
                SettingPvB(side, difficulty);
                end = checkIfEnd();
            }
        }
    }

    /**
    * Metode utama untuk menjalankan program.
    * 
    * @param args argumen baris perintah (tidak digunakan).
    */
    public static void main(String[] args) {
        start();
    }
}
