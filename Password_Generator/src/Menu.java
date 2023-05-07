public class Menu {

    private int choice;
    private PasswordBuilder passwordBuilder;

    public Menu(){

        passwordBuilder = new PasswordBuilder();
        showMainMenu();   //shows main screen text
    }
    
    private void showMainMenu() {
        
        System.out.println("\033[H\033[2J");    //clears the screen
        System.out.println("Welcome to Password Generator and Strength Checker");
        System.out.println("1.Generate a new Password");
        System.out.println("2.Check Strength of a Password");
        System.out.println("0.Exit");
        System.out.println("What do you want to do?\nPlease enter your choice");
        takeUserChoice();   //takes the user choice
    }

    private void takeUserChoice(){
        
        do {
            choice = Helper.promptInput();
            switch(choice){
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    passwordBuilderMenu();
                    break;
                case 2:
                    new StrengthChecker();
                    break;
                default: 
                    System.err.println("Give valid number as input");
            }
        } while(!Helper.isValidInteger(choice, 0, 2));
    }

    private void passwordBuilderMenu(){

        System.out.println("\033[H\033[2J");    //clears the screen
        System.out.println("Welcome to Password Builder\n");
        System.out.println("0.Go back\n1.Set Password Parameters\n2.Generate Password");
        System.out.print("Please give your choice: ");
        
        do {
            choice = Helper.promptInput();
            switch(choice){
                case 0:
                    showMainMenu();
                    break;
                case 1: 
                    passwordBuilder.setPassParameters();
                    break;
                case 2: 
                    passwordBuilder.generate();
                    break;
                default:
                    System.err.println("Please give a valid number");
            }
            choice = -1;
            System.out.print("Please give your choice: ");
        } while(!Helper.isValidInteger(choice, 0, 2));
    }
}
