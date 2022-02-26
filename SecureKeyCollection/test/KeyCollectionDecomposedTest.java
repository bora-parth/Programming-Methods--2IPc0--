//# BEGIN SKELETON
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for functional decomposition in {@code KeyCollectionDecomposed}.
 *
<!--//# BEGIN TODO: Name, id, and date-->
<p><font color="red"><b>Parth Bora, 1552619, 11/20/2021</b></font></p>
<!--//# END TODO-->
 */
// -----8<----- cut line -----8<-----
public class KeyCollectionDecomposedTest extends AbstractKeyCollectionTestCases
{

    public KeyCollectionDecomposedTest() {
    }

    @Before
    public void setUp() {
        instance = new KeyCollectionDecomposed();
    }

//# BEGIN TODO: Test cases for auxiliary methods
    
    //declaring keys that are going to be tested
    protected int[][] key1;
    protected int[][] key2;
    
    public void checkSecureKeys(String msg, int[][] key1, int[][] key2, 
                            boolean expResult) {
        boolean result;
        result = ((KeyCollectionDecomposed)instance).isSecureKeys(key1, key2);
        
        assertEquals(msg, expResult, result);
    }
    
    @Test
    public void inconvertibleKeysRow() {
        // Secure boundary case: two inconvertable keys, due to 1st row lengths
        key1 = new int[][]{new int[]{ 1 }, new int[]{ 1 }};
        key2 = new int[][]{new int[]{ 1, 1 }, new int[]{ 1 }};
         
        checkSecureKeys("Two Secure keys, first row lengths differ", 
                        key1, key2, true);
    }
    
    @Test
    public void inconvertibleKeysRow1() {
        // Secure boundary case: two inconvertable keys, due to 2nd row lengths
        key1 = new int[][]{new int[]{ 1 }, new int[]{ 1 }};
        key2 = new int[][]{new int[]{ 1 }, new int[]{ 1, 1 }};
        
        checkSecureKeys("Two Secure Keys, second row lengths differ", 
                        key1, key2, true);
    }
    
    @Test
    public void inconvertibleKeysSameRowLengths() {
        // Secure boundary case: two inconvertable keys, same row lengths
        key1 = new int[][]{new int[]{ 1 }, new int[]{ 2 }};
        key2 = new int[][]{new int[]{ 2 }, new int[]{ 1 }};
        
        checkSecureKeys("Two Secure keys, same row lengths",
                        key1, key2, true);
    }
    
    @Test
    public void convertibleKeysRow0() {
        // Insecure boundary case: two convertable keys, diff in first row
        key1 = new int[][]{new int[]{ 1 }, new int[]{ 1 }};
        key2 = new int[][]{new int[]{ 2 }, new int[]{ 1 }};
        
        checkSecureKeys("Two Insecure keys, first row", key1, key2, false);
    }

    @Test
    public void convertibleKeysRow1() {
        // Insecure boundary case: two convertable keys, diff in second row
        key1 = new int[][]{new int[]{ 1 }, new int[]{ 1 }};
        key2 = new int[][]{new int[]{ 1 }, new int[]{ 2 }};
        
        checkSecureKeys("Two Insecure keys, second row", key1, key2, false);
    }

    @Test
    public void convertibleKeysRow0Rev() {
        // Insecure boundary case: two convertable keys, diff in first row
        // reversed order
        key1 = new int[][]{new int[]{ 1 }, new int[]{ 1 }};
        key2 = new int[][]{new int[]{ 2 }, new int[]{ 1 }};
   
        checkSecureKeys("Two Insecure keys, reversed order, first row",
                        key1, key2, false);
    }

    @Test
    public void convertibleKeysRow1Rev() {
        // Insecure boundary case: two convertable keys, diff in second row
        // reversed order
        key1 = new int[][]{new int[]{ 1 }, new int[]{ 1 }};
        key2 = new int[][]{new int[]{ 1 }, new int[]{ 2 }};
       
        checkSecureKeys("Two Insecure keys, reversed order, second row", 
                        key1, key2, false);
    }
    
    //declaring rows that are going to be tested
    protected int[] row1;
    protected int[] row2;
    
    public void checkSecureRows(String msg, int[] row1, int[] row2, 
                            boolean expResult) {
        boolean result;
        result = ((KeyCollectionDecomposed)instance).isSecureRows(row1, row2);
        
        assertEquals(msg, expResult, result);
    }
    
    @Test
    public void diffLengthRows() {
        //secure boundary case, diff length rows
        row1 = new int[]{2,1};
        row2 = new int[]{1};
        
        checkSecureRows("Two rows of different length", row1, row2, true);
    }
    
    @Test
    public void sameLengthRowsSecure() {
        //secure boundary case: indentation of first row is deeper
        row1 = new int[]{2,1};
        row2 = new int[]{1,1};
        
        checkSecureRows("Two rows of same length, first row:deeper indentation", 
                        row1, row2, true);
    }
    
    @Test
    public void sameLengthRowsInsecure() {
        //secure boundary case: indentation of first row is deeper
        row1 = new int[]{1,1};
        row2 = new int[]{2,1};
        
        checkSecureRows("Two rows of same length, "
                        + "second row:deeper indentation", 
                        row1, row2, false);
    }
    
    @Test
    public void checkIndentationSecure() {
        //secure boundary case: first indentaion is deeper
        boolean expResult = true;
        boolean result;
        result = ((KeyCollectionDecomposed)instance).isSecureIndentation(2, 1);
        
        assertEquals("First Indentation deeper", expResult, result);
    }
    
    @Test
    public void checkIndentationSame() {
        //secure boundary case: same indentations
        boolean expResult = false;
        boolean result;
        result = ((KeyCollectionDecomposed)instance).isSecureIndentation(1, 1);
        
        assertEquals("Equal Indentation ", expResult, result);
    }
    
    @Test
    public void checkIndentationInsecure() {
        //secure boundary case: first indentation  lower
        boolean expResult = false;
        boolean result;
        result = ((KeyCollectionDecomposed)instance).isSecureIndentation(1, 2);
        
        assertEquals("First Indentation Lower", expResult, result);
    }
//# END TODO

}
//# END SKELETON
