/**
 * This abstract class for the graded homework assignment in Series 1 of 2IPC0
 * provides an auxiliary function and the signature of {@code isSecure()}.
 * <p>
 * A particular type of key to open a lock consists of two rows of
 * indentations of varying depth.  The indentation depth varies
 * from 1 to 5.  The rows are not necessarily of equal length.
 * You can convert one key into another key by deepening some of
 * the indentations of the first key.  Of course, the resulting key
 * has the same row lengths for corresponding rows.
 * <p>
 * For example, if the depths of the first key are {{1, 2}, {5, 1, 1}},
 * then it can be converted into the key {{2, 2}, {5, 1, 3}},
 * but not into the keys {{2, 1}, {5, 1, 3}} and {{1, 3, 1}, {5, 2, 1}}.
 * <p>
 * A collection of keys is called <em>secure</em>, when no key in the set
 * can be converted into another key in the set.
 * <p>
 * In the following code, the number of rows and the maximum indentation depth
 * is parameterized by the constants {@code N_ROWS} and {@code MAX_DEPTH}.
 * <p>
 * @author Tom Verhoeff (TU/e)
 * @since 14 Nov 2012
 */
public abstract class AbstractKeyCollection {

    /** Number of rows in a key. {@code N_ROWS >= 2} */
    final static int N_ROWS = 2;
    
    /** Number of indentation depths in a key. {@code MAX_DEPTH >= 2} */
    final static int MAX_DEPTH = 5;
    
    /**
     * Determines whether the given integer array represents a key.
     * 
     * @param key  the key to be checked
     * @return  whether {@code key} represents a key.
     * @pre {@code true}
     * @modifies None
     * @post {@code \result == (key != null &&
     *     key.length == N_ROWS && B}<br>
     * where<ul>
     *     <li>{@code B = (\forall r; key.has(r);
     *         key[r] != null && key[r].length != 0 &&
     *         (\forall i; key[r].has(i); 1 <= key[r][i] <= MAX_DEPTH))}
     * </ul>
     */ 
    public boolean isKey(int[][] key) {
        if (key == null) {
            return false;
        }
        if (key.length != N_ROWS) {
            return false;
        }
        // key.length == N_ROWS
        for (int r = 0; r != N_ROWS; ++ r) {
            if (key[r] == null || key[r].length == 0) {
                return false;
            }
            // key[r].length >= 1
            for (int i = 0; i != key[r].length; ++ i) {
                if (! (1 <= key[r][i] && key[r][i] <= MAX_DEPTH)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Determines whether the given set of keys is secure.
     * Not robust.
     * 
     * @param keys  array with the keys
     * @return whether {@code keys} forms a secure set of keys
     * @pre {@code keys != null &&
     *     (\forall k; keys.has(k); isKey(keys[k])}
     * @modifies None
     * @post {@code \result ==
     *     (\forall k1, k2; keys.has(k1) && keys.has(k2) && k1 != k2;
     *         ! CK(keys[k1], keys[k2]))},<br>
     * where<ul>
     *     <li>{@code
     *     CK(key1, key2) = (\forall r; key1.has(r);
     *             CR(key1[r], key2[r]))},
     *     <li>{@code
     *     CR(row1, row2) = row1.length == row2.length &&
     *             (\forall i; row1.has(i); row1[i] <= row2[i])}
     *     </ul>
     */
    public abstract boolean isSecure(int[][][] keys);
        
}
