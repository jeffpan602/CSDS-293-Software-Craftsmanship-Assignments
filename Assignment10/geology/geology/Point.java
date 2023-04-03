package geology;

class Point {

    //private fields to store the x and y value this Point has on the Landscape
    private int x;
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
    void setX(int x) { this.x = x; }
    void setY(int y) { this.y = y; }
}
