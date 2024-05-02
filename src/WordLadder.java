import java.util.*;

class WordLadder {
    public static void main(String[] args) {
        new Dictionary(); // initialize dictionary

        Scanner inputScanner = new Scanner(System.in);
        Boolean isLoop = true;
        String startWord = "", endWord = "";
        while (isLoop) {
            System.out.print("Masukkan kata awal: ");
            startWord = inputScanner.nextLine().split(" ")[0].toUpperCase();
            int wordLength = startWord.length();

            System.out.print("Masukkan kata akhir: ");
            endWord = inputScanner.nextLine().split(" ")[0].toUpperCase();

            if (endWord.length() != wordLength) {
                System.out.println("Panjang kata harus sama!");
            }
            else if (!Dictionary.isInDictionary(startWord) || !Dictionary.isInDictionary(endWord)) { 
                System.out.println("Kata tidak ada di dalam English Dictionary");
            } 
            else {
                isLoop = false;
            }
        }

        System.out.println("Kata awal: " + startWord);
        System.out.println("Kata akhir: " + endWord);

        isLoop = true;
        String pilihanAlgoritma = "";
        while (isLoop) {
            System.out.println("Pilihan algoritma: \n" +
                "1. UCS \n" +
                "2. Greedy Best First Search \n" +
                "3. A* ");

            System.out.print("Masukkan pilihan algoritma: "); 
            pilihanAlgoritma = inputScanner.nextLine();

            if (pilihanAlgoritma.equals("UCS")) {
                pilihanAlgoritma = "1";
            } 
            else if (pilihanAlgoritma.equals("Greedy Best First Search")) {
                pilihanAlgoritma = "2";
            } 
            else if (pilihanAlgoritma.equals("A*")) {
                pilihanAlgoritma = "3";
            } 
            
            if (pilihanAlgoritma.equals("1") || pilihanAlgoritma.equals("2") || pilihanAlgoritma.equals("3")) 
            {
                isLoop = false;
            }
            else {
                System.out.println("Input invalid!");
            }
        }

        System.out.println("Pilihan algoritma: " + pilihanAlgoritma);

        long startTime = System.currentTimeMillis();
        if (pilihanAlgoritma.equals("1")){
            Algorithm.UCS(startWord, endWord);
        } else if (pilihanAlgoritma.equals("2")) {
            Algorithm.GBFS(startWord, endWord);
        } else {
            Algorithm.AStar(startWord, endWord);
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Time: " + (endTime-startTime) + "ms");

        inputScanner.close();
    }
}
