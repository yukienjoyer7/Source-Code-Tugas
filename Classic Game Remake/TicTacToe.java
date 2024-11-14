import java.util.*;
public class TicTacToe {
    
    static char [][] input = {
    {' ', ' ', ' '},
    {' ', ' ', ' '},
    {' ', ' ', ' '},}; //Start with blank space

    static int counter = 1;

    
    public static void PrintBoard() {
        System.out.println("Current Board:");
        System.out.println("          Col Col Col");
        System.out.println("           1   2   3"); // Label kolom
        System.out.println("");
    
        for (int i = 0; i < 3; i++) {
            System.out.print("Row " + (i+1) + "    "); // Label baris
            for (int j = 0; j < 3; j++) {
                System.out.print("| " + input[i][j] + " ");
            }
            System.out.println("|");
            System.out.println("         -------------"); // Garis pemisah antar baris
        }
    }
    

    public static void takeInput(Scanner scan) {
        int row, column;
        char player = (counter % 2 != 0) ? 'X' : 'O';
        
        while (true) {
            System.out.printf("Enter a row (1,2, or 3) for player %c: ", player);
            row = scan.nextInt() - 1;
            System.out.printf("Enter a column (1,2, or 3) for player %c: ", player);
            column = scan.nextInt() - 1;
            
            // Cek apakah input berada di luar batas papan
            if (row < 0 || row > 2 || column < 0 || column > 2) {
                System.out.println("Invalid move: Row and column must be between 1 and 3. Try again.");
            }
            // Cek apakah posisi sudah ditempati
            else if (input[row][column] != ' ') {
                System.out.println("Invalid move: The cell is already occupied. Try again.");
            }
            // Input valid
            else {
                input[row][column] = player;
                break;
            }
        }
        ++counter;
    }
    
    

    public static boolean checkIfEnd(){
        boolean vertical = checkVertical();
        boolean horizontal = checkHorizontal();
        boolean diagonal_kiri = checkDiagonal(0);
        boolean diagonal_kanan = checkDiagonal(2);
        System.out.println("vertical = " + vertical);
        System.out.println("horizontal = " + horizontal);
        System.out.println("diagonal_kiri = " + diagonal_kiri);
        System.out.println("diagonal_kanan = " + diagonal_kanan);
        
        if (vertical || horizontal || diagonal_kiri || diagonal_kanan){
            PrintBoard();
            System.out.println("Congratulations! The winner is " + ((counter % 2 == 0) ? 'X':'O'));
            return true;
        }
        else if (counter>9){
            PrintBoard();
            System.out.println("The game is a draw!");
            return true;
        }
        else{
            return false;
        }

    }


    public static boolean checkDiagonal (int column){
        boolean result = false;
        switch(column){
            //Jika Yang di check di pojok kiri atas, traverse ke kanan bawah
            case 0:
            for (int row = 0, columnLoop = 0; row < 2; row++,columnLoop++){
                result = false;
                if (input[row][columnLoop] != input[row+1][columnLoop+1] || input[row][columnLoop] == ' '){
                    break;
                }
                result = true;
            }
            
            //Jika Yang di check di pojok kanan atas, traverse ke kiri bawah
            case 2:
            for (int row = 0, columnLoop = 2; row > 0; row++,columnLoop--){
                result = false;
                if (input[row][columnLoop] != input[row+1][columnLoop-1] || input[row][columnLoop] == ' '){
                    break;
                }
                result = true;
            }

        }
        return result;
    }

    public static boolean checkHorizontal() {
        for (int row = 0; row < 3; row++) {
            if (input[row][0] == input[row][1] && input[row][1] == input[row][2] && input[row][0] != ' ') {
                return true; // Ada kemenangan secara horizontal
            }
        }
        return false; // Tidak ada kemenangan horizontal
    }
    
    public static boolean checkVertical() {
        for (int columnLoop = 0; columnLoop < 3; columnLoop++) {
            if (input[0][columnLoop] == input[1][columnLoop] && input[1][columnLoop] == input[2][columnLoop] && input[0][columnLoop] != ' ') {
                return true; // Ada kemenangan secara vertikal
            }
        }
        return false; // Tidak ada kemenangan vertikal
    }


    public static void main(String[] args) {
        boolean end = false;
        Scanner scan = new Scanner(System.in);

        while (end == false){
            PrintBoard();
            takeInput(scan);
            end = checkIfEnd();
        }
    }
}
