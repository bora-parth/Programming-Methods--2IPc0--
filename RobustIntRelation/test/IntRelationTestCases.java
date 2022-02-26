import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @name Parth Bora
 * @studentID 1552619
 * @date 1/12/2021
 */

/**
 * Abstract test cases for IntRelation, to be extended to obtain
 * concrete test cases for an extension of IntRelation.
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
     * @param n   the given extent
     * @pre {@code 0 <= n}
     * @modifies {@code instance}
     * @post {@code instance.extent() == n && AF(instance) = [ ]}
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
    
    /** Tests the constructor with exceptions. */
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
    
    /** Tests the extent method with small relations. */
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

    /** Tests the areRelated method on empty relation. */
    @Test
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
    
    /**
     * Invokes areRelated(a, b) and checks if an exception is thrown.
     * 
     * @param extent  the extent of instance
     * @param a first element in pair
     * @param b second element in pair
     */
    private void checkAreRelatedException(int extent, int a, int b) {
        System.out.println("areRelated( " + a + ", " + b + ") exception");
        setInstance(extent);
        
        try {
            instance.areRelated(a, b);
            fail("should have thrown " + IllegalArgumentException.class);
        } catch (Exception e) {
            assertTrue("type: " + e.getClass().getName() + 
                        " should be instance of " + 
                        IllegalArgumentException.class,
                        IllegalArgumentException.class.isInstance(e));
            assertNotNull("message should not be null", e.getMessage());
        }
    }
    
    /** Tests areRelated method on empty relation for exceptions.*/
    @Test
    public void testAreRelatedExceptionA() {
        //boundary case a = extent
        checkAreRelatedException(2, 2, 1);
    }
    
    @Test
    public void testAreRelatedExceptionB() {
        //boundary case b > extent
        checkAreRelatedException(2, 1, 5);
    }
    
    @Test
    public void testAreRelatedExceptionAB() {
        //boundary case a&b are invalid
        checkAreRelatedException(2, 5, 3);
    }

    /** Tests the add method. */
    @Test
    public void testAdd() {
        System.out.println("add");
        setInstance(2);
        instance.add(0, 1); // N.B. not a pair of equals
        assertTrue("isRepOk()", instance.isRepOk());
        checkAreRelated(0, 1, true);
        checkAreRelated(0, 0, false);
        checkAreRelated(1, 0, false);
        checkAreRelated(1, 1, false);
        instance.add(0, 1);
        assertTrue("isRepOk()", instance.isRepOk());
        checkAreRelated(0, 1, true);
        checkAreRelated(0, 0, false);
        checkAreRelated(1, 0, false);
        checkAreRelated(1, 1, false);
    }
    
    /**
     * Invokes add(a, b) and checks if an exception is thrown.
     * 
     * @param extent  the extent of instance
     * @param a first element in pair
     * @param b second element in pair
     */
    private void checkAddException(int extent, int a, int b) {
        System.out.println("areRelated( " + a + ", " + b + ") exception");
        setInstance(extent);
        
        try {
            instance.add(a, b);
            fail("should have thrown " + IllegalArgumentException.class);
        } catch (Exception e) {
            assertTrue("type: " + e.getClass().getName() + 
                        " should be instance of " + 
                        IllegalArgumentException.class,
                        IllegalArgumentException.class.isInstance(e));
            assertNotNull("message should not be null", e.getMessage());
        }
    }
    
    /** Tests add method on empty relation for exceptions.*/
    @Test
    public void testAddExceptionA() {
        //boundary case: a = extent
        checkAddException(2, 2, 1);
    }
    
    @Test
    public void testAddExceptionB() {
        //boundary case: b > extent
        checkAddException(2, 1, 3);
    }
    
    @Test
    public void testAddExceptionAB() {
        //boundary case: a&b are invalid
        checkAddException(2, 4, 5);
    }
    
    /** Tests the remove method. */
    @Test
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
    
    /**
     * Invokes remove(a, b) and checks if an exception is thrown.
     * 
     * @param extent  the extent of instance
     * @param a first element in pair
     * @param b second element in pair
     */
    private void checkRemoveException(int extent, int a, int b) {
        System.out.println("areRelated( " + a + ", " + b + ") exception");
        setInstance(extent);
        
        try {
            instance.remove(a, b);
            fail("should have thrown " + IllegalArgumentException.class);
        } catch (Exception e) {
            assertTrue("type: " + e.getClass().getName() + 
                        " should be instance of " + 
                        IllegalArgumentException.class,
                        IllegalArgumentException.class.isInstance(e));
            assertNotNull("message should not be null", e.getMessage());
        }
    }
    
    /** Tests add method on empty relation for exceptions.*/
    @Test
    public void testRemoveExceptionA() {
        //boundary case: a = extent
        checkRemoveException(2, 2, 1);
    }
    
    @Test
    public void testRemoveExceptionB() {
        //boundary case: b > extent
        checkRemoveException(2, 1, 5);
    }
    
    @Test
    public void testRemoveExceptionAB() {
        //boundary case: a&b are invalid
        checkRemoveException(2, 4, 5);
    }
}
