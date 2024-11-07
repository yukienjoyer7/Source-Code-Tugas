import java.util.*;

public class HiraganaEz {
    static final String[] hiragana = { 
    "あ - A", "い - I", "う - U", "え - E", "お - O",  
    "か - KA", "き - KI", "く - KU", "け - KE", "こ - KO",  
    "さ - SA", "し - SHI", "す - SU", "せ - SE", "そ - SO",  
    "た - TA", "ち - CHI", "つ - TSU", "て - TE", "と - TO",  
    "な - NA", "に - NI", "ぬ - NU", "ね - NE", "の - NO",  
    "は - HA", "ひ - HI", "ふ - FU", "へ - HE", "ほ - HO",  
    "ま - MA", "み - MI", "む - MU", "め - ME", "も - MO",  
    "や - YA", "ゆ - YU", "よ - YO",  
    "ら - RA", "り - RI", "る - RU", "れ - RE", "ろ - RO",  
    "わ - WA", "を - WO", "ん - N"  
    };

    static final String[] romaji = {  
    "a", "i", "u", "e", "o",       
    "ka", "ki", "ku", "ke", "ko",  
    "sa", "shi", "su", "se", "so", 
    "ta", "chi", "tsu", "te", "to",
    "na", "ni", "nu", "ne", "no",  
    "ha", "hi", "fu", "he", "ho",  
    "ma", "mi", "mu", "me", "mo",  
    "ya", "yu", "yo",              
    "ra", "ri", "ru", "re", "ro",  
    "wa", "wo", "n" 
    };

    static int CorrectAmount = 0;
    static int WrongAmount = 0;
    static Scanner scanner = new Scanner(System.in);


    public static String getRandomHiragana(){
        int randomIndex = (int) (Math.random() * hiragana.length);
        return hiragana[randomIndex];
    }

    public static boolean checkAnswer(String input, int index){
        return input.equalsIgnoreCase(romaji[index]);
    }

    public static void updateScore(boolean isCorrect){
        if (isCorrect) {
            ++CorrectAmount;
        } else{ 
            ++WrongAmount;
        }
    }

    public static char getGrade(float accuracy) {
        char grade;
        if (accuracy >= 80.0) {
            grade = 'A';
        } else if (accuracy >= 70.0) {
            grade = 'B';
        } else if (accuracy >= 60.0) {
            grade = 'C';
        } else if (accuracy >= 50.0) {
            grade = 'D';
        } else {
            grade = 'F';
        }
        return grade;
    }
    

    public static void PrintFinalGrade (){
        System.out.println("\nSkor Akhir Anda Adalah:\nJumlah Benar:" + CorrectAmount + "\nJumlah Salah:" + WrongAmount);
        float accuracy = (CorrectAmount * 100.0f / (CorrectAmount + WrongAmount));
        char grade = getGrade(accuracy);
        System.out.printf("Nilai Akhir anda adalah:%.2f%% - %s", accuracy, grade);
    }

    public static void start (){
        System.out.print("Berapa banyak soal yang anda inginkan?: ");
        int AmmountOfIteration  = scanner.nextInt();

        for (int i = 0; i < AmmountOfIteration; i++){
            String currentHiragana = getRandomHiragana();
            System.out.println("Apa romanji dari: " + currentHiragana + "?");
            String answer = scanner.next();
            int index = Arrays.asList(hiragana).indexOf(currentHiragana);

            if (checkAnswer(answer, index)){
                System.out.println("Benar!");   
                updateScore(true);
            } else{
                System.out.println("Salah. Jawaban yang benar adalah: " + romaji[index]);
                updateScore(false);
            }
        }
        scanner.close();
        PrintFinalGrade();
    }



    public static void main(String[] args){
        System.out.println("Selamat datang di HiraganaEz! Tebak romaji dari karakter Hiragana yang muncul. Jawaban benar akan menambah skor, dan jawaban salah akan menampilkan koreksinya. Selamat belajar dan semoga berhasil!");
        start();

    }
}