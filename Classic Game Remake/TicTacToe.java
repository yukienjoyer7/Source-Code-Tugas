import java.util.*;
public class TicTacToe {

    static char [][] input = {
    {' ', ' ', ' '},
    {' ', ' ', ' '},
    {' ', ' ', ' '},}; // Mulai dengan ruang kosong

    static int counter = 1;

    private static Scanner scan = new Scanner(System.in);

    public static void PrintBoard() {
        System.out.println("Papan saat ini:");
        System.out.println("            Kol Kol Kol");
        System.out.println("             1   2   3"); // Label kolom
        System.out.println("");
    
        for (int i = 0; i < 3; i++) {
            System.out.print("Baris " + (i+1) + "    "); // Label baris
            for (int j = 0; j < 3; j++) {
                System.out.print("| " + input[i][j] + " ");
            }
            System.out.println("|");
            System.out.println("           -------------"); // Garis pemisah antar baris
        }
    }

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

    public static void inputBot(char Bot, char Player, int difficulty) {
        try {
            System.out.println("Bot sedang berpikir...");
            int RANDOM = (int)(Math.random() * 2 + 2) * 1000;
            Thread.sleep(RANDOM);
        } catch (InterruptedException e) {
            System.out.println("Terjadi kesalahan pada delay: " + e.getMessage());
        }

        switch (difficulty) {
            case 1:
                BotLogic_Easy(Bot);
                break;
            case 2:
                BotLogic_Medium(Bot, Player);
                break;
        }
    }   
    //Logika Easy: Pure Random
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

    //Logika Medium: Random sampai bisa blokir satu langkah sebelum menang
    public static void BotLogic_Medium(char Bot, char Player){
        if (BlokirSatu(Bot, Player)){
            return;
        }

        BotLogic_Easy(Bot);
    }

    //Idk yet.
    public static void BotLogic_Hard(char xD){

    }

    public static Boolean BlokirSatu (char Bot, char Player){
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


    // Bermain 2 orang
    public static void SettingPvP() {
        inputPlayer((counter % 2 != 0) ? 'X' : 'O');
        ++counter;
    }

    // Bermain 1 orang melawan bot
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
        else if (counter > 9){
            PrintBoard();
            System.out.println("Permainan berakhir seri!");
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean checkDiagonal(){
        boolean diagonal_kiri = ((input[0][0] == input[1][1] && input[1][1] == input[2][2]) && input[0][0] != ' ');
        boolean diagonal_kanan = ((input[0][2] == input[1][1] && input[1][1] == input[2][0]) && input[0][2] != ' ');
        return diagonal_kiri || diagonal_kanan;
    }

    public static boolean checkHorizontal() {
        for (int row = 0; row < 3; row++) {
            if (input[row][0] == input[row][1] && input[row][1] == input[row][2] && input[row][0] != ' ') {
                return true; 
            }
        }
        return false; 
    }
    
    public static boolean checkVertical() {
        for (int columnLoop = 0; columnLoop < 3; columnLoop++) {
            if (input[0][columnLoop] == input[1][columnLoop] && input[1][columnLoop] == input[2][columnLoop] && input[0][columnLoop] != ' ') {
                return true; 
            }
        }
        return false; 
    }
    
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
            System.out.println("Ketikkan angka dari pion yang anda mau (1/2): ");
            int side = scan.nextInt();
            int difficulty = 3;

            while(difficulty == 3){
                System.out.println("Pilih tingkat kesulitan Bot:");
                System.out.println("1. Easy");
                System.out.println("2. Medium");
                System.out.println("3. Hard (WIP)");
                System.out.print("Tingkat kesulitan yang Anda pilih (1/2/3): ");
                difficulty = scan.nextInt();
                if (difficulty == 3){
                    System.out.println("Mohon maaf, mode Hard masih dalam tahap pengembangan, mohon pilih yang lain");
                }
            }        
            while (!end){
                PrintBoard();
                SettingPvB(side, difficulty);
                end = checkIfEnd();
            }
        }
    }

    public static void main(String[] args) {
        start();
    }
}
