import java.util.*;

class WordLadder {
    private final static Scanner inputScanner;

    static {
        inputScanner = new Scanner(System.in);
    }
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        Boolean isLoop = true;
        String in = "cli";

        while (isLoop) {
            System.out.print("CLI/GUI? ");
            in = inputScanner.nextLine().split(" ")[0].toLowerCase();
            
            if (!in.equals("cli") && !in.equals("gui")) {
                System.out.println("Invalid input! Masukkan input CLI/GUI");
            }
            else {
                isLoop = false;
            }
        }

        if (in.equals("cli")) {
            wordLadderCLI();
        } else {
            wordLadderGUI();
        }
        

        inputScanner.close();
    }

    public static void wordLadderCLI() {
        new Dictionary(); // initialize dictionary

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

        Pair<ArrayList<String>, Integer> solusi = new Pair<>(null, null);
        Boolean found = true;
        Integer numChecked = 0;

        long startTime = System.currentTimeMillis();
        System.out.println("Calculating...");
        try {
            if (pilihanAlgoritma.equals("1")){
                solusi = Algorithm.UCS(startWord, endWord);
            } else if (pilihanAlgoritma.equals("2")) {
                solusi = Algorithm.GBFS(startWord, endWord);
            } else {
                solusi = Algorithm.AStar(startWord, endWord);
            }
        }
        catch (NoSolutionException e) {
            System.out.println("Finished calculating");
            System.out.println(e.getMessage());
            if (e.getChecked() != null) {
                numChecked = e.getChecked();
            }
            found = false;
        }
        long endTime = System.currentTimeMillis();

        if (found) {
            System.out.println("Finished calculating");
            ArrayList<String> path = solusi.getKey();
    
            System.out.print("Path: ");
    
            for (int i=0;i<path.size();i++) {
                System.out.print(path.get(i));
                if (i != path.size()-1) {
                    System.out.print(" -> ");
                }
            }
            System.out.print(" (" + (path.size()-1) + " steps)\n");

            numChecked = solusi.getValue();
        }

        System.out.println("Jumlah Node yang dikunjungi: " + numChecked);
        System.out.println("Time: " + (endTime-startTime) + "ms");
    }

    public static void wordLadderGUI() {
        new WordLadderGUI();
    }
}