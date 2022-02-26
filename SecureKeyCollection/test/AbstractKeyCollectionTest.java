import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for non-abstract methods in {@code AbstractKeyCollection}.
 *
 * @author Tom Verhoeff (TU/e)
 * @since 14 Nov 2012
 */
public class AbstractKeyCollectionTest {

    /** Instance under test, initialized in setUp(). */
    private AbstractKeyCollection instance;

    /** Key to check. */
    private int[][] key;

    /**
     * Prepare for test.
     */
    @Before
    public void setUp() {
        instance = new TestKeyCollection();
        key = null;
    }

    private class TestKeyCollection extends AbstractKeyCollection {

        // This method will not be tested here.
        @Override
        public boolean isSecure(int[][][] keys) {
            return false;
        }

    }

    /**
     * Invokes isKey(key) and checks result against expResult.
     *
     * @param msg  message
     * @param expResult  expected result
     */
    private void checkKey(String msg, boolean expResult) {
        boolean result = instance.isKey(key);
        assertEquals(msg, expResult, result);
    }

    // Tests for method isKey()

    @Test
    public void nullKey() {
        // Boundary case: null key
        key = null;
        checkKey("Null key", false);
    }

    @Test
    public void emptyKeyRow0() {
        // Boundary case: incorrect key with empty first row
        key = new int[][] { new int[] { }, new int[] { 1 }};
        checkKey("Empty key, 1st row", false);
    }

    @Test
    public void emptyKeyRow1() {
        // Boundary case: incorrect key with empty second row
        key = new int[][] { new int[] { 1 }, new int[] { }};
        checkKey("Empty key, 2nd row", false);
    }

    @Test
    public void minimalSingletonKey() {
        // Boundary case: correct key with minimal singleton rows
        key = new int[][] { new int[] { 1 }, new int[] { 1 }};
        checkKey("Minimal depths key", true);
    }

    @Test
    public void maximalSingletonKey() {
        // Boundary case: correct key with maximal singleton rows
        key = new int[][] {
            new int[] { instance.MAX_DEPTH },
            new int[] { instance.MAX_DEPTH }};
        checkKey("Maximal depths key", true);
    }

    @Test
    public void subminimalSingletonRow0Key() {
        // Boundary case: incorrect key with subminimal singleton first row
        key = new int[][] { new int[] { 0 }, new int[] { 1 }};
        checkKey("Subminimal depth key, 1st row", false);
    }

    @Test
    public void subminimalSingletonRow1Key() {
        // Boundary case: incorrect key with subminimal singleton second row
        key = new int[][] { new int[] { 1 }, new int[] { 0 }};
        checkKey("Subminimal depth key, 2nd row", false);
    }

    @Test
    public void supermaximalSingletonRow0Key() {
        // Boundary case: incorrect key with supermaximal singleton first row
        key = new int[][] {
            new int[] { instance.MAX_DEPTH + 1 },
            new int[] { 1 }};
        checkKey("Supermaximal depth key, 1st row", false);
    }

    @Test
    public void supermaximalSingletonRow1Key() {
        // Boundary case: incorrect key with supermaximal singleton second row
        key = new int[][] {
            new int[] { 1 },
            new int[] { instance.MAX_DEPTH + 1 }};
        checkKey("Supermaximal depth key, 2nd row", false);
    }

    @Test
    public void typicalKey() {
        // Typical case: correct key with all allowed depths
        key = new int[instance.N_ROWS][instance.MAX_DEPTH];
        for (int i = 0; i != instance.MAX_DEPTH; ++i) {
            key[0][i] = i + 1;
        }
        for (int i = 0; i != instance.MAX_DEPTH; ++i) {
            key[1][i] = instance.MAX_DEPTH - i;
        }
        checkKey("Typical key, 2nd row", true);
    }

}
