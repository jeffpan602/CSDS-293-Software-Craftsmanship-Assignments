package geology;

import java.util.ArrayList;
import java.util.List;

public class Landscape {

    //private field to store the range of this Landscape (x-axis)
    private final int range;
    //private field to store the points in this Landscape
    private final List<Point> points = new ArrayList<>();

    /**
     * Constructor to build the Landscape
     * range starts from 0 and extends to the input range given
     * Each point is given a height of 0 by default
     * @param range value of the x-axis length
     */
    public Landscape(int range) {
        verifyArguments(range);
        this.range = range;
        for(int i = 0; i <= range; i++) {
            points.add(new Point(i, 0));
        }
    }

    //getter method for range field
    int getRange() { return this.range; }

    List<Point> getPoints() { return this.points; }

    /**
     * Method to modify this Landscape
     * @param x1 left-most point range value at which to start the modification
     * @param x2 right-most point range at which to start the modification
     * @param operation Enumerated type specifying the modification to complete
     */
    public void modify(int x1, int x2, Modification operation) {
        //check preconditions
        checkModifyPreconditions(x1,x2);
        switch (operation) {
            case RAISE:
                raise(x1,x2);
            case DEPRESS:
                depress(x1,x2);
            case HILL:
                hill(x1, x2);
            case VALLEY:
                valley(x1, x2);
        }
    }

    //helper method to check constructor parameters
    private void verifyArguments(int range) {
        if(range <= 0)
            throw new IllegalArgumentException("Landscape range must be greater than 0");
    }

    //helper method to check modify() preconditions
    private void checkModifyPreconditions(int x1, int x2) {
        if(x1 > x2)
            throw new IllegalArgumentException("x1 must be less than or equal to x2");
    }

    //helper method to check depress() preconditions
    private void checkDepressPreconditions() {
        for(Point point: getPoints()) {
            if(point.getY() < 0)
                throw new IllegalArgumentException("Landscape points at height 0, can't depress or valley further");
        }
    }

    //helper method for the RAISE operation
    private void raise(int x1, int x2) {
        for(int i = x1; i <= x2; i++) {
            getPoints().get(x1).increaseY(1);
        }
    }

    //helper method for the DEPRESS operation
    private void depress(int x1, int x2) {
        checkDepressPreconditions();

        for(int i = x1; i <= x2; i++) {
            Point point = points.get(i);
            point.decreaseY(1);
        }
    }

    //helper method for the HILL operation
    private void hill(int x1, int x2) {
        int midpoint = (x1 + x2) / 2;
        if((x2 - x1) % 2 == 0) {
            raiseUpwardsSlope(x1, midpoint, false);
            raiseDownwardsSlope(midpoint+1, x2);
        }
        else {
            raiseUpwardsSlope(x1, midpoint, true);
            raiseDownwardsSlope(midpoint + 2, x2);
        }
    }

    //helper method for the VALLEY operation
    private void valley(int x1, int x2) {
        checkValleyPreconditions(x1, x2);

        int midpoint = (x1 + x2) / 2;
        if((x2 - x1) % 2 == 0) {
            depressDownwardsSlope(x1, midpoint, false);
            depressUpwardsSlope(midpoint+1, x2);
        }
        else {
            depressDownwardsSlope(x1, midpoint, true);
            depressUpwardsSlope(midpoint + 2, x2);
        }
    }

    //helper method for the HILL operation to make the upwards slope of the hill
    private void raiseUpwardsSlope(int x1, int x2, boolean isOdd) {
        int heightIncrease = 1;
        for(int i = x1; i <= x2; i++) {
            getPoints().get(i).increaseY(heightIncrease);
            heightIncrease++;
        }
        if(isOdd) {
            getPoints().get(x2+1).increaseY(heightIncrease);
        }
    }

    //helper method for the HILL operation to make the downwards slope of the hill
    private void raiseDownwardsSlope(int x1, int x2) {
        int heightIncrease = 1;
        for(int i = x2; i >= x1; i--) {
            getPoints().get(i).increaseY(heightIncrease);
            heightIncrease++;
        }
    }

    //helper method for the VALLEY operation to make the downwards slope of the valley
    private void depressDownwardsSlope(int x1, int x2, boolean isOdd) {
        int heightDecrease = 1;
        for(int i = x1; i <= x2; i++) {
            getPoints().get(i).decreaseY(heightDecrease);
            heightDecrease++;
        }
        if(isOdd) {
            getPoints().get(x2+1).increaseY(heightDecrease);
        }
    }

    //helper method for the VALLEY operation to make the upwards slope of the valley
    private void depressUpwardsSlope(int x1, int x2) {
        int heightDecrease = 1;
        for(int i = x2; i >= x1; i--) {
            getPoints().get(i).decreaseY(heightDecrease);
            heightDecrease++;
        }
    }

    //helper method to check valley preconditions()
    private void checkValleyPreconditions(int x1, int x2) {
        int maxHeightDecrease = (x2 - x1) / 2;
        if(getPoints().get(maxHeightDecrease).getY() - maxHeightDecrease < 0)
            throw new IllegalArgumentException("Landscape is not high enough to create a valley");
    }
}
