import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Abstract test cases for {@link IntEquRelation}, to be extended to obtain
 * concrete test cases for an extension of {@link IntEquRelation}.
 *
<!--//# BEGIN TODO Parth, 1552619, and 13/12/2021-->
<!--//# END TODO-->
 */
// -----8<----- cut line -----8<-----
public abstract class IntEquRelationTestCases {

    /** Test fixture. */
    protected IntEquRelation instance;

    /**
     * Sets instance to a newly constructed equivalence relation of given extent.
     * (This is a kind of factory method; cf. Factory Method Design Pattern.)
     *
     * @param n   the given extent
     * @pre {@code 0 <= n}
     * @modifies {@code instance}
     * @post {@code instance.extent() == n
     *              && AF(instance) = set of (i,i) such that
     *                      0 <= i < instance.extent() }
     */
    protected abstract void setInstance(final int n);

    /** Tests the constructor with small extent values. */
    @Test
    public void testConstructor() {
        System.out.println("constructor(int)");
        for (int n = 0; n <= 3; ++ n) {
            setInstance(n);
            assertTrue("isRepOk()", instance.isRepOk());
        }
    }

    /** Tests the extent method with small equivalence relations. */
    @Test
    public void testExtent() {
        System.out.println("extent");
        for (int n = 0; n <= 3; ++ n) {
            setInstance(n);
            assertEquals("size", n, instance.extent());
            assertTrue("isRepOk()", instance.isRepOk());
        }
    }

    /**
     * Invokes areRelated(a, b) and checks the result.
     *
     * @param a  first element in pair
     * @param b  second element in pair
     * @param expResult  expected result
     */
    private void checkAreRelated(int a, int b, boolean expResult) {
        boolean result = instance.areRelated(a, b);
        assertEquals("areRelated(" + a + ", " + b + ")", expResult, result);
        assertTrue("isRepOk()", instance.isRepOk());
    }

    /** Tests the areRelated method on initial equivalence relation. */
    @Test
    public void testAreRelated() {
        System.out.println("areRelated");
        
        setInstance(1);
        checkAreRelated(0, 0, true);
        
        setInstance(2);
        checkAreRelated(0, 0, true);
        checkAreRelated(0, 1, false);
        checkAreRelated(1, 0, false);
        checkAreRelated(1, 1, true);
    }

    /** Tests the add method. */
    @Test
    public void testAdd() {
        System.out.println("add");
        
        setInstance(1);
        assertTrue("isRepOk()", instance.isRepOk());
        checkAreRelated(0, 0, true);
        
        setInstance(2);
        assertTrue("isRepOk()", instance.isRepOk());
        checkAreRelated(0, 0, true);
        checkAreRelated(0, 1, false);
        checkAreRelated(1, 0, false);
        checkAreRelated(1, 1, true);
        instance.add(1, 0);
        assertTrue("isRepOk()", instance.isRepOk());
        checkAreRelated(0, 0, true);
        checkAreRelated(0, 1, true);
        checkAreRelated(1, 0, true);
        checkAreRelated(1, 1, true);
        setInstance(2);
        instance.add(1, 1);
        assertTrue("isRepOk()", instance.isRepOk());
        checkAreRelated(0, 0, true);
        checkAreRelated(0, 1, false);
        checkAreRelated(1, 0, false);
        checkAreRelated(1, 1, true);
        
        setInstance(3);
        instance.add(0, 1);
        assertTrue("isRepOk()", instance.isRepOk());
        checkAreRelated(0, 0, true);
        checkAreRelated(0, 1, true);
        checkAreRelated(1, 0, true);
        checkAreRelated(1, 1, true);
        checkAreRelated(0, 2, false);
        checkAreRelated(2, 0, false);
        checkAreRelated(1, 2, false);
        checkAreRelated(2, 1, false);
        checkAreRelated(2, 2, true);
        instance.add(0, 2);
        assertTrue("isRepOk()", instance.isRepOk());
        checkAreRelated(0, 0, true);
        checkAreRelated(0, 1, true);
        checkAreRelated(1, 0, true);
        checkAreRelated(1, 1, true);
        checkAreRelated(0, 2, true);
        checkAreRelated(2, 0, true);
        checkAreRelated(1, 2, true);
        checkAreRelated(2, 1, true);
    }

    /** Tests the equClass method. */
    @Test
    public void testEquivalenceClass() {
        System.out.println("equClass");
        Set<Integer> expected;
        
        setInstance(1);
        assertTrue("isRepOk()", instance.isRepOk());
        assertTrue("isRepOk()", instance.isRepOk());
        
        setInstance(2);
        assertTrue("isRepOk()", instance.isRepOk());
        expected = new HashSet();
        expected.add(0);
        assertEquals("equClass(0)", expected, instance.equClass(0));
        expected = new HashSet();
        expected.add(1);
        assertEquals("equClass(1)", expected, instance.equClass(1));
        instance.add(1, 0);
        expected.add(1);
        expected.add(0);
        assertTrue("isRepOk()", instance.isRepOk());
        assertEquals("equClass(0)", expected, instance.equClass(0));
        assertEquals("equClass(1)", expected, instance.equClass(1));
        
        setInstance(2);
        instance.add(1, 1);
        assertTrue("isRepOk()", instance.isRepOk());
        expected = new HashSet();
        expected.add(0);
        assertEquals("equClass(0)", expected, instance.equClass(0));
        expected = new HashSet();
        expected.add(1);
        assertEquals("equClass(1)", expected, instance.equClass(1));
        
        setInstance(3);
        instance.add(0, 1);
        assertTrue("isRepOk()", instance.isRepOk());
        expected = new HashSet();
        expected.add(0);
        expected.add(1);
        assertEquals("equClass(0)", expected, instance.equClass(0));
        assertEquals("equClass(1)", expected, instance.equClass(1));
        expected = new HashSet();
        expected.add(2);
        assertEquals("equClass(2)", expected, instance.equClass(2));
        instance.add(0, 2);
        assertTrue("isRepOk()", instance.isRepOk());
        expected = new HashSet();
        expected.add(0);
        expected.add(1);
        expected.add(2);
        assertEquals("equClass(0)", expected, instance.equClass(0));
        assertEquals("equClass(1)", expected, instance.equClass(1));
        assertEquals("equClass(1)", expected, instance.equClass(2));
    }


    // Tests for robustness
    //
//# BEGIN TODO Add test cases for robustness
    
    /** Tests the constructor for exceptions. */
    @Test
    public void testConstructorException() {
        System.out.println("constructor(-1) exception");
        try {
            setInstance(-1);
            fail("should have thrown " + IllegalArgumentException.class);
        } catch (Exception e) {
            assertTrue("type: " + e.getClass().getName() + 
                        " should be instance of " + 
                        IllegalArgumentException.class,
                        IllegalArgumentException.class.isInstance(e));
            assertNotNull("message should not be null", e.getMessage());
        }
    }
    
    /**
     * Invokes areRelated(a, b) and checks for expected exception.
     *
     * @param a  the left-hand element to check
     * @param b  the right-hand element to check
     * @pre {@code ! isValidPair(a, b)}
     */
    private void checkAreRelatedException(int a, int b) {
        System.out.println("areRelated(" + a + ", " + b + ")");
        Class expected = IllegalArgumentException.class;
        try {
            instance.areRelated(a, b); // result is irrelevant
            fail("should have thrown " + expected);
        } catch (Exception e) {
            System.out.println("  " + e.toString());
            assertTrue("type: " + e.getClass().getName()
                    + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be null",
                    e.getMessage());
        }
    }

    /**
     * Tests areRelated for expected exceptions.
     */
    @Test(timeout = 10)
    public void testAreRelatedException() {
        setInstance(1);
        checkAreRelatedException(-1, 0);
        checkAreRelatedException(1, 0);
        checkAreRelatedException(0, -1);
        checkAreRelatedException(0, 1);
    }
    
    /**
     * Invokes add(a, b) and checks for expected exception.
     *
     * @param a  the left-hand element to check
     * @param b  the right-hand element to check
     * @pre {@code ! isValidPair(a, b)}
     */
    private void checkAddException(int a, int b) {
        System.out.println("add(" + a + ", " + b + ")");
        Class expected = IllegalArgumentException.class;
        try {
            instance.add(a, b);
            fail("should have thrown " + expected);
        } catch (Exception e) {
            System.out.println("  " + e.toString());
            assertTrue("type: " + e.getClass().getName()
                    + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be null",
                    e.getMessage());
        }
    }

    /**
     * Tests add for expected exceptions.
     */
    @Test(timeout = 10)
    public void testAddException() {
        setInstance(1);
        checkAddException(-1, 0);
        checkAddException(1, 0);
        checkAddException(0, -1);
        checkAddException(0, 1);
    }
    
    /**
     * Invokes remove() and checks for expected exception.
     * @pre {@code true}
     */
    @Test
    public void testRemoveException() {
        System.out.println("remove(1,1)");
        Class expected = UnsupportedOperationException.class;
        setInstance(2);
        try {
            instance.remove(1,1);
            fail("should have thrown " + expected);
        } catch (Exception e) {
            System.out.println("  " + e.toString());
            assertTrue("type: " + e.getClass().getName()
                    + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be null",
                    e.getMessage());
        }
    }
    
    /**
     * Invokes equClass(a) and checks for expected exception.
     *
     * @param a  element to return equivalence class for
     * @pre {@code !(0 <= a < extent())}
     */
    private void checkEquClassException(int a) {
        System.out.println("equClass(" + a + ")");
        Class expected = IllegalArgumentException.class;
        try {
            instance.equClass(a);
            fail("should have thrown " + expected);
        } catch (Exception e) {
            System.out.println("  " + e.toString());
            assertTrue("type: " + e.getClass().getName()
                    + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be null",
                    e.getMessage());
        }
    }

    /**
     * Tests equClass for expected exceptions.
     */
    @Test(timeout = 10)
    public void testEquClassException() {
        setInstance(1);
        checkEquClassException(-1);
        checkEquClassException(1);
    }
    
//# END TODO
}
