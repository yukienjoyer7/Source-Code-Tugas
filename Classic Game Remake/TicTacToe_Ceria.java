import java.util.Scanner;
import java.lang.Thread;

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
public class TicTacToe_Ceria {

    /** 
    * Matriks 3x3 untuk merepresentasikan papan permainan.
    * Inisialisasi papan dengan karakter kosong (' ').
    */
    static char [][] board = {
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
     * Menampilkan efek loading berupa sejumlah titik dengan jeda waktu tertentu.
     * Jumlah titik ditentukan secara acak antara 3 hingga 5.
     * 
     * <p>Contoh keluaran:</p>
     * <pre>
     * ...
     * .....
     * </pre>
     * 
     * @param durationInMilliseconds durasi jeda antar titik dalam milidetik.
     *                               Misalnya, jika 500 diberikan, akan ada jeda 500 ms antara tiap titik.
     * 
     * @throws InterruptedException wajib ada.
     */
    public static void loading(int durationInMilliseconds) {
        int amountOfDots = (int) (Math.random() * 3) + 3; // Random antara 3 hingga 5
        for (int i = 0; i < amountOfDots; i++) {          // Ulang sebanyak amountOfDots
            System.out.print(".");
            try {
                Thread.sleep(durationInMilliseconds);    // Beri jeda selama durationInMilliseconds
            } catch (InterruptedException e) {
                System.err.println("sigma banget bang");
            }
        }
        System.out.println(); // Tambahkan newline setelah loading selesai
    }

    /**
     * Menampilkan pesan akhir permainan (Game Over) pada konsol.

     * <b>Contoh keluaran:</b>
     * Untuk mode Player vs Player:
     * <pre>
     * ======================= Game Over =====================
     *              Pemenangnya adalah Player1(X)!
     *=======================================================
     * </pre>
     *
     * Untuk mode Player vs Bot:
     * <pre>
     * ======================= Game Over =====================
     *              Pemenangnya adalah Bot (X)!
     *=======================================================
     * 
     * </pre>
     *
    * @param result hasil akhir permainan, bisa berupa "Win" untuk kemenangan atau "Draw" untuk seri.
    * @param mode mode permainan:
    *         <ul>
    *             <li>1: Player vs Player.</li>
    *             <li>2: Player vs Bot.</li>
    *         </ul>
    * @param state status pemain dalam mode Player vs Bot:
    *         <ul>
    *             <li>1: Player adalah X dan Bot adalah O.</li>
    *             <li>2: Player adalah O dan Bot adalah X.</li>
    *         </ul>
    */
    public static void printGameOver(String result, int mode, int state){
        System.out.println("======================= Game Over =====================");
        if (result.equals("Win")){
            System.out.print("              Pemenangnya adalah ");
            if (mode == 1){
                System.out.println("Player" + ((counter % 2 != 0) ? "2(O)" : "1(X)") + "!");
            }
            else if (mode == 2){
                if (state == 1){
                    System.out.println(((counter % 2 != 0) ?  "Bot (O)" : "Player (X)") + "!");
                }
                else if (state == 2){
                    System.out.println(((counter % 2 != 0) ?  "Player (O)" : "Bot (X)") + "!");
                }
            }
        }

        else if (result.equals("Draw")){
            System.out.println("                Permainan berakhir seri!");        }
            System.out.println("=======================================================");
    }

    /**
     * Menampilkan papan permainan Tic Tac Toe ke konsol.
     * Papan terdiri dari 3x3 kotak dengan label baris dan kolom untuk mempermudah referensi.
     * 
     * <p>Contoh tampilan papan permainan:</p>
     * <pre>
     *            Kol Kol Kol
     *             1   2   3
     *           -------------
     * Baris 1   | X | O | X |
     *           -------------
     * Baris 2   |   | X |   |
     *           -------------
     * Baris 3   | O |   | O |
     *           -------------
     * </pre>
     * 
     * <p>Catatan:</p>
     * <ul>
     *   <li>Papan permainan diisi berdasarkan array global `input` 2D (3x3).</li>
     *   <li>Setiap elemen di array merepresentasikan simbol ('X', 'O', atau spasi kosong).</li>
     * </ul>
     */
    public static void printBoard() {
        System.out.println("            Kol Kol Kol");
        System.out.println("             1   2   3"); // Label kolom
        System.out.println("           -------------");
    
        for (int i = 0; i < 3; i++) {
            System.out.print("Baris " + (i+1) + "    "); // Label baris
            for (int j = 0; j < 3; j++) {
                System.out.print("| " + board[i][j] + " ");
            }
            System.out.println("|");
            System.out.println("           -------------"); // Garis pemisah antar baris
        }
    }

    /**
     * Mengosongkan papan permainan Tic Tac Toe dengan mengatur semua kotak menjadi spasi kosong.
     * 
     * <p>Metode ini digunakan untuk mereset kondisi papan sebelum memulai permainan baru.</p>
     * 
     * <p>Efek dari metode ini:</p>
     * <ul>
     *   <li>Semua elemen dalam array global `input` 2D (3x3) diisi dengan karakter spasi (' ').</li>
     *   <li>Papan permainan akan terlihat kosong saat dicetak menggunakan {@link #printBoard()}.</li>
     * </ul>
     * 
     * <p>Contoh hasil array setelah pemanggilan metode:</p>
     * <pre>
     * input = {
     *   {' ', ' ', ' '},
     *   {' ', ' ', ' '},
     *   {' ', ' ', ' '}
     * };
     * </pre>
     */
    public static void clearBoard(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ' ;
            }
        }
    }

    /**
     * Memproses input langkah pemain untuk permainan Tic Tac Toe.
     * 
     * <p>Metode ini meminta pemain untuk memasukkan baris dan kolom tempat mereka ingin meletakkan simbol
     * mereka di papan. Langkah tersebut akan divalidasi sebelum diterima:</p>
     * <ul>
     *   <li>Pemain harus memasukkan baris dan kolom yang berada dalam rentang valid (1-3).</li>
     *   <li>Pemain tidak dapat memilih sel yang sudah ditempati.</li>
     * </ul>
     * 
     * @param currentPlayer karakter pemain yang akan menandai langkahnya di papan (misalnya 'X' atau 'O').
     * 
     * <p>Proses input:</p>
     * <ol>
     *   <li>Pemain diminta memasukkan nomor baris dan kolom.</li>
     *   <li>Jika input berada di luar batas (kurang dari 1 atau lebih dari 3), pemain akan diminta untuk mengulang.</li>
     *   <li>Jika sel pada baris dan kolom yang dipilih sudah terisi, pemain juga akan diminta untuk mengulang.</li>
     *   <li>Jika input valid, simbol pemain ditambahkan ke papan pada posisi yang dipilih.</li>
     * </ol>
     * 
     * <p>Contoh penggunaan:</p>
     * <pre>
     * // Pemain X memilih baris 1 kolom 2
     * inputPlayer('X');
     * // Papan akan diperbarui dengan simbol X pada posisi (0, 1) dalam array.
     * </pre>
     */
    public static void inputPlayer (char currentPlayer){
        int row, column;

        while (true){
            System.out.printf("Masukkan baris (1, 2, atau 3) untuk pemain %c: ", currentPlayer);
            row = scan.nextInt() - 1;
            while(true){
                if (row > 2 || row < 0){
                    System.out.print("Langkah tidak valid: Baris harus antara 1 dan 3. Coba lagi.(1/2/3): ");
                    row = scan.nextInt() - 1;
                    continue;
                }
                break;
            }
            
            System.out.printf("Masukkan kolom (1, 2, atau 3) untuk pemain %c: ", currentPlayer);
            column = scan.nextInt() - 1;
            while (true){
                if (column > 2 || column < 0){
                    System.out.print("Langkah tidak valid: Kolom harus antara 1 dan 3. Coba lagi.(1/2/3): ");
                    row = scan.nextInt() - 1;
                    continue;
                }
                break;
            }
            
            // Cek apakah posisi sudah ditempati
            if (board[row][column] != ' ') {
                System.out.println("Langkah tidak valid: Sel sudah ditempati. Coba lagi.");
            }
            // Input valid
            else {
                board[row][column] = currentPlayer;
                break;
            }
        }
    }

    /**
     * Mengatur langkah bot dalam permainan Tic Tac Toe berdasarkan tingkat kesulitan yang dipilih.
     * 
     * <p>Metode ini menentukan langkah bot dengan memanggil logika bot yang sesuai
     * berdasarkan tingkat kesulitan. Sebelum langkah bot dilakukan, akan ada animasi
     * bot "berpikir" dengan durasi tertentu yang bervariasi sesuai tingkat kesulitan.</p>
     * 
     * @param Bot karakter bot ('X' atau 'O').
     * @param Player karakter lawan bot.
     * @param difficulty tingkat kesulitan (1 = Easy, 2 = Medium, 3 = Hard).
     */
    public static void inputBot(char Bot, char Player, int difficulty) {
        System.out.print("Bot Sedang Berpikir");
        int duration_in_milliseconds;
        if (difficulty == 1){
            duration_in_milliseconds = 400;
        }
        else if (difficulty == 2){
            duration_in_milliseconds = 600;
        }
        else{
            duration_in_milliseconds = 800;
        }
        loading(duration_in_milliseconds);

        switch (difficulty) {
            case 1:
                botLogic_Easy(Bot);
                break;
            case 2:
                botLogic_Medium(Bot, Player);
                break;
            case 3:
                botLogic_Impossible(Bot);
                break;
            default:
                System.out.println("Congrats, You found a bug, please contact me at discord @crtal7, and kindly tell me about the bug :D");
                break;
        }
    }   

    /**
     * Mengimplementasikan logika untuk bot pada mode "Easy" dalam permainan Tic-Tac-Toe.
     * Bot akan memilih secara acak sel kosong pada papan dan menempatkan simbolnya.
     *
     * @param Bot karakter yang mewakili simbol bot (misalnya, 'X' atau 'O').
     *            Simbol ini akan ditempatkan oleh bot pada papan.
     */
    public static void botLogic_Easy(char Bot) {
        while (true) {
            int row_random = (int)(Math.random() * 3);
            int column_random = (int)(Math.random() * 3);

            if (board[row_random][column_random] == ' ') {
                board[row_random][column_random] = Bot;
                break;
            }
        }
    }
     
    /**
     * <p>
     * Mengimplementasikan logika untuk bot pada mode "Sedang" dalam permainan Tic-Tac-Toe.
     * Bot akan memprioritaskan langkah untuk memenangkan permainan, 
     * atau memblokir langkah pemain yang dapat menang, 
     * jika tidak ada opsi tersebut, bot akan memilih langkah secara acak.
     *  </p>
     * @param Bot karakter yang mewakili simbol bot (misalnya, 'X' atau 'O').
     * @param Player karakter yang mewakili simbol pemain (misalnya, 'X' atau 'O').
     */
    public static void botLogic_Medium(char Bot, char Player) {
        if (winOne(Bot, Player)) {
            return; 
        } else if (blockOne(Bot, Player)) {
            return;
        }

        botLogic_Easy(Bot);
    }

    /**
     * Logika bot tingkat Impossible: menggunakan algoritma Minimax untuk memilih langkah terbaik.
     * 
     * @param Bot karakter bot ('X' atau 'O').
     */
    public static void botLogic_Impossible(char Bot) {
        int bestScore = Integer.MIN_VALUE; // Mulai dengan nilai minimum
        int bestRow = -1, bestCol = -1;
    
        // Iterasi semua sel di papan untuk menemukan langkah terbaik
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Jika sel kosong
                if (board[i][j] == ' ') {
                    board[i][j] = Bot; // Coba langkah Bot
                    int score = minimax(false, Bot); // Panggil Minimax untuk pemain lawan
                    board[i][j] = ' '; // Kembalikan papan seperti semula
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
            board[bestRow][bestCol] = Bot;
        }
    }
    
    /**
     * Memeriksa apakah bot memiliki kesempatan untuk memenangkan permainan
     * dalam satu langkah dan menempatkan simbolnya untuk menang jika memungkinkan.
     *
     * @param Bot karakter yang mewakili simbol bot (misalnya, 'X' atau 'O').
     * @param Player karakter yang mewakili simbol pemain (misalnya, 'X' atau 'O').
     * @return
     * <code>True</code> jika bot bisa menang dalam satu langkah. 
     * <li><code>False</code> jika tidak ada langkah kemenangan.
     */
    public static Boolean winOne (char Bot, char Player){
        // Cek setiap baris
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == Bot && board[i][1] == Bot && board[i][2] == ' ') {
                board[i][2] = Bot;
                return true;
            } else if (board[i][1] == Bot && board[i][2] == Bot && board[i][0] == ' ') {
                board[i][0] = Bot;
                return true;
            } else if (board[i][0] == Bot && board[i][2] == Bot && board[i][1] == ' ') {
                board[i][1] = Bot;
                return true;
            }
        }
        
        // Cek setiap kolom
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == Bot && board[1][j] == Bot && board[2][j] == ' ') {
                board[2][j] = Bot;
                return true;
            } else if (board[1][j] == Bot && board[2][j] == Bot && board[0][j] == ' ') {
                board[0][j] = Bot;
                return true;
            } else if (board[0][j] == Bot && board[2][j] == Bot && board[1][j] == ' ') {
                board[1][j] = Bot;
                return true;
            }
        }
        
        // Cek diagonal

        // Diagonal Kiri (row 0,0)
        if (board[0][0] == Bot && board[1][1] == Bot && board[2][2] == ' ') {
            board[2][2] = Bot;
            return true;
        } else if (board[1][1] == Bot && board[2][2] == Bot && board[0][0] == ' ') {
            board[0][0] = Bot;
            return true;
        } else if (board[0][0] == Bot && board[2][2] == Bot && board[1][1] == ' ') {
            board[1][1] = Bot;
            return true;
        }
        
        // Diagonal Kanan (row 0,2)
        if (board[0][2] == Bot && board[1][1] == Bot && board[2][0] == ' ') {
            board[2][0] = Bot;
            return true;
        } else if (board[1][1] == Bot && board[2][0] == Bot && board[0][2] == ' ') {
            board[0][2] = Bot;
            return true;
        } else if (board[0][2] == Bot && board[2][0] == Bot && board[1][1] == ' ') {
            board[1][1] = Bot;
            return true;
        }
        
        return false;
    }

    /**
     * Memeriksa apakah pemain memiliki kesempatan untuk menang dalam satu langkah
     * dan menempatkan simbol bot untuk memblokir langkah tersebut.
     *
     * @param Bot karakter yang mewakili simbol bot (misalnya, 'X' atau 'O').
     * @param Player karakter yang mewakili simbol pemain (misalnya, 'X' atau 'O').
     * @return
     * <code>True</code> jika bot berhasil memblokir langkah pemain yang hampir menang 
     * <li><code>False</code> jika tidak ada langkah untuk diblokir.
     */
    public static Boolean blockOne (char Bot, char Player){
        // Cek setiap baris
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == Player && board[i][1] == Player && board[i][2] == ' ') {
                board[i][2] = Bot;
                return true;
            } else if (board[i][1] == Player && board[i][2] == Player && board[i][0] == ' ') {
                board[i][0] = Bot;
                return true;
            } else if (board[i][0] == Player && board[i][2] == Player && board[i][1] == ' ') {
                board[i][1] = Bot;
                return true;
            }
        }
        
        // Cek setiap kolom
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == Player && board[1][j] == Player && board[2][j] == ' ') {
                board[2][j] = Bot;
                return true;
            } else if (board[1][j] == Player && board[2][j] == Player && board[0][j] == ' ') {
                board[0][j] = Bot;
                return true;
            } else if (board[0][j] == Player && board[2][j] == Player && board[1][j] == ' ') {
                board[1][j] = Bot;
                return true;
            }
        }
        
        // Cek diagonal

        // Diagonal Kiri (row 0,0)
        if (board[0][0] == Player && board[1][1] == Player && board[2][2] == ' ') {
            board[2][2] = Bot;
            return true;
        } else if (board[1][1] == Player && board[2][2] == Player && board[0][0] == ' ') {
            board[0][0] = Bot;
            return true;
        } else if (board[0][0] == Player && board[2][2] == Player && board[1][1] == ' ') {
            board[1][1] = Bot;
            return true;
        }
        
        // Diagonal Kanan (row 0,2)
        if (board[0][2] == Player && board[1][1] == Player && board[2][0] == ' ') {
            board[2][0] = Bot;
            return true;
        } else if (board[1][1] == Player && board[2][0] == Player && board[0][2] == ' ') {
            board[0][2] = Bot;
            return true;
        } else if (board[0][2] == Player && board[2][0] == Player && board[1][1] == ' ') {
            board[1][1] = Bot;
            return true;
        }
        
        return false;
    }

    /**
     * Fungsi Minimax untuk menentukan langkah terbaik bagi Bot dalam permainan Tic-Tac-Toe.
     * <p>
     * Fungsi ini digunakan untuk mengevaluasi papan permainan dan memilih langkah terbaik
     * berdasarkan algoritma minimax. Bot akan berusaha memaksimalkan skor, sedangkan pemain
     * akan berusaha meminimalkan skor.
     * </p>
     *
     * <h3>Penjelasan:</h3>
     * <p>
     * 1. Fungsi ini akan mengevaluasi papan permainan berdasarkan apakah Bot menang, kalah, atau seri.
     * <br>2. Fungsi ini kemudian mencoba setiap kemungkinan langkah, dan menggunakan rekursi untuk menentukan langkah terbaik
     *    yang akan diambil oleh Bot atau pemain.
     * <br>3. Bot akan berusaha memilih langkah yang memaksimalkan skor, sedangkan pemain akan berusaha memilih langkah yang
     *    meminimalkan skor untuk Bot.
     * </p>
     *
     * @param isMaximizing <code>true</code> jika saat ini giliran Bot untuk memaksimalkan skor,
     *                     <code>false</code> jika giliran pemain untuk meminimalkan skor.
     * @param Bot Simbol yang digunakan oleh Bot, biasanya 'X' atau 'O'.
     * @return <strong>Skor terbaik</strong> yang dapat dicapai oleh Bot pada papan permainan saat ini.
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
                    if (board[i][j] == ' ') {
                        board[i][j] = Bot; // Coba langkah Bot
                        bestScore = Math.max(bestScore, minimax(false, Bot)); // Rekursi untuk lawan
                        board[i][j] = ' '; // Kembalikan papan
                    }
                }
            }
            return bestScore;
        } else { // Pemain mencoba meminimalkan
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = Player; // Coba langkah lawan
                        bestScore = Math.min(bestScore, minimax(true, Bot)); // Rekursi untuk Bot
                        board[i][j] = ' '; // Kembalikan papan
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
     * <code>10</code>, Jika bot menang. 
     * <li><code>-10</code>, Jika bot kalah.
     * <li><code>0</code>, Jika hasil permainan seri.
     */
    public static int evaluate(char Bot) {
        char Player = (Bot == 'X') ? 'O' : 'X';
    
        // Periksa baris
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                if (board[i][0] == Bot) return 10;
                if (board[i][0] == Player) return -10;
            }
        }
    
        // Periksa kolom
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                if (board[0][j] == Bot) return 10;
                if (board[0][j] == Player) return -10;
            }
        }
    
        // Periksa diagonal
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == Bot) return 10;
            if (board[0][0] == Player) return -10;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == Bot) return 10;
            if (board[0][2] == Player) return -10;
        }
    
        return 0; // Tidak ada pemenang
    }

    /**
     * Mengatur giliran pemain dalam mode Player vs Player (PvP) pada permainan Tic-Tac-Toe.
     * Fungsi ini menentukan simbol pemain berdasarkan giliran (X atau O)
     * dan memperbarui giliran dengan menambah nilai penghitung (counter).
     */
    public static void settingPvP() {
        inputPlayer((counter % 2 != 0) ? 'X' : 'O');
        ++counter;
    }

    /**
     * Mengatur giliran pemain dan bot dalam mode Player vs Bot (PvB) pada permainan Tic-Tac-Toe.
     * Fungsi ini menentukan giliran berdasarkan status permainan dan tingkat kesulitan bot.
     *
     * @param state nilai integer yang menunjukkan status permainan:
     *              <ul>
     *               <li><strong>State 1</strong>: Pemain = 'X', Bot = 'O'</li>
     *               <li><strong>State 2</strong>: Pemain = 'O', Bot = 'X'</li>
     *              </ul>
     * @param difficulty tingkat kesulitan bot.
     *                   Tingkat kesulitan ini akan diteruskan ke fungsi logika bot.
     *              <ul>
     *               <li><strong>difficulty 1:</strong>: Easy </li>
     *               <li><strong>difficulty 2:</strong>: Medium</li>
     *               <li><strong>difficulty 3:</strong>: Imposibble</li>
     *              </ul>
     */
    public static void settingPvB(int state, int difficulty) {

        // State 1: Pemain = 'X', Bot = 'O'
        if (state == 1) {
            if (counter % 2 != 0) {
                inputPlayer('X'); // Giliran pemain
            } else {
                inputBot('O', 'X', difficulty); // Giliran bot
            }
        }
        // State 2: Pemain = 'O', Bot = 'X'
        else if (state == 2) {
            if (counter % 2 != 0) {
                inputBot('X', 'O', difficulty); // Giliran bot
            } else {
                inputPlayer('O'); // Giliran pemain
            }
        }

        ++counter; // Memperbarui giliran
    }

    /**
     * Mengecek apakah permainan sudah berakhir (menang, kalah, atau seri).
     * 
     * @return 
     * <code>true</code> jika permainan masih berlanjut. 
     * <li><code>false</code> jika tidak.
     */
    public static boolean ifEnd(int mode, int state){
        if (checkVertical()|| checkHorizontal() || checkDiagonal()){
            printBoard();
            printGameOver("Win",mode,state);
            System.out.print("Mengakhiri permainan");
            loading(800);
            return true;
        }
        else if (isDraw()){
            printBoard();
            printGameOver("Draw",mode,state);
            System.out.print("Mengakhiri permainan");
            loading(800);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Memeriksa apakah ada pemenang yang terbentuk di diagonal papan permainan.
     * <p>
     * Fungsi ini memeriksa dua diagonal di papan Tic-Tac-Toe:
     * <ul>
     *     <li>Diagonal kiri atas ke kanan bawah (board[0][0], board[1][1], board[2][2])</li>
     *     <li>Diagonal kanan atas ke kiri bawah (board[0][2], board[1][1], board[2][0])</li>
     * </ul>
     * Jika ada tiga simbol yang sama pada salah satu diagonal, maka fungsi ini akan mengembalikan <code>true</code>,
     * yang menandakan bahwa pemain telah memenangkan permainan di diagonal tersebut.
     * </p>
     *
     * <h3>Detail Pengoperasian:</h3>
     * <p>
     * 1. Fungsi ini memeriksa apakah simbol pada diagonal kiri atas ke kanan bawah atau diagonal kanan atas ke kiri bawah
     *    memiliki simbol yang sama dan bukan spasi (' ').
     * <br>2. Jika salah satu diagonal memiliki tiga simbol yang sama, maka fungsi ini akan mengembalikan nilai <code>true</code>.
     * <br>3. Jika tidak ada diagonal yang membentuk kemenangan, fungsi ini akan mengembalikan nilai <code>false</code>.
     * </p>
     *
     * @return 
     * <code>True</code> jika ada pemenang pada salah satu diagonal, 
     * <li><Code>false</code> jika tidak ada.
     */
    public static boolean checkDiagonal(){
        boolean diagonal_kiri = ((board[0][0] == board[1][1] && board[1][1] == board[2][2]) && board[0][0] != ' ');
        boolean diagonal_kanan = ((board[0][2] == board[1][1] && board[1][1] == board[2][0]) && board[0][2] != ' ');
        return diagonal_kiri || diagonal_kanan;
    }

    /**
     * Memeriksa apakah ada pemenang yang terbentuk di baris papan permainan.
     * <p>
     * Fungsi ini memeriksa setiap baris pada papan Tic-Tac-Toe untuk menentukan apakah ada tiga simbol yang sama dalam
     * satu baris. Jika ada tiga simbol yang sama dan bukan spasi (' '), maka fungsi ini akan mengembalikan <code>true</code>,
     * yang menandakan bahwa pemain telah memenangkan permainan pada baris tersebut.
     * </p>
     *
     * <h3>Detail Pengoperasian:</h3>
     * <p>
     * 1. Fungsi ini iterasi melalui setiap baris pada papan permainan.
     * <br>2. Untuk setiap baris, fungsi memeriksa apakah ketiga elemen dalam baris tersebut memiliki simbol yang sama.
     * <br>3. Jika ketiga simbol pada suatu baris sama dan bukan spasi (' '), maka fungsi ini akan mengembalikan nilai <code>true</code>.
     * <br>4. Jika tidak ada baris yang memenuhi kriteria tersebut, fungsi ini akan mengembalikan nilai <code>false</code>.
     * </p>
     *
     * @return 
     * <code>True</code> jika ada pemenang pada salah satu baris, 
     * <li><code>False</code> jika tidak ada.
     */
    public static boolean checkHorizontal() {
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2] && board[row][0] != ' ') {
                return true;
            }
        }
        return false;
    }

    
    /**
     * Memeriksa apakah ada pemenang yang terbentuk di kolom papan permainan.
     * <p>
     * Fungsi ini memeriksa setiap kolom pada papan Tic-Tac-Toe untuk menentukan apakah ada tiga simbol yang sama dalam
     * satu kolom. Jika ada tiga simbol yang sama dan bukan spasi (' '), maka fungsi ini akan mengembalikan <code>true</code>,
     * yang menandakan bahwa pemain telah memenangkan permainan pada kolom tersebut.
     * </p>
     *
     * <h3>Detail Pengoperasian:</h3>
     * <p>
     * 1. Fungsi ini iterasi melalui setiap kolom pada papan permainan.
     * <br>2. Untuk setiap kolom, fungsi memeriksa apakah ketiga elemen dalam kolom tersebut memiliki simbol yang sama.
     * <br>3. Jika ketiga simbol dalam suatu kolom sama dan bukan spasi (' '), maka fungsi ini akan mengembalikan nilai <code>true</code>.
     * <br>4. Jika tidak ada kolom yang memenuhi kriteria tersebut, fungsi ini akan mengembalikan nilai <code>false</code>.
     * </p>
     *
     * @return 
     * <code>True</code> jika ada pemenang pada salah satu kolom, 
     * <li><code>False</code> jika tidak ada.
     */
    public static boolean checkVertical() {
        for (int columnLoop = 0; columnLoop < 3; columnLoop++) {
            if (board[0][columnLoop] == board[1][columnLoop] && board[1][columnLoop] == board[2][columnLoop] && board[0][columnLoop] != ' ') {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Memeriksa apakah permainan berakhir dengan hasil seri.
     * <p>
     * Fungsi ini memeriksa seluruh papan Tic-Tac-Toe untuk menentukan apakah semua posisi sudah terisi
     * dan tidak ada ruang kosong lagi (' '). Jika seluruh papan terisi dan tidak ada pemenang, maka permainan berakhir seri.
     * </p>
     *
     * <h3>Detail Pengoperasian:</h3>
     * <p>
     * 1. Fungsi ini akan memeriksa setiap posisi di papan Tic-Tac-Toe.
     * <br>2. Jika ada satu posisi yang masih kosong (bernilai <code>' '</code>), maka fungsi ini akan mengembalikan <code>false</code>,
     *    menandakan permainan belum berakhir seri.
     * <br>3. Jika semua posisi sudah terisi dan tidak ada ruang kosong, fungsi ini akan mengembalikan nilai <code>true</code>,
     *    menandakan permainan berakhir seri.
     * </p>
     *
     * @return 
     * <code>true</code> jika permainan berakhir seri (semua posisi terisi dan tidak ada pemenang),
     * <li><code>false</code> jika permainan masih berlangsung.
     */
    public static boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') return false; // Jika ada ruang kosong, belum seri
            }
        }
        return true;
    }

    /**
     * Memulai permainan Tic-Tac-Toe dengan memilih mode permainan dan pengaturan lainnya.
     * <p>
     * Fungsi ini mengatur permainan berdasarkan pilihan pemain untuk mode Player vs Player (PvP)
     * atau Player vs Bot (PvB). Pemain juga dapat memilih simbol dan tingkat kesulitan bot dalam mode PvB.
     * Setelah permainan selesai, pemain dapat memilih untuk bermain lagi.
     * </p>
     *
     * <h2>Alur Permainan:</h2>
     * <ol>
     *     <li><strong>Mode Permainan</strong> - Pemain dapat memilih antara:
     *         <ul>
     *             <li>1. Player vs Player</li>
     *             <li>2. Player vs Bot</li>
     *         </ul>
     *     </li>
     *     <li><strong>Pemilihan Pion (untuk mode PvB)</strong> - Pemain memilih simbol mereka:
     *         <ul>
     *             <li>1. X (Giliran pertama)</li>
     *             <li>2. O (Giliran kedua)</li>
     *         </ul>
     *     </li>
     *     <li><strong>Pemilihan Kesulitan (untuk mode PvB)</strong> - Pemain memilih tingkat kesulitan bot:
     *         <ul>
     *             <li>1. Easy</li>
     *             <li>2. Medium</li>
     *             <li>3. Impossible</li>
     *         </ul>
     *     </li>
     *     <li><strong>Pertanyaan Ulang (Setelah permainan selesai)</strong> - Pemain diminta memilih apakah ingin bermain lagi:
     *         <ul>
     *             <li>1. Ya</li>
     *             <li>2. Tidak</li>
     *         </ul>
     *     </li>
     * </ol>
     *
     * <p>Setelah permainan selesai, fungsi ini akan menanyakan apakah pemain ingin bermain lagi.</p>
     *
     * <h3>Detail Pengoperasian:</h3>
     * <p>
     * 1. Pemain memilih mode permainan dengan memilih angka 1 atau 2.
     * <br>2. Dalam mode PvB, pemain memilih pion dan tingkat kesulitan bot.
     * <br>3. Setelah permainan berakhir, pemain dapat memilih untuk bermain lagi atau tidak.
     * </p>
     *
     * @see #settingPvP()
     * @see #settingPvB(int, int)
     * @see #ifEnd(int, int)
     * @see #loading(int)
     */
    public static void start(){
        boolean repeat = true;
        System.out.println("===========================================================");
        System.out.println("          Selamat datang di Tic Tac Toe Ceria!");
        System.out.println("===========================================================");
        while (repeat){
            boolean end = false;
            int mode = 0;
            int state = 0;
            int difficulty = 0;
            System.out.println("\nSilakan pilih mode permainan:");
            System.out.println("1. Player vs Player");
            System.out.println("2. Player vs Bot");
            System.out.print("Mode yang Anda pilih (1/2): ");
            mode = scan.nextInt();
            while (mode < 1 || mode > 2){
                System.out.print("Opsi yang anda pilih tidak valid! Mohon pilih dari opsi yang ada (1/2): ");
                mode = scan.nextInt();
            }  
            
            while (true){
                if(mode == 1){
                    System.out.print("Memulai permainan");
                    loading(600);
                    while (!end){
                        printBoard();
                        settingPvP();
                        end = ifEnd(mode, 0);
                    }
                    break;
                }
                else if(mode == 2){     
                    System.out.println("\nSilakan pilih pion Anda:");
                    System.out.println("1. X (Giliran pertama)");
                    System.out.println("2. O (Giliran kedua)");
                    System.out.print("Ketikkan angka dari pion yang anda mau (1/2): ");
                    state = scan.nextInt();
                    while (state < 1 || state > 2){
                        System.out.print("Pion yang anda pilih tidak valid! Mohon pilih dari opsi yang ada (1/2): ");
                        state = scan.nextInt();
                    }

                    
                    System.out.println("\nPilih tingkat kesulitan Bot:");
                    System.out.println("1. Easy");
                    System.out.println("2. Medium");
                    System.out.println("3. Impossible");
                    System.out.print("Tingkat kesulitan yang Anda pilih (1/2/3): ");
                    difficulty = scan.nextInt();
                    while(difficulty < 1 || difficulty > 3){
                        System.out.print("Tingkat kesulitan tidak valid. Mohon masukkan opsi yang ada (1/2/3): ");
                        difficulty = scan.nextInt();
                    }
                    System.out.print("Memulai permainan");
                    loading(600);     
                    while (!end){
                        printBoard();
                        settingPvB(state, difficulty);
                        end = ifEnd(mode, state);
                    }
                    break;
                }
                System.out.println("Mode yang anda pilih tidak valid, mohon pilih opsi yang ada. (1/2):");
                mode = scan.nextInt();
            }

            System.out.println("\nMau bermain lagi?");
            System.out.println("1.Ya");
            System.out.println("2.Tidak");
            System.out.print("Silahkan pilih (1/2): ");
            int playAgain = scan.nextInt();
            while (true){
                if (playAgain == 1){
                    counter = 1;
                    clearBoard();
                    repeat = true;
                    break;
                }
                else if (playAgain == 2){
                    repeat = false;
                    System.out.println("\nTerima kasih sudah bermain TicTacToe!");
                    System.out.print("Mengakhiri program");
                    loading(800);
                    break;

                }
                else{
                    System.out.println("Opsi anda tidak valid! mohon pilih opsi yang ada(1/2)");
                    playAgain = scan.nextInt();
                }    
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
