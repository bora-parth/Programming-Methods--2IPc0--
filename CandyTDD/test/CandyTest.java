//# BEGIN SKELETON
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 *
<!--//# BEGIN TODO: Name, id, and date-->
<p><font color="red"><b>Parth Bora, 1552619, 17/11/2021</b></font></p>
<!--//# END TODO-->
 */
// -----8<----- cut line -----8<-----
public class CandyTest {

    final static Candy SUT = null; // to simplify method calls

    final static long MAXVALUE = 999999999999999999L;

    /**
     * Checks the result of SUT.divide(k, c).
     */
    private void check(long k, long c, boolean expected) {
        System.out.println("divide(" + k + ", " + c + ")");
        long result = SUT.divide(k, c);
        System.out.println("  result = " + result);
        assertEquals("possible (0 <= result)", expected, 0 <= result);
        if (0 <= result) {
            assertTrue("range (result <= MAXVALUE)", result <= MAXVALUE);
            assertTrue("quotient (result * k == c)", result * k == c);
        }
    }

    // Test cases

    /** The given example. */
    @Test
    public void testDivideGivenExample() {
        check(3, 15, true);
    }

//# BEGIN TODO: Additional test cases
    
    /**
     * Test of divide method, of class Candy.
     * Boundary Case: 0 kids and 0 candies
     */
    @Test
    public void testDivideZeroes() {
        check(0, 0, true);
    }
    
    /**
     * Test of divide method, of class Candy.
     * Boundary Case: 0 kids and candies > 0
     */
    @Test
    public void testDivideZeroKids() {
        check(0, 10, false);
    }
    
    /**
     * Test of divide method, of class Candy.
     * Boundary Case: > 0 kids and 0 candies
     */
    @Test
    public void testDivideZeroCandies() {
        check(10, 0, true);
    }
    
    /**
     * Test of divide method, of class Candy.
     * Boundary Case: No. of Kids > No. of Candies
     */
    @Test
    public void testDivideMoreKids() {
        check(15, 3, false);
    }
    
    /**
     * Test of divide method, of class Candy.
     * Boundary Case: No. of candies is indivisible by no. of kids
     */
    @Test
    public void testDivideNotDivisible() {
        check(199, 200, false);
    }
    
//# END TODO

}
//# END SKELETON
