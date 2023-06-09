import java.util.Scanner;

public class Helper {

    private static Scanner scan = new Scanner(System.in);

    public static int promptInput() {

        while (true) {
            String input = scan.next();
            int value = 0;
            try {
                value = Integer.parseInt(input);
                return value;
            } catch (NumberFormatException e) {
                System.err.println("Please enter a number");
            }
        }
    }

    public static boolean isValidInteger(int value, int low) {
        return value >= low;
    }

    public static boolean isValidInteger(int value, int low, int high) {
        if (value <= high && value >= low)
            return true;
        return false;
    }

    public static boolean setBoolean() {

        do {
            switch (scan.next()) {
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
}
