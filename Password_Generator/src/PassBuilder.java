public class PassBuilder {
    
    private char[] keys = new char[90];

    public PassBuilder(){

        init();
        System.out.println("Welcome to Password Builder");
    }

    private void init() {
        for(int i=0; i<keys.length; i++){
            keys[i] = (char)(i+32);
        }
    }

    public void generate(){}
}
