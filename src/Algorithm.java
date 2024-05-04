import java.util.*;

class Algorithm {
    public static Pair<ArrayList<String>, Integer> UCS(String startWord, String endWord) throws NoSolutionException {
        PriorityQueue<Pair<ArrayList<String>,Integer>> pQueue = new PriorityQueue<>(Pair::compareTo);
        HashMap<String, Boolean> checked = new HashMap<>();
        Boolean found = false;

        ArrayList<String> startPath = new ArrayList<>();
        startPath.add(startWord);

        ArrayList<String> finalPath = new ArrayList<>();
        pQueue.add(new Pair<ArrayList<String>,Integer>(startPath, 0));
        while (!found && !pQueue.isEmpty()) {
            Pair<ArrayList<String>,Integer> currentNode = pQueue.poll();
            String currentWord = currentNode.getKey().get(currentNode.getKey().size()-1);
            Integer currentCost = currentNode.getValue();
            checked.put(currentWord, true);
            
            if (currentWord.equals(endWord)) {
                found = true;
                finalPath = currentNode.getKey();
                break;
            }

            ArrayList<String> neighbours = Dictionary.getNeighbour(currentWord);
            for (String el : neighbours) {
                if (checked.get(el) == null) {
                    ArrayList<String> newPath = new ArrayList<>(currentNode.getKey());
                    newPath.add(el);

                    pQueue.add(new Pair<ArrayList<String>,Integer>(newPath, currentCost+1));
                }
            }
        }

        if (!found) {
            throw new NoSolutionException("Tidak ada solusi yang ditemukan", checked.size());
        }

        return new Pair<ArrayList<String>, Integer>(finalPath, checked.size());
    }

    public static Pair<ArrayList<String>, Integer> GBFS(String startWord, String endWord) throws NoSolutionException {
        PriorityQueue<Pair<ArrayList<String>,Integer>> pQueue = new PriorityQueue<>(Pair::compareTo);
        Boolean found = false;
        Integer numChecked = 0;

        ArrayList<String> startPath = new ArrayList<>();
        startPath.add(startWord);

        ArrayList<String> finalPath = new ArrayList<>();
        pQueue.add(new Pair<ArrayList<String>,Integer>(startPath, Dictionary.charDifference(startWord, endWord)));
        while (!found && !pQueue.isEmpty()) {
            Pair<ArrayList<String>,Integer> currentNode = pQueue.poll();
            String currentWord = currentNode.getKey().get(currentNode.getKey().size()-1);
            numChecked++;
            
            if (currentWord.equals(endWord)) {
                found = true;
                finalPath = currentNode.getKey();
                break;
            }

            ArrayList<String> neighbours = Dictionary.getNeighbour(currentWord);
            for (String el : neighbours) {
                ArrayList<String> newPath = new ArrayList<>(currentNode.getKey());
                newPath.add(el);

                pQueue.add(new Pair<ArrayList<String>,Integer>(newPath, Dictionary.charDifference(el, endWord)));
            }
        }

        if (!found) {
            throw new NoSolutionException("Tidak ada solusi yang ditemukan", numChecked);
        }

        return new Pair<ArrayList<String>, Integer>(finalPath, numChecked);
    }

    public static Pair<ArrayList<String>, Integer> AStar(String startWord, String endWord) throws NoSolutionException {
        PriorityQueue<Pair<ArrayList<String>,Integer>> pQueue = new PriorityQueue<>(Pair::compareTo);
        HashMap<String, Boolean> checked = new HashMap<>();
        Boolean found = false;

        ArrayList<String> startPath = new ArrayList<>();
        startPath.add(startWord);

        ArrayList<String> finalPath = new ArrayList<>();
        pQueue.add(new Pair<ArrayList<String>,Integer>(startPath, 0+Dictionary.charDifference(startWord, endWord)));
        while (!found && !pQueue.isEmpty()) {
            Pair<ArrayList<String>,Integer> currentNode = pQueue.poll();
            String currentWord = currentNode.getKey().get(currentNode.getKey().size()-1);
            Integer currentCost = currentNode.getValue();
            checked.put(currentWord, true);
            
            if (currentWord.equals(endWord)) {
                found = true;
                finalPath = currentNode.getKey();
                break;
            }

            ArrayList<String> neighbours = Dictionary.getNeighbour(currentWord);
            for (String el : neighbours) {
                if (checked.get(el) == null) {
                    ArrayList<String> newPath = new ArrayList<>(currentNode.getKey());
                    newPath.add(el);

                    pQueue.add(new Pair<ArrayList<String>,Integer>(newPath,currentCost+1+Dictionary.charDifference(el, endWord)));
                }
            }
        }

        if (!found) {
            throw new NoSolutionException("Tidak ada solusi yang ditemukan", checked.size());
        }

        return new Pair<ArrayList<String>, Integer>(finalPath, checked.size());
    }
}

class NoSolutionException extends Exception {
    private Integer numChecked;

    public NoSolutionException(String s, Integer c) {
        super(s);
    }

    public Integer getChecked() {
        return numChecked;
    }
}