public class PasswordStrengthChecker {

    private String passA, passB, passC;
    PasswordBuilder passwordBuilder;
    private int choice;

    public PasswordStrengthChecker(PasswordBuilder passwordBuilder) {
        this.passwordBuilder = passwordBuilder;
        init();
        strengthCheckMenu();
    }

    private void init() {
        try {
            passA = passwordBuilder.passwordA.toString();
            passB = passwordBuilder.passwordB.toString();
            passC = passwordBuilder.passwordC.toString();
        } catch (NullPointerException e) {
            System.err.println("Please generate password before checking strength");
        }

    }

    private void strengthCheckMenu() {
        System.out.println("\033[H\033[2J");
        System.out.println("Password Strength Checker Menu");
        System.out.println("0. Exit");
        System.out.println("1. Show passwords and their strength");
        System.out.println("2. Save a password");
        System.out.println("3. Regenrate password");
        System.out.println("4. Go back");

        while (true) {
            choice = Helper.promptInput();
            switch (choice) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("Password: " + passA + "\tStrength: " + getPasswordStrength(passA));
                    System.out.println("Password: " + passB + "\tStrength: " + getPasswordStrength(passB));
                    System.out.println("Password: " + passC + "\tStrength: " + getPasswordStrength(passC));
                    break;
                case 2:
                    save();
                    break;
                case 3:
                    passwordBuilder.generate();
                    init();
                    break;
                case 4:
                    new Menu();
                    break;
                default:
                    System.err.println("Please give a valid input");
            }
            System.out.println("Your choice :");
        }
    }

    public void save() {
        System.out.println("Which password do you want to save: ");
        int choice = -1;
        do {
            choice = Helper.promptInput();
            switch (choice) {
                case 1:
                    Display.save(passA);
                    break;
                case 2:
                    Display.save(passB);
                    break;
                case 3:
                    Display.save(passC);
                    break;
                default:
                    System.out.println("Choose valid input");
            }

        } while (!Helper.isValidInteger(choice, 1, 3));
    }

    public int calculatePasswordStrength(String password) {

        int score = 0, symbolCount = 0, numberCount = 0;

        if (password.length() >= 16) {
            score += 2;
        } else if (password.length() >= 8) {
            score += 1;
        }

        if (Character.isUpperCase(password.charAt(0))) {
            score += 1;
        }

        if (Character.isLowerCase(password.charAt(password.length() - 1))) {
            score += 1;
        }

        for (char ch : password.toCharArray()) {
            if (Character.isDigit(ch)) {
                numberCount++;
            } else if (!Character.isLetterOrDigit(ch)) {
                symbolCount++;
            }
        }

        if (numberCount >= 3) {
            score += 2;
        } else if (numberCount >= 1) {
            score += 1;
        }

        if (symbolCount >= 3) {
            score += 2;
        } else if (symbolCount >= 1) {
            score += 1;
        }

        return score;
    }

    public String getPasswordStrength(String password) {

        int score = calculatePasswordStrength(password);

        if (score >= 7) {
            return "Great";
        } else if (score >= 5) {
            return "Good";
        } else if (score >= 3) {
            return "Medium";
        } else {
            return "Weak";
        }
    }
}