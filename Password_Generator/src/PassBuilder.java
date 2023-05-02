import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class PassBuilder {
    
    private char[] charUpper = new char[26];
    private char[] charLower = new char[26];
    private char[] charNumbers = new char[10];
    private char[] charSymbols = new char[32];

    private boolean useUpperCase, useLowerCase, useNumbers, useSymbols;
    private int passLength;

    private Scanner scan = new Scanner(System.in);

    public PassBuilder(){

        init();
        passBuilderMenu();
    }

    private void init() {   //initialize all variables

        useUpperCase = useLowerCase = useNumbers = useSymbols = true;
        passLength = 8;

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
        System.out.println("Welcome to Password Builder");
        setPassParameters();
        generate();
    }

    private void setPassParameters(){

        System.out.println("Please tell us your password requirements");
        System.out.print("Do you want Uppercase?: ");
        useUpperCase = setBoolean();
        System.out.print("Do you want Lowercase?: ");
        useLowerCase = setBoolean();
        System.out.print("Do you want Numbers?: ");
        useNumbers = setBoolean();
        System.out.print("Do you want Symbols?: ");
        useSymbols = setBoolean();
        System.out.println("Give the length of the password");
        System.out.print("(Minimum length of password is 8)\n");
        setPassLength();
    }

    private boolean setBoolean(){

        do {
            switch(scan.next()){
                case "Yes":
                case "yes":
                case "YES":
                    return true;
                case "No":
                case "NO":
                case "no":
                    return false;
                default:
                    System.err.print("Please give input either \"Yes\" OR \"No\": ");
            }
        } while (true);
    }

    public void setPassLength() {

        try {
            passLength = scan.nextInt();
            try {
                if(passLength<8) throw new CustomException("Please give a number 8 or above");
            } catch (CustomException e) {
                System.err.println(e.getMessage());
                setPassLength();
            }
        } catch (InputMismatchException e) {
            System.err.print("Please give a number as input: ");
            scan.nextLine();
            setPassLength();
        }
    }

    public void generate(){

        ArrayList<Character> list = new ArrayList<>();
        StringBuilder passA = new StringBuilder(passLength);
        StringBuilder passB = new StringBuilder(passLength);
        StringBuilder passC = new StringBuilder(passLength);
        if(useLowerCase){
            for(char c: charLower) list.add(c);
        }
        if(useUpperCase){
            for(char c: charUpper) list.add(c);
        }
        if(useNumbers){
            for(char c: charNumbers) list.add(c);
        }
        if(useSymbols){
            for(char c: charSymbols) list.add(c);
        }
        Random random = new Random();
        for(int i=0; i<passLength; i++){
            passA.append(list.get(random.nextInt(list.size())));
        }
        for(int i=0; i<passLength; i++){
            passB.append(list.get(random.nextInt(list.size())));
        }
        for(int i=0; i<passLength; i++){
            passC.append(list.get(random.nextInt(list.size())));
        }
        System.out.println(passA);
        System.out.println(passB);
        System.out.println(passC);
        System.out.println("0.Go Back\t\t1.Regenrate password\t\t2.Save current password");
        int i = scan.nextInt();
        if(i==0) passBuilderMenu();
        else if(i==1) generate();
    }
}
