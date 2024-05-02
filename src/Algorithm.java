import java.util.*;

class Algorithm {
    public static void UCS(String startWord, String endWord) {
        PriorityQueue<Pair<String,Integer>> pQueue = new PriorityQueue<Pair<String,Integer>>(Pair::compareTo);
        HashMap<String, ArrayList<String>> parentList = new HashMap<>();
        HashMap<String, Boolean> checked = new HashMap<>();
        Boolean found = false;

        pQueue.add(new Pair<String,Integer>(startWord, 0));
        while (!found && !pQueue.isEmpty()) {
            Pair<String,Integer> currentNode = pQueue.poll();
            String currentWord = currentNode.getKey();
            Integer currentCost = currentNode.getValue();
            
            if (currentWord.equals(endWord)) {
                found = true;
                // System.out.println("Found!");
                break;
            }

            checked.put(currentWord, true);

            ArrayList<String> neighbours = Dictionary.getNeighbour(currentWord);
            for (String el : neighbours) {
                if (checked.get(el) == null) {
                    pQueue.add(new Pair<String,Integer>(el,currentCost+1));

                    if (parentList.get(el) == null) {
                        parentList.put(el, new ArrayList<>());
                    }
    
                    parentList.get(el).add(currentWord);
                }
            }

            // System.out.println("Queue");
            // PriorityQueue<Pair<String,Integer>> tempQueue = new PriorityQueue<Pair<String,Integer>>(pQueue);;
            // while (!tempQueue.isEmpty()) {
            //     Pair<String,Integer> tempNode = tempQueue.poll();
            //     System.out.println(tempNode.getKey() + " : " + tempNode.getValue());
            // }
        }

        ArrayList<String> path = findPath(startWord, endWord, parentList);

        System.out.print("Path: ");

        for (int i=0;i<path.size();i++) {
            System.out.print(path.get(i));
            if (i != path.size()-1) {
                System.out.print(" -> ");
            }
        }
        System.out.print("\n");
    }

    private static ArrayList<String> findPath(String startWord, String endWord, HashMap<String, ArrayList<String>> parentList) {
        ArrayList<String> path = new ArrayList<>();
        Boolean found = false;

        String currentWord = endWord;
        while (!found) {
            path.add(0,currentWord);

            if (currentWord.equals(startWord)) {
                found = true;
            } else {
                currentWord = parentList.get(currentWord).get(0);
            }
        }

        return path;
    }
}