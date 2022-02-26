import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Abstract test cases for {@link IntRelation}, to be extended to obtain
 * concrete test cases for an extension of {@link IntRelation}.
 *
 * @author Tom Verhoeff (TU/e)
 */
public abstract class IntRelationTestCases {

    /** Test fixture. */
    protected IntRelation instance;

    /**
     * Sets instance to a newly constructed relation of given extent.
     * (This is a kind of factory method; cf. Factory Method Design Pattern.)
     *
     * @param n   extent
     */
    protected abstract void setInstance(final int n);

    /** Tests the constructor with small values. */
    @Test(timeout = 10)
    public void testConstructor() {
        System.out.println("constructor(int)");
        for (int n = 0; n <= 3; ++ n) {
            setInstance(n);
            assertTrue("isRepOk()", instance.isRepOk());
        }
    }

    /** Tests the extent method with small relations. */
    @Test(timeout = 10)
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

    /** Tests the areRelated method on empty relation. */
    @Test(timeout = 10)
    public void testAreRelated() {
        System.out.println("areRelated");
        setInstance(1);
        checkAreRelated(0, 0, false);
        setInstance(2);
        checkAreRelated(0, 0, false);
        checkAreRelated(0, 1, false);
        checkAreRelated(1, 0, false);
        checkAreRelated(1, 1, false);
    }

    /** Tests the add method. */
    @Test(timeout = 10)
    public void testAdd() {
        System.out.println("add");
        setInstance(2);
        instance.add(0, 1); // N.B. not a pair of equals
        assertTrue("isRepOk()", instance.isRepOk());
        checkAreRelated(0, 1, true);
        checkAreRelated(0, 0, false);
        checkAreRelated(1, 0, false);
        checkAreRelated(1, 1, false);
        instance.add(0, 1); // already present
        assertTrue("isRepOk()", instance.isRepOk());
        checkAreRelated(0, 1, true);
        checkAreRelated(0, 0, false);
        checkAreRelated(1, 0, false);
        checkAreRelated(1, 1, false);
    }

    /** Tests the remove method. */
    @Test(timeout = 10)
    public void testRemove() {
        System.out.println("remove");
        setInstance(2);
        instance.remove(0, 1); // N.B. not a pair of equals
        assertTrue("isRepOk()", instance.isRepOk());
        checkAreRelated(0, 0, false);
        checkAreRelated(0, 1, false);
        checkAreRelated(1, 0, false);
        checkAreRelated(1, 1, false);
        instance.add(0, 1);
        assertTrue("isRepOk()", instance.isRepOk());
        checkAreRelated(0, 1, true);
        instance.remove(0, 1);
        assertTrue("isRepOk()", instance.isRepOk());
        checkAreRelated(0, 0, false);
        checkAreRelated(0, 1, false);
        checkAreRelated(1, 0, false);
        checkAreRelated(1, 1, false);
    }

    // Tests for robustness
    //
    // We have refrained from testing checkValidPair, because
    // solutions need not provide that method.
    // We stick to black-box (functional) testing, rather than
    // glass-box (structural) testing.  In the latter case, you
    // use knowledge about the implementation to select test cases.
    // This can help reduce test cases, but can also lead to
    // extra test cases that are specific for the structure under test.

    /**
     * Tests constructor for expected exceptions.
     */
    @Test(timeout = 10)
    public void testConstructorException() {
        System.out.println("constructor(-1)");
        Class expected = IllegalArgumentException.class;
        final int n = -1;
        try {
            setInstance(n);
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
     * Invokes remove(a, b) and checks for expected exception.
     *
     * @param a  the left-hand element to check
     * @param b  the right-hand element to check
     * @pre {@code ! isValidPair(a, b)}
     */
    private void checkRemoveException(int a, int b) {
        System.out.println("remove(" + a + ", " + b + ")");
        Class expected = IllegalArgumentException.class;
        try {
            instance.remove(a, b);
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
     * Tests remove for expected exceptions.
     */
    @Test(timeout = 10)
    public void testRemoveException() {
        setInstance(1);
        checkRemoveException(-1, 0);
        checkRemoveException(1, 0);
        checkRemoveException(0, -1);
        checkRemoveException(0, 1);
    }

}
