import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Converter {
    
    private String saveFilePath;
    private String loadFilePath;

    public void setSaveFilePath(String saveFilePath) {
        this.saveFilePath = saveFilePath;
    }

    public void setLoadFilePath(String loadFilePath) {
        this.loadFilePath = loadFilePath;
    }

    public String[][] convertFileToWord() {
        ArrayList<String> text = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader(loadFilePath))) {
            String line;
            while((line = reader.readLine()) != null){
                String words[] = line.trim().split("\\s+");
                for(String str: words){
                    text.add(str);
                }
            }
        } catch (IOException e) {
            System.err.println("Error!");
            return null;
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

        return words;
    }

    public boolean saveWordToFile(String words[][]){
        File file = new File(saveFilePath);
        try {
            if(file.createNewFile());
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(saveFilePath))) {
                for(String[] row: words){
                    if(row!=null){
                        for(String word: row){
                            if(word!=null){
                                writer.write(word+" ");
                            }
                        }
                        writer.write("\n");
                    }
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
