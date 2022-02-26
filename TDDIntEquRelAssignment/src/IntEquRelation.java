
import java.util.Set;

// Class to illustrate Test-Driven Development of an Abstract Data Type.

/**
 * An {@code IntEquRelation} object maintains an equivalence relation
 * on small integers.
 * The relation is a subset of {@code [0..n) x [0..n)},
 * where {@code n}, called the <em>extent</em> of the relation,
 * is given in the constructor and is immutable.
 * There are operations to test membership,
 * to retrieve the equivalence class of an element,
 * and to add pairs
 * <p>
 * Model: subset of {@code [0..extent()) x [0..extent())}
 *
 * @inv {@code (NonNegativeExtent: 0 <= extent()) &&
 * (ReflexiveRelation: (\forall i; 0 <= i < extent(); areRelated(i, i))) &&
 * (SymmetricRelation: (\forall i, j; areRelated(i, j); areRelated(j, i))) &&
 * (TransitiveRelation: (\forall i, j, k; areRelated(i, j) && areRelated(j, k);
 *                              areRelated(i, k)))}
 * @author Loek Cleophas / Tom Verhoeff (TU/e)
 */
public abstract class IntEquRelation extends IntRelation {

    /**
     * Constructs an empty equivalence relation of given extent.
     *
     * @param n  extent of the new equivalence relation
     * @throws IllegalArgumentException  if n < 0
     * @pre {@code 0 <= n}
     * @post {@code this is the identity relation on [0..extent()) (the smallest equivalence relation on this set).}
     */
    public IntEquRelation(final int n) throws IllegalArgumentException {
        super(n);
    }

    /**
     * Adds a pair to the equivalence relation.
     *
     * @param a  first element of the pair to add
     * @param b  second element of the pair to add
     * @throws IllegalArgumentException  if ! isValidPair(a, b)
     * @pre {@code isValidPair(a, b)}
     * @modifies {@code this}, but not {@code this.extent()}
     * @post {@code this} is the minimal extension of
     *     {@code \old(this) union [ (a, b) ]} such that {@code this} is
     *     an equivalence relation
     */
    @Override
    public abstract void add(int a, int b) throws IllegalArgumentException;

    @Override
    public void remove(int a, int b) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param a  element to return equivalence class for
     * @throws IllegalArgumentException if ! (0 <= a < extent())
     * @pre {@code 0 <= a < extent()}
     * @return   the equivalence class to which {@code a} belongs, as a set
     * @modifies None
     * @post {@code \result} consists of all b
     *     such that {@code (a,b) in this}
     */
    public abstract Set<Integer> equClass(int a)
            throws IllegalArgumentException;
}
