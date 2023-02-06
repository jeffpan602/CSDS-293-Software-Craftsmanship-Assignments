package polygon;

public record IndexPair(Integer xIndex, Integer yIndex) implements Comparable<IndexPair>{
    /**
     * Compares two IndexPair objects based on values of x coordinates and y coordinates
     * Given p1(x1, y1) and p2(x2, y2), if x1 > x2 then p1 > p2; if x1 < x2 then p1 < p2
     * If x1 = x2, then use y coordinates to compare using same logic
     * @param o the object to be compared.
     * @return positive int if this > argument, negative int if this < argument, 0 if equal
     */
    @Override
    public int compareTo(IndexPair o) {
        int diff = this.xIndex - o.xIndex();
        if(diff != 0) {
            return diff;
        }
        else {
            return this.yIndex() - o.yIndex();
        }
    }

    /**
     * Returns the Index Pair after it is incremented in direction given by the input Direction
     * @param direction
     * @return incremented Index Pair
     */
    public IndexPair increment(Direction direction) {
        //have a null check for the argument
        switch(direction) {
            case LEFT:
                return new IndexPair(this.xIndex()-1, this.yIndex());
            case RIGHT:
                return new IndexPair(this.xIndex()+1, this.yIndex());
            case BOTTOM:
                return new IndexPair(this.xIndex(), this.yIndex()-1);
            default:
                return new IndexPair(this.xIndex(), this.yIndex()+1);
        }

    }
}
       
        




