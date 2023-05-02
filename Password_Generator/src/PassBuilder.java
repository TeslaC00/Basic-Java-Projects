import java.util.InputMismatchException;
import java.util.Scanner;

public class PassBuilder {
    
    private char[] charUpper = new char[26];
    private char[] charLower = new char[26];
    private char[] charNumbers = new char[10];
    private char[] charSymbols = new char[32];

    private boolean upperCase, lowerCase, numbers, symbols;
    private int passLength;

    private final int DEFAULT_PASS_LENGTH = 8;

    private Scanner scan = new Scanner(System.in);

    public PassBuilder(){

        passBuilderMenu();
        init();
    }

    private void init() {   //initialize all variables

        upperCase = lowerCase = numbers = symbols = false;
        passLength = DEFAULT_PASS_LENGTH;

        for(int i=0; i<charUpper.length; i++){
            charUpper[i] = (char)(i+65);
            charLower[i] = (char)(i+97);
        }
        for(int i=0; i<charNumbers.length; i++){
            charNumbers[i] = (char)(i+48);
        }
        for(int i=0; i<32; i++){
            if(i<15) charSymbols[i] = (char)(i+33);
            else if(i>=15 && i<22) charSymbols[i] = (char)(i+43);
            else if(i>=22 && i<28) charSymbols[i] = (char)(i+69);
            else charSymbols[i] = (char)(i+95);
        }
    }

    private void passBuilderMenu(){

        System.out.println("\033[H\033[2J");    //clears the screen
        System.out.println("\t\t\tWelcome to Password Builder");
        System.out.println("\n\t\t\tPlease tell us your password requirements");
        System.out.print("\n\t\t\tDo you want Uppercase?: ");
        upperCase = setBoolean();
        System.out.print("\n\t\t\tDo you want Lowercase?: ");
        lowerCase = setBoolean();
        System.out.print("\n\t\t\tDo you want Numbers?: ");
        numbers = setBoolean();
        System.out.print("\n\t\t\tDo you want Symbols?: ");
        symbols = setBoolean();
        System.out.println("\n\t\t\tGive the length of the password");
        System.out.print("\t\t\t(Minimum length of password is 8)\n\t\t\t");
        setPassLength();
    }

    private boolean setBoolean(){

        Boolean b = false;

        switch(scan.next()){
            case "Yes":
            case "yes":
            case "YES":
                b = true; break;
            case "No":
            case "NO":
            case "no":
                b = false; break;
            default:
                System.err.print("\t\t\tPlease give input either \"Yes\" OR \"No\": ");
                setBoolean();
        }
        return b;
    }

    public void setPassLength() {

        try {
            passLength = scan.nextInt();
            try {
                if(passLength<8) throw new CustomException("Please give a number 8 or above");
            } catch (Exception e) {
                System.err.println(e.getMessage());
                setPassLength();
            }
        } catch (InputMismatchException e) {
            System.err.println("\t\t\tPlease give a number as input");
            setPassLength();
        }
    }
}
