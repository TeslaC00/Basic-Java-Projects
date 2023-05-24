import java.security.SecureRandom;
import java.util.ArrayList;

public class PasswordBuilder {

    private final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private final String NUMBERS = "0123456789";
    private final String SYMBOLS = "!\"#$%&'()*+,-./:;< = >?@[\\]^_`{|}~";

    private boolean useUpperCase, useLowerCase, useNumbers, useSymbols;
    private int passwordLength;
    StringBuilder passwordA, passwordB, passwordC;
    private ArrayList<Character> list;

    public PasswordBuilder() {

        init(); // initialize all variables
    }

    private void init() {

        useUpperCase = useLowerCase = useNumbers = useSymbols = true;
        list = new ArrayList<>();
        passwordLength = 16;
    }

    public void setPassParameters() {

        System.out.println("Please tell us your password requirements(Yes/No)");
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
            if (!Helper.isValidInteger(passwordLength, 8))
                System.err.println("Password length must be greater than 8");
        } while (!Helper.isValidInteger(passwordLength, 8));
    }

    public void generate() {

        SecureRandom random = new SecureRandom();
        passwordA = new StringBuilder(passwordLength);
        passwordB = new StringBuilder(passwordLength);
        passwordC = new StringBuilder(passwordLength);

        if (useUpperCase) {
            for (int i = 0; i < UPPERCASE.length(); i++) {
                list.add(UPPERCASE.charAt(i));
            }
        }
        if (useLowerCase) {
            for (int i = 0; i < LOWERCASE.length(); i++) {
                list.add(LOWERCASE.charAt(i));
            }
        }
        if (useNumbers) {
            for (int i = 0; i < NUMBERS.length(); i++) {
                list.add(NUMBERS.charAt(i));
            }
        }
        if (useSymbols) {
            for (int i = 0; i < SYMBOLS.length(); i++) {
                list.add(SYMBOLS.charAt(i));
            }
        }
        for (int i = 0; i < passwordLength; i++) {
            passwordA.append(list.get(random.nextInt(list.size())));
            passwordB.append(list.get(random.nextInt(list.size())));
            passwordC.append(list.get(random.nextInt(list.size())));
        }
        new Display(passwordA.toString(), passwordB.toString(), passwordC.toString());
    }
}
