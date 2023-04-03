package geology;

class Point {

    //private fields to store the x and y value this Point has on the Landscape
    private final int x;
    private int y;

    /**
     * Constructor to make a Landscape Point
     * @param x horizontal value of the Point
     * @param y vertical value of the Point
     */
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() { return this.x; }
    int getY() { return this.y; }

    //increment the height by an input amount
    void increaseY(int amount) {
        this.y += amount;
    }
    //decrement the height by an input amount
    void decreaseY(int amount) {
        this.y -= amount;
    }
}
