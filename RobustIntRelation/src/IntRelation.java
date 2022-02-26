// Class to illustrate Test-Driven Development of an Abstract Data Type.

/**
 * @name Parth Bora
 * @studentID 1552619
 * @date 1/12/2021
 */

/**
 * An {@code IntRelation} object maintains a relation on small integers.
 * The relation is a subset of {@code [0..n) x [0..n)},
 * where {@code n}, called the <em>extent</em> of the relation,
 * is given in the constructor and is immutable.
 * There are operations to test membership,
 * and to add and remove pairs.
 * <p>
 * Model: subset of {@code [0..extent()) x [0..extent())}
 *
 * @inv {@code NonNegativeExtent: 0 <= extent()}
 *
 * @author Tom Verhoeff (TU/e)
 */
public abstract class IntRelation {

    /**
     * Constructs an empty relation of given extent.
     *
     * @param n  extent of the new relation
     * @pre {@code 0 <= n}
     * @throws IllegalArgumentException if {@code n < 0}
     * @post {@code this == [ ]  &&  this.extent() == n}
     */
    public IntRelation(final int n) throws IllegalArgumentException {
    }

    /**
     * Checks whether the representation invariants hold.
     *
     * @return whether the representation invariants hold
     * @throws IllegalStateException  if precondition violated
     * @pre representation invariants hold
     * @modifies None
     * @post {@code \result}
     */
    public abstract boolean isRepOk() throws IllegalStateException;

    /**
     * Returns the extent of this relation.
     *
     * @return extent of this relation
     * @pre {@code true}
     * @modifies None
     * @post (basic query)
     */
    public abstract int extent();

    /**
     * Determines whether a pair is valid for this relation.
     *
     * @param a  first element of the pair
     * @param b  second element of the pair
     * @return whether the pair is valid for this relation
     * @pre {@code true}
     * @modifies None
     * @post {@code \result == 0 <= a < extent() && 0 <= b < extent()}
     */
    public boolean isValidPair(int a, int b) {
        return 0 <= a && a < this.extent()
                && 0 <= b && b < this.extent();
    }

    /**
     * Returns whether the elements in a pair are related.
     *
     * @param a  first element of the pair
     * @param b  second element of the pair
     * @return  whether {@code (a, b)} are related
     * @pre {@code isValidPair(a, b)}
     * @modifies None
     * @throws IllegalArgumentException if {@code !(isValidPair(a, b))}
     * @post {@code \result == (a, b) in this}
     */
    public abstract boolean areRelated(int a, int b)
            throws IllegalArgumentException;

    /**
     * Adds a pair to the relation.
     *
     * @param a  first element of the pair to add
     * @param b  second element of the pair to add
     * @pre {@code isValidPair(a, b)}
     * @modifies {@code this}, but not {@code this.extent()}
     * @throws IllegalArgumentException if {@code !(isValidPair(a, b))}
     * @post {@code this == \old(this) union [ (a, b) ]}
     */
    public abstract void add(int a, int b) throws IllegalArgumentException;

    /**
     * Removes a pair from the relation.
     *
     * @param a  first element of the pair to remove
     * @param b  second element of the pair to remove
     * @pre {@code isValidPair(a, b)}
     * @modifies {@code this}, but not {@code this.extent()}
     * @throws IllegalArgumentException if {@code !(isValidPair(a, b))}
     * @post {@code this == \old(this) minus [ (a, b) ]}
     */
    public abstract void remove(int a, int b) throws IllegalArgumentException;

}
