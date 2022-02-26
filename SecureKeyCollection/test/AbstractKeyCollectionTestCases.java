import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Abstract test cases, reusable for implementations of
 * {@code AbstractKeyCollection}.
 * Note that these test cases are not applied to {@code AbstractKeyCollection},
 * because it is not named {@code AbstractKeyCollectionTest}.
 * There should only be test cases here that apply to
 * {@code AbstractKeyCollection}.
 * In particular, test cases for auxiliary functions that are specific
 * to a concrete class must not be added here, but to the test class
 * for the concrete class that inherits from {@code AbstractKeyCollection}.
 *
 * @author Tom Verhoeff (TU/e)
 * @since 14 Nov 2012
 */
public class AbstractKeyCollectionTestCases {

    /**
     * Implementation under test. Set by concrete test class.
     */
    protected AbstractKeyCollection instance;

    /**
     * Key collection to check.
     */
    protected int[][][] keys;

    public AbstractKeyCollectionTestCases() {
    }

    // Abstract test cases for isSecure

    /**
     * Invokes isSecure(keys) and checks result against expResult.
     *
     * @param msg message
     * @param expResult expected result
     */
    private void checkKeys(String msg, boolean expResult) {
        boolean result = instance.isSecure(keys);
        assertEquals(msg, expResult, result);
    }

    @Test
    public void noKeys() {
        // Secure boundary case: no keys
        keys = new int[][][]{ };
        checkKeys("No keys", true);
    }

    @Test
    public void singletonKeys() {
        // Secure boundary case: one minimal key
        keys = new int[][][]{
            new int[][]{new int[]{ 1 }, new int[]{ 1 }}};
        checkKeys("One minimal key", true);
    }

    @Test
    public void twoInconvertibleKeysRow0() {
        // Secure boundary case: two inconvertable keys, due to 1st row lengths
        keys = new int[][][]{
            new int[][]{new int[]{ 1 }, new int[]{ 1 }},
            new int[][]{new int[]{ 1, 1 }, new int[]{ 1 }}
        };
        checkKeys("Two inconvertable keys, first row lengths differ", true);
    }

    @Test
    public void twoInconvertibleKeysRow1() {
        // Secure boundary case: two inconvertable keys, due to 2nd row lengths
        keys = new int[][][]{
            new int[][]{new int[]{ 1 }, new int[]{ 1 }},
            new int[][]{new int[]{ 1 }, new int[]{ 1, 1 }}
        };
        checkKeys("Two inconvertable keys, second row lengths differ", true);
    }

    @Test
    public void twoInconvertibleKeysSameRowLengths() {
        // Secure boundary case: two inconvertable keys, same row lengths
        keys = new int[][][]{
            new int[][]{new int[]{ 1 }, new int[]{ 2 }},
            new int[][]{new int[]{ 2 }, new int[]{ 1 }}
        };
        checkKeys("Two inconvertable keys, same row lengths", true);
    }

    @Test
    public void twoConvertibleKeysRow0() {
        // Insecure boundary case: two convertable keys, diff in first row
        keys = new int[][][]{
            new int[][]{new int[]{ 1 }, new int[]{ 1 }},
            new int[][]{new int[]{ 2 }, new int[]{ 1 }}
        };
        checkKeys("Two convertable keys, first row", false);
    }

    @Test
    public void twoConvertibleKeysRow1() {
        // Insecure boundary case: two convertable keys, diff in second row
        keys = new int[][][]{
            new int[][]{new int[]{ 1 }, new int[]{ 1 }},
            new int[][]{new int[]{ 1 }, new int[]{ 2 }}
        };
        checkKeys("Two convertable keys, second row", false);
    }

    @Test
    public void twoConvertibleKeysRow0Rev() {
        // Insecure boundary case: two convertable keys, diff in first row
        // reversed order
        keys = new int[][][]{
            new int[][]{new int[]{ 2 }, new int[]{ 1 }},
            new int[][]{new int[]{ 1 }, new int[]{ 1 }}
        };
        checkKeys("Two convertable keys, reversed order, first row", false);
    }

    @Test
    public void twoConvertibleKeysRow1Rev() {
        // Insecure boundary case: two convertable keys, diff in second row
        // reversed order
        keys = new int[][][]{
            new int[][]{new int[]{ 1 }, new int[]{ 2 }},
            new int[][]{new int[]{ 1 }, new int[]{ 1 }}
        };
        checkKeys("Two convertable keys, reversed order, second row", false);
    }

}
