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
            case RAISE -> raise(x1, x2);
            case DEPRESS -> depress(x1, x2);
            case HILL -> hill(x1, x2);
            default -> valley(x1, x2);
        }

    }

    /**
     * Verification algorithm to determine if the landscape matches the application of a sequence of modifications
     * @param pointsNum number of points in the Landscape
     * @param modifications sequence of modifications (Enum type, int x1, int x2)
     * @param heights list of final heights at each ith point in this Landscape
     * @return boolean value indicating if the modifications produce the height list
     */
    public static boolean verifyModifications(int pointsNum, List<ModificationRecord> modifications, List<Integer> heights) {
        checkVerifyModificationPreconditions(pointsNum, modifications,heights);

        Landscape landscape = new Landscape(pointsNum-1);

        for(ModificationRecord modification: modifications) {
            landscape.modify(modification.x1(), modification.x2(), modification.operation());
        }

        for(int i = 0; i <= landscape.getRange(); i++) {
            if(landscape.getPoints().get(i).getY() != heights.get(i))
                return false;
        }

        return true;
    }

    //helper method to check verifyModification preconditions()
    private static void checkVerifyModificationPreconditions(int pointsNum, List<ModificationRecord> modifications, List<Integer> heights) {
        verifyNonNull(modifications, heights);
        verifyElementsNonNull(modifications);
        verifyElementsNonNull(heights);
        if(pointsNum != heights.size())
            throw new IllegalArgumentException("The number of points must be the same as the list of final heights");
    }

    //helper method to verify if arguments are non-null
    private static void verifyNonNull(Object...args) {
        for(Object arg: args) {
            if(arg == null)
                throw new IllegalArgumentException("Arguments must be non-null");
        }
    }

    //helper method to verify if elements in a List are non-null
    private static <E> void verifyElementsNonNull(List<E> list) {
        for(E element: list) {
            if(element == null)
                throw new IllegalArgumentException("Elements in the arguments must be non-null");
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
        if(x1 < 0)
            throw new IllegalArgumentException("x1 and x2 must be greater than 0");
        if(x2 > getRange())
            throw new IllegalArgumentException("x1 and x2 must be within range");
    }

    //helper method for the RAISE operation
    private void raise(int x1, int x2) {
        for(int i = x1; i <= x2; i++) {
            getPoints().get(i).increaseY(1);
        }
    }

    //helper method for the DEPRESS operation
    private void depress(int x1, int x2) {

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
        heightIncrease--;
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
        heightDecrease--;
        if(isOdd) {
            getPoints().get(x2+1).decreaseY(heightDecrease);
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
