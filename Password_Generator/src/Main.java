import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static int choice = -1;
    public static void main(String[] args) throws Exception {
        
        mainScreen();   //shows main screen text
        takeChoice();   //takes the user choice

        switch(choice){
            case 0:
                System.exit(0);
                break;
            case 1:
                method1();
                break;
            case 2:
                method2();
                break;
            default:
                System.out.println("\t\t\tPlease give a number as input shown in the above menu");
                takeChoice();
        }
    }

    private static void method2() {
        System.out.println("Case 2");
    }

    private static void method1() {
        System.out.println("Case 1");
    }

    private static void mainScreen() {
        System.out.println("\033[H\033[2J");    //clears the screen
        System.out.println("\t\t\tWelcome to Password Generator and Strength Checker");
        System.out.println("\n\n\t\t\t1.Generate a new Password");
        System.out.println("\n\t\t\t2.Check Strength of a Password");
        System.out.println("\n\t\t\t0.Exit");
        System.out.println("\n\t\t\tWhat do you want to do?\n\n\t\t\tPlease enter your choice");
    }

    public static void takeChoice(){
        Scanner scan = new Scanner(System.in);
        try {
            System.out.print("\t\t\tYour Choice: ");
            choice = scan.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("\t\t\tPlease give a number as input shown in the above menu");
            takeChoice();
        } finally{
            scan.close();
        }
    }
}
