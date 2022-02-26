/**
 * Concrete class for graded homework assignment in Series 1 of 2IPC0,
 * providing a (defective) monolithic implementation of {@code isSecure()}.
 * <p>
 * @author Tom Verhoeff (TU/e)
 * @since 14 Nov 2012
 */
public class KeyCollectionMonolithic extends AbstractKeyCollection {

    @Override
    public boolean isSecure(int[][][] keys)
    {
        for (int k1 = 0; k1 != keys.length; ++ k1) {
            for (int k2 = 0; k2 != keys.length; ++ k2) {
                if (k1 != k2) {
                    boolean convertable; // can key k1 be converted into key k2?
                    convertable = true; // anticipated
                    
                    for (int r = 0; r != N_ROWS; ++ r) {
                        if (keys[k1][r].length != keys[k2][r].length) {
                            convertable = false;
                            break;
                        }

                        for (int i = 0; i != keys[k1].length; ++ i) {
                            if (keys[k1][r][i] > keys[k2][r][i]) {
                                convertable = false;
                                break;
                            }
                        } // end for i

                        if (! convertable) {
                            break;
                        }
                    } // end for r
                    
                    if (convertable) {
                        return false;
                    }
                }
            } // end for k2
        } // end for k1
        
        return true;
    }
    
}
