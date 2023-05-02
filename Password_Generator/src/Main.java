import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static int choice;
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        
        showMainMenu();   //shows main screen text
        takeUserChoice();   //takes the user choice

        switch(choice){
            case 0:
                System.exit(0);
                break;
            case 1:
                new PassBuilder();
                break;
            case 2:
                new StrengthChecker();
                break;
        }
    }

    private static void showMainMenu() {

        System.out.println("\033[H\033[2J");    //clears the screen
        System.out.println("Welcome to Password Generator and Strength Checker");
        System.out.println("1.Generate a new Password");
        System.out.println("2.Check Strength of a Password");
        System.out.println("0.Exit");
        System.out.println("What do you want to do?\nPlease enter your choice");
    }

    public static void takeUserChoice(){
        
        try {
            System.out.print("Your Choice: ");
            choice = scan.nextInt();
            try {
                if(choice>3 || choice<0) throw new CustomException("Please give a number between 0 and 2");
            } catch (CustomException e) {
                System.err.println(e.getMessage());
                takeUserChoice();
            }
        } catch (InputMismatchException e) {
            System.err.println("Please give a number as input shown in the above menu");
            scan.nextLine();
            takeUserChoice();
        }
    }
}
