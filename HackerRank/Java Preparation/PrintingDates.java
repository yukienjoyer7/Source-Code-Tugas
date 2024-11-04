import java.util.*;
public class PrintingDates {

    public static String getMonth(int month){
        switch (month){
            case 1:return "January";
            case 2:return "February";
            case 3:return "March";
            case 4:return "April";
            case 5:return "May";
            case 6:return "June";
            case 7:return "July";
            case 8:return "August";
            case 9:return "September";
            case 10:return "October";
            case 11:return "November";
            case 12:return "December";
            default:return "";
        }
    }

    public static void PrintDates (int month, int year){
        int amount = getDays(month, year);
        int start = findStart(month,year);
        if (start == 0) start = 7;
        for (int i = 1; i < 42; i++){
            if (i >= start && i <= start + amount - 1){
                System.out.printf("%4d",i - (start - 1)," ");
            }
            else{
                System.out.printf("%4s", "");
            }
            if (i % 7 == 0){
                System.out.println("");
            }
        }
    }

    public static int findStart(int month, int year){
        int year1 = 2;
        return (year1 + TotalDays(month, year))% 7;
    }

    public static int TotalDays (int month, int year){
        int total = 0;
        for (int i = 1; i < year; i++){
            if (isLeapYear(i)){
                total += 366;
            }
            else{
                total += 365;
            }
        }
        for (int i = 1; i < month; i++){
            total += getDays (i, year);
        }
        return total;
    }

    public static int getDays(int month, int year){
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
            return 31;
        }
        else if (month == 2){
            return isLeapYear(year) ? 29 : 28;
        }
        else{
            return 30;
        }
    }


    public static boolean isLeapYear(int year){
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner (System.in);

        System.out.print("Enter full year (e.g 2012):");
        int year = scan.nextInt();

        System.out.print("Enter month (e.g 7):");
        int month = scan.nextInt();

        if(month > 12 || month < 1){
            System.out.println("INVALID MONTH");
            System.exit(0);
        }

        System.out.printf("%16s %s\n", getMonth(month), year);
        System.out.println("-----------------------------");
        System.out.println(" Sun Mon Tue Wed Thu Fri Sat");
        PrintDates(month,year);
    }
}
