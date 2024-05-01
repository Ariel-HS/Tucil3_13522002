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

            if (pilihanAlgoritma.equals("1")) {
                pilihanAlgoritma = "UCS";
            } 
            else if (pilihanAlgoritma.equals("2")) {
                pilihanAlgoritma = "Greedy Best First Search";
            } 
            else if (pilihanAlgoritma.equals("3")) {
                pilihanAlgoritma = "A*";
            } 
            
            if (pilihanAlgoritma.equals("UCS") || pilihanAlgoritma.equals("Greedy Best First Search") || pilihanAlgoritma.equals("A*")) 
            {
                isLoop = false;
            }
            else {
                System.out.println("Input invalid!");
            }
        }

        System.out.println("Pilihan algoritma: " + pilihanAlgoritma);

        inputScanner.close();
    }
}
