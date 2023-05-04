import java.util.Scanner;

public class Helper {

    public static int promptInput(){

        while(true){
            Scanner scan = new Scanner(System.in);
            String input = scan.next();
            int value = 0;
            try {
                value = Integer.parseInt(input);
                scan.close();
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number");
            }
        }
    }
}
