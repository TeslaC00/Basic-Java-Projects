import java.util.Scanner;

public class Helper {

    private static Scanner scan = new Scanner(System.in);

    public static int promptInput(){

        while(true){
            String input = scan.next();
            int value = 0;
            try {
                value = Integer.parseInt(input);
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number");
            }
        }
    }

    public static boolean isValidInteger(int value, int low){
        return value>=low;
    }

    public static boolean isValidInteger(int value, int low, int high){
        if(value<=high && value>=low) return true;
        return false;
    }
}
