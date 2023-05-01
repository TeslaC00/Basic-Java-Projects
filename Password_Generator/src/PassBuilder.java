public class PassBuilder {
    
    private char[] keys = new char[90];

    public PassBuilder(){

        for(int i=0; i<keys.length; i++){
            keys[i] = (char)(i+32);
        }
    }
}
