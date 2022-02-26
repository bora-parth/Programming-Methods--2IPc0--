//# BEGIN SKELETON
/**
 * Concrete class for graded homework assignment in Series 1 of 2IPC0,
 * where you provide a functional decomposition of {@code isSecure()}.
 * <p>
 * Write your code in this file between the lines marked by
 * //# BEGIN TODO ... and //# END TODO (do NOT remove these markers).
 * <p>
<!--//# BEGIN TODO: Name, id, and date-->
<p><font color="red"><b>Parth Bora, 1552619, 19/11/2021</b></font></p>
<!--//# END TODO-->
 */
// -----8<----- cut line -----8<-----
public class KeyCollectionDecomposed extends AbstractKeyCollection {

    @Override
    public  boolean isSecure(int[][][] keys)
    {
//# BEGIN TODO: Functional decomposition; the top-level method
        
        for (int[][] key1 : keys) {
            for (int[][] key2 : keys) {
                if (key1 != key2) {
                    //key set isn't secure if two keys aren't secure
                    if (!(isSecureKeys(key1, key2))) {
                        return false;
                    }
                }
            }
        }
        
        return true;
        
//# END TODO
    }
    
//# BEGIN TODO: Functional decomposition; auxiliary method(s) with contracts
    
    /**
     * Determines whether the first key is secure w.r.t second key
     * Not robust.
     * 
     * @param key1 the first key
     * @param key2 the second key
     * @return whether {@code key1} is secure w.r.t {@code key2} 
     * @pre {@code true}
     * @modifies None
     * @post {@code \result = !CK(key1, key2)}, <br>
     * 
     * where <ul>
     *     <li>{@code
     *     CK(key1, key2) = (\forall r; key1.has(r);
     *             CR(key1[r], key2[r]))},
     *     <li>{@code
     *     CR(row1, row2) = row1.length == row2.length &&
     *             (\forall i; row1.has(i); row1[i] <= row2[i])}
     *     </ul>
     */
    public boolean isSecureKeys(int[][] key1, int[][] key2){
   
        //iterate over the rows in each key 
        for (int r = 0; r != N_ROWS; ++ r) {
            /* if a pair of rows are secure(i.e. due to length difference or 
            higher indentation) then the set of keys are also secure
            */
            if (isSecureRows(key1[r], key2[r])) {
                return true;
            }
        } // end for r
        
        return false;
    }
    
    /**
     * Determines whether the first row is secure w.r.t the second one
     * Not robust.
     * 
     * @param row1 the first row
     * @param row2 the second row
     * @return (whether {@code row1} is secure w.r.t {@code row2}
     * @pre {@code row1.length == row2.length}
     * @modifies None
     * @post {@code \result == !CR(row1, row2)}, <br>
     * 
     * where <ul>
     *     <li>{@code
     *     CR(row1, row2) = (\forall i; row1.has(i); row1[i] <= row2[i])}
     *     </ul>
     */
    public boolean isSecureRows(int[] row1, int[] row2){
       
        if (row1.length == row2.length) {
            
            //iterate over the different level of indentations in each row
            for (int i = 0; i != row1.length; ++ i) {
                if(isSecureIndentation(row1[i], row2[i])){
                    return true;
                }
            } // end for i
            return false;
        }
        
        return true;
    }
    
    
    /**
     * Checks if first indent is deeper than the second.
     * Not robust.
     * 
     * @param indent1 the first indent
     * @param indent2 the second indent
     * @return (whether {@code row1} is deeper than {@code row2}
     * @pre {@code true}
     * @modifies None
     * @post {@code \result == indent1 > indent2}   
     */
    public boolean isSecureIndentation(int indent1, int indent2){
        return indent1 > indent2;
    }
//# END TODO
        
}
//# END SKELETON
