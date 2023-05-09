public class Display {
    
    String passwordA, passwordB, passwordC;

    Display(String A, String B, String C){

        String password1 = A;
        String password2 = B;
        String password3 = C;

        System.out.println("Generated Password 1: " + password1);
        System.out.println("Generated Password 2: " + password2);
        System.out.println("Generated Password 3: " + password3);
    }

    public void save(){}
}
