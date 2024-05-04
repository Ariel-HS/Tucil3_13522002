import java.util.*;
import java.io.*;

class Dictionary {
    private static HashMap<String, Boolean> wordList;

    static {
        wordList = new HashMap<String, Boolean>();

        try {
            File wordFile = new File("./test/wordList.txt");
            Scanner readScanner = new Scanner(wordFile);
            System.out.println("Importing dictionary...");
            while (readScanner.hasNextLine()) {
                String word = readScanner.nextLine().toUpperCase();
                wordList.put(word,true);
                // System.out.println(word);
            }
            System.out.println("Finished importing");

            readScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan! Tolong tempatkan wordList pada /test");

            System.exit(0);
        }
        
        // System.out.println(wordList);
    }

    public static Boolean isInDictionary(String word) {
        if (wordList.get(word) == null) {
            return false;
        }

        return true;
    }

    public static ArrayList<String> getNeighbour(String word) {
        ArrayList<String> neighbours = new ArrayList<String>();
        for (String el : wordList.keySet()) {
            if (charDifference(word, el) == 1) {
                neighbours.add(el);
            }
        }

        return neighbours;
    }

    static Integer charDifference(String word1, String word2) {
        if (word1.length() != word2.length()) { // if different length
            return -1;
        }

        int ctr = 0;
        for (int i=0; i<word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                ctr++;
            }
        }

        return ctr;
    }
}