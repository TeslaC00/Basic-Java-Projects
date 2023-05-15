public class PasswordStrengthChecker {

    private PasswordBuilder passwordBuilder;
    private String password;
    private int score=0;

    public PasswordStrengthChecker(PasswordBuilder passwordBuilder) {
        this.passwordBuilder = passwordBuilder;
        menu();
    }

    private void menu(){
        System.out.println("\033[H\033[2J"); 
        System.out.println("Choose a password to chek strength of: ");
        System.out.println("1."+passwordBuilder.passwordA);
        System.out.println("2."+passwordBuilder.passwordB);
        System.out.println("3."+passwordBuilder.passwordC);
        System.out.println("4. Go back");
        System.out.println("5. Exit");

        int choice = Helper.promptInput();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int calculatePasswordStrength() {

        if (password.length() >= 8 && password.length() >= 16) {
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

        int numberCount = 0;
        int symbolCount = 0;

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

    public String getPasswordStrength(int score) {
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

    public int checkPasswordStrength() {
        int score = 0;

        // Check if password starts with uppercase letter
        if (password.matches("^[A-Z].*$")) {
            score += 2;
        }

        // Check if password ends with lowercase letter
        if (password.matches("^.*[a-z]$")) {
            score += 2;
        }

        // Check if password has at least three numbers
        if (password.matches("^(.\\d.){3,}$")) {
            score += 2;
        }

        // Check if password uses at least three symbols
        if (password.matches("^(.[!@#$%^&()_+\\-={}\\[\\]|;:\"<>,./?].*){3,}$")) {
            score += 2;
        }

        // Check if password is 8 characters or longer
        if (password.length() >= 8) {
            score += 2;
        }

        // Check if password is 16 characters or longer
        if (password.length() >= 16) {
            score += 2;
        }

        return score;
    }
}