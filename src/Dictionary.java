import java.util.*;
import java.io.*;

public class Dictionary {
    private static HashMap<String, Boolean> wordList;

    static {
        wordList = new HashMap<String, Boolean>();

        try {
            File wordFile = new File("wordList.txt");
            Scanner readScanner = new Scanner(wordFile);
            System.out.println("Importing dictionary...");
            while (readScanner.hasNextLine()) {
                String word = readScanner.nextLine().toUpperCase();
                wordList.put(word,true);
                // System.out.println(word);
            }
            System.out.println("Finished!");

            readScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan! Tolong tempatkan wordList pada /src");
        }
        
        // System.out.println(wordList);
    }

    public static Boolean isInDictionary(String word) {
        if (wordList.get(word) == null) {
            return false;
        }

        return true;
    }
}