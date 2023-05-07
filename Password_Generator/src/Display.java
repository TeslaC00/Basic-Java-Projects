public class Display {
    
    String passwordA, passwordB, passwordC;

    public Display(String passwordA, String passwordB, String passwordC) {

        this.passwordA = passwordA;
        this.passwordB = passwordB;
        this.passwordC = passwordC;
        save();
        showPassword();
    }

    private void showPassword() {

        System.out.println(passwordA);
        System.out.println(passwordB);
        System.out.println(passwordC);
    }

    private void save() {
        
    }
    
}
