public class Util {
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

class Pair<X,Y extends Number> implements Comparable<Pair<X,Y>>  {
    private X key;
    private Y value;

    public Pair(X key, Y value) {
        this.key = key;
        this.value = value;
    }

    public X getKey() {
        return this.key;
    }

    public Y getValue() {
        return this.value;
    }

    public int compareTo(Pair<X,Y> p) {
        if (this.getValue().doubleValue() < p.getValue().doubleValue()) {
            return -1;
        }
        else if (this.getValue().doubleValue() > p.getValue().doubleValue()) {
            return 1;
        }
        else return 0;
    }
}