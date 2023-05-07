import java.util.ArrayList;
import java.util.Random;

public class PasswordBuilder {
    
    private char[] charUpper = new char[26];
    private char[] charLower = new char[26];
    private char[] charNumbers = new char[10];
    private char[] charSymbols = new char[32];

    private boolean useUpperCase, useLowerCase, useNumbers, useSymbols;
    private int passwordLength;
    private StringBuilder passwordA, passwordB, passwordC;
    private ArrayList<Character> list;

    public PasswordBuilder(){

        init();     //initialize all variables
    }

    private void init() {

        useUpperCase = useLowerCase = useNumbers = useSymbols = true;
        list = new ArrayList<>();
        passwordLength = 16;

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

    public void setPassParameters(){

        System.out.println("Please tell us your password requirements");
        System.out.print("Do you want Uppercase?: ");
        useUpperCase = Helper.setBoolean();
        System.out.print("Do you want Lowercase?: ");
        useLowerCase = Helper.setBoolean();
        System.out.print("Do you want Numbers?: ");
        useNumbers = Helper.setBoolean();
        System.out.print("Do you want Symbols?: ");
        useSymbols = Helper.setBoolean();
        System.out.println("Give the length of the password");
        System.out.print("(Minimum length of password is 8)\n");
        setpasswordLength();
    }

    public void setpasswordLength() {

        do {
            passwordLength = Helper.promptInput();
            if(!Helper.isValidInteger(passwordLength, 8)) System.err.println("Password length must be greater than 8");
        } while (!Helper.isValidInteger(passwordLength, 8));
    }

    public void generate(){
        
        Random random = new Random();
        passwordA = new StringBuilder(passwordLength);
        passwordB = new StringBuilder(passwordLength);
        passwordC = new StringBuilder(passwordLength);

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
        for(int i=0; i<passwordLength; i++){
            passwordA.append(list.get(random.nextInt(list.size())));
            passwordB.append(list.get(random.nextInt(list.size())));
            passwordC.append(list.get(random.nextInt(list.size())));
        }
        
        new Display(passwordA.toString(), passwordB.toString(), passwordC.toString());
    }
}
