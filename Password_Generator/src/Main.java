public class Main {

    private int choice;

    public Main(){

        showMainMenu();   //shows main screen text
        takeUserChoice();   //takes the user choice
    }

    private void showMainMenu() {

        System.out.println("\033[H\033[2J");    //clears the screen
        System.out.println("Welcome to Password Generator and Strength Checker");
        System.out.println("1.Generate a new Password");
        System.out.println("2.Check Strength of a Password");
        System.out.println("0.Exit");
        System.out.println("What do you want to do?\nPlease enter your choice");
    }

    private void takeUserChoice(){
        
        do {
            choice = Helper.promptInput();
            switch(choice){
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    new PassBuilder();
                    break;
                case 2:
                    new StrengthChecker();
                    break;
                default: 
                    System.err.println("Give valid number as input");
            }
        } while(!Helper.isValidInteger(choice, 0, 2));
    }
}
