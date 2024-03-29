package geology;

import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class LandscapeTest {

    @Test
    public void testRange() {
        Landscape landscape = new Landscape(100);
        assertEquals(100, landscape.getRange());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testRangeExceptions() {
        Landscape landscape = new Landscape(-10);
        assertEquals(-10, landscape.getRange());
    }

    @Test
    public void testGetPoints() {
        Landscape landscape = new Landscape(10);
        int i = 0;
        for(Point point: landscape.getPoints()) {
            assertEquals(i, point.getX());
            assertEquals(0, point.getY());
            i++;
        }
    }

    @Test
    public void testModifyRAISE() {
        Landscape landscape = new Landscape(5);
        landscape.modify(1,5, Modification.RAISE);
        for(int i = 1; i < 5; i++) {
            assertEquals(1, landscape.getPoints().get(i).getY());
        }
    }

    @Test
    public void testModifyDEPRESS() {
        Landscape landscape = new Landscape(5);
        landscape.modify(1,5, Modification.RAISE);
        landscape.modify(1,5, Modification.RAISE);
        landscape.modify(1,5, Modification.RAISE);
        landscape.modify(1,5, Modification.DEPRESS);
        for(int i = 1; i < 5; i++) {
            assertEquals(2, landscape.getPoints().get(i).getY());
        }
    }

    @Test
    public void testModifyHILL() {
        Landscape landscape1 = new Landscape(7);
        landscape1.modify(1,6,Modification.HILL);
        int[] heights1 = {0, 1, 2, 3, 3, 2, 1, 0};
        for(int i = 0; i <= landscape1.getRange(); i++) {
            assertEquals(heights1[i], landscape1.getPoints().get(i).getY());
        }

        Landscape landscape2 = new Landscape(6);
        landscape2.modify(1,5,Modification.HILL);
        int[] heights2 = {0, 1, 2, 3, 2, 1, 0};
        for(int i = 0; i <= landscape2.getRange(); i++) {
            assertEquals(heights2[i], landscape2.getPoints().get(i).getY());
        }
    }

    @Test
    public void testModifyVALLEY() {
        Landscape landscape1 = new Landscape(7);
        for(int i = 0; i < 3; i++) {
            landscape1.modify(0, 7, Modification.RAISE);
        }
        landscape1.modify(1,6,Modification.VALLEY);
        int[] heights1 = {3, 2, 1, 0, 0, 1, 2, 3};
        for(int i = 0; i <= landscape1.getRange(); i++) {
            assertEquals(heights1[i], landscape1.getPoints().get(i).getY());
        }

        Landscape landscape2 = new Landscape(6);
        for(int i = 0; i < 3; i++) {
            landscape2.modify(0, 6, Modification.RAISE);
        }
        landscape2.modify(1,5,Modification.VALLEY);
        int[] heights2 = {3, 2, 1, 0, 1, 2, 3};
        for(int i = 0; i <= landscape2.getRange(); i++) {
            assertEquals(heights2[i], landscape2.getPoints().get(i).getY());
        }
    }

    @Test (expected = IllegalArgumentException.class)
    public void testModifyExceptionX2Negative() {
        Landscape landscape = new Landscape(5);
        landscape.modify(1,-5, Modification.RAISE);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testModifyExceptionX1Negative() {
        Landscape landscape = new Landscape(5);
        landscape.modify(-1,5, Modification.RAISE);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testModifyExceptionX1X2() {
        Landscape landscape = new Landscape(5);
        landscape.modify(-10,-5, Modification.RAISE);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testModifyExceptionOutOfRange() {
        Landscape landscape = new Landscape(5);
        landscape.modify(1, 100, Modification.RAISE);
    }

    @Test
    public void testVerifyModifications() {
        Landscape landscape1 = new Landscape(7);
        ModificationRecord m1 = new ModificationRecord(Modification.RAISE, 1, 7);
        ModificationRecord m2 = new ModificationRecord(Modification.DEPRESS, 1, 4);
        ModificationRecord m3 = new ModificationRecord(Modification.DEPRESS, 5, 7);
        List<ModificationRecord> modifications = new ArrayList<>(Arrays.asList(m1, m2, m3));
        List<Integer> heights = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0));
        assertTrue(Landscape.verifyModifications(8, modifications, heights));

        Landscape landscape2 = new Landscape(10);
        ModificationRecord m4 = new ModificationRecord(Modification.RAISE, 1, 7);
        ModificationRecord m5 = new ModificationRecord(Modification.HILL, 3, 5);
        List<ModificationRecord> modifications2 = new ArrayList<>(Arrays.asList(m4, m5));
        List<Integer> heights2 = new ArrayList<>(Arrays.asList(0, 1, 1, 2, 3, 2, 1, 1, 0, 0, 0));
        assertTrue(Landscape.verifyModifications(11, modifications2, heights2));
    }

    @Test
    public void testVerifyModificationsFalse() {
        Landscape landscape1 = new Landscape(7);
        ModificationRecord m1 = new ModificationRecord(Modification.RAISE, 1, 7);
        ModificationRecord m2 = new ModificationRecord(Modification.DEPRESS, 1, 4);
        ModificationRecord m3 = new ModificationRecord(Modification.DEPRESS, 5, 7);
        List<ModificationRecord> modifications = new ArrayList<>(Arrays.asList(m1, m2, m3));
        List<Integer> heights = new ArrayList<>(Arrays.asList(1, 0, 0, 0, 0, 0, 0, 0));
        assertFalse(Landscape.verifyModifications(8, modifications, heights));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testVerifyModificationsDiffNumPointsAndHeights() {
        ModificationRecord m1 = new ModificationRecord(Modification.RAISE, 1, 7);
        ModificationRecord m2 = new ModificationRecord(Modification.DEPRESS, 1, 4);
        ModificationRecord m3 = new ModificationRecord(Modification.DEPRESS, 5, 7);
        List<ModificationRecord> modifications = new ArrayList<>(Arrays.asList(m1, m2, m3));
        List<Integer> heights = new ArrayList<>(Arrays.asList(1, 0, 0, 0, 0, 0, 0, 0));
        assertFalse(Landscape.verifyModifications(5, modifications, heights));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testVerifyModificationsNullException() {
        ModificationRecord m1 = new ModificationRecord(Modification.RAISE, 1, 7);
        ModificationRecord m2 = new ModificationRecord(Modification.DEPRESS, 1, 4);
        ModificationRecord m3 = new ModificationRecord(Modification.DEPRESS, 5, 7);
        List<ModificationRecord> modifications = new ArrayList<>(Arrays.asList(m1, m2, m3));
        //List<Integer> heights = new ArrayList<>(Arrays.asList(1, 0, 0, 0, 0, 0, 0, 0));
        assertFalse(Landscape.verifyModifications(8, modifications, null));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testVerifyModificationsNullElementsException() {
        ModificationRecord m1 = new ModificationRecord(Modification.RAISE, 1, 7);
        ModificationRecord m2 = new ModificationRecord(Modification.DEPRESS, 1, 4);
        ModificationRecord m3 = new ModificationRecord(Modification.DEPRESS, 5, 7);
        List<ModificationRecord> modifications = new ArrayList<>(Arrays.asList(m1, m2, m3));
        List<Integer> heights = new ArrayList<>(Arrays.asList(1, 0, 0, null, 0, 0, 0, 0));
        assertFalse(Landscape.verifyModifications(8, modifications, heights));
    }
}
