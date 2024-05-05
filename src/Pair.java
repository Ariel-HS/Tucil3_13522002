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

    // Override method for Comparable
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