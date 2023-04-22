import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println("File Format: D:/vikas/");
        System.out.println("Give file name in data folder: ");
        String filePath = "D:/Vikas/VS Code/Basic Java Projects/Text_Converter/data/"+scan.nextLine();
        scan.close();

        ArrayList<String> text = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = reader.readLine()) != null){
                String words[] = line.trim().split("\\s+");
                for(String str: words){
                    text.add(str);
                }
            }
        } catch (IOException e) {
            System.err.println("Error!");
            return ;
        }

        String words[][] = new String[26][];
        int indexes[] = new int[26];
        
        for(String i: text){
            if(i.isBlank()) continue;
            int index = i.toLowerCase().charAt(0)-'a';
            if(words[index] == null){
                words[index] = new String[1];
            }
            else if(indexes[index] >= words[index].length){
                String temp[] = new String[words[index].length+1];
                System.arraycopy(words[index], 0, temp, 0, words[index].length);
                words[index] = temp;
            }
            words[index][indexes[index]++] = i;
        }

        for(int i: indexes){
            System.out.print(i+" ");
        }
        System.out.println();

        for(String[] row: words){
            if(row != null){
                for(String word: row){
                    if(word != null){
                        System.out.print(word+" ");
                    }
                }
                System.out.println();
            }
        }
    }
}
