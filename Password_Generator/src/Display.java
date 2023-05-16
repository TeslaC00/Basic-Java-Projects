import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Display {

    private String passwordA, passwordB, passwordC;

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
        File allPassword = new File("All_Password.txt");
        Boolean newFile = false;
        try {
            newFile = allPassword.createNewFile();
        } catch (IOException e) {
            System.err.println("Error in creating new file");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(allPassword, true))) {
            if (newFile)
                writer.append("All Generated Passwords: \n");
            writer.append(passwordA + "\n");
            writer.append(passwordB + "\n");
            writer.append(passwordC + "\n");
        } catch (IOException e1) {
            System.err.println("File writer error");
        }
    }

    public static void save(String password) {
        File userPassword = new File("User_Password.txt");
        Boolean newFile = false;
        try {
            newFile = userPassword.createNewFile();
        } catch (IOException e) {
            System.err.println("Error in creating new file");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userPassword, true))) {
            if (newFile)
                writer.append("User Passwords: \n");
            writer.append(password + "\n");
        } catch (IOException e1) {
            System.err.println("File writer error");
        }
    }

}