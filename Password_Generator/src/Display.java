public class Display {

    String passwordA, passwordB, passwordC;

    Display(String A, String B, String C) {

        passwordA = A;
        passwordB = B;
        passwordC = C;

        save();
        show();
    }

    public void show() {
        System.out.println("Generated Password 1: " + passwordA);
        System.out.println("Generated Password 2: " + passwordB);
        System.out.println("Generated Password 3: " + passwordC);
    }

    public void save() {
    }

}

class Saver {

    public static void userSave(String password) {
        System.out.println("saved " + password);
    }
}
