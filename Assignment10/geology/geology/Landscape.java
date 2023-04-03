package geology;

import java.util.ArrayList;
import java.util.List;

public class Landscape {

    //private field to store the range of this Landscape (x-axis)
    private final int range;
    //private field to store the height of this Landscape (y-axis)
    private final int height;
    //private field to store the points in this Landscape
    private final List<Point> points = new ArrayList<>();

    /**
     * Constructor to build the Landscape
     * range starts from 0 and extends to the input range given
     * @param range value of the x-axis length
     * @param height value of the y-axis length
     */
    public Landscape(int range, int height) {
        this.range = range;
        this.height = height;
        for(int i = 0; i <= range; i++) {
            points.add(new Point(i, 0));
        }
    }

    //getter method for range field
    int getRange() { return this.range; }

    //getter method for height field
    int getHeight() { return this.height; }

    /**
     * Method to modify this Landscape
     * @param x1 left-most point range value at which to start the modification
     * @param x2 right-most point range at which to start the modification
     * @param operation Enumerated type specifying the modification to complete
     */
    public void modify(int x1, int x2, Modification operation) {
        //check preconditions
        checkPreconditions(x1,x2);
        switch (operation) {
            case RAISE:
                raise(x1,x2);
            case DEPRESS:
                depress(x1,x2);
            case HILL:
                hill(x1, x2);
            default:
                valley(x1, x2);
        }
    }

    //helper method to check modify() preconditions
    private void checkPreconditions(int x1, int x2) {
        if(x1 > x2)
            throw new IllegalArgumentException("x1 must be less than or equal to x2");
    }

    //helper method for the RAISE operation
    private void raise(int x1, int x2) {
        for(int i = x1; i <= x2; i++) {
            Point point = points.get(i);
            point.setY(point.getY()+1);
        }
    }

    //helper method for the DEPRESS operation
    private void depress(int x1, int x2) {
        for(int i = x1; i <= x2; i++) {
            Point point = points.get(i);
            point.setY(point.getY()-1);
        }
    }

    //helper method for the HILL operation
    private void hill(int x1, int x2) {

    }

    //helper method for the VALLEY operation
    private void valley(int x1, int x2) {

    }
}
