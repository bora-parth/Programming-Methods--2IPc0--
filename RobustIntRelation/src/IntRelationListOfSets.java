import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Class to illustrate Test-Driven Development of an Abstract Data Type.

/**
 * An implementation of {@link IntRelation} using a {@link List} of {@link Sets}.
 * For sparse relations with a large extent, this reduces memory usage.
 * However, there is a bit of performance overhead.
 *
 * @author Tom Verhoeff (TU/e)
 */
public class IntRelationListOfSets extends IntRelation {

    /** Representation of the relation. */
    protected final List<Set<Integer>> relation;

    /*
     * Representation invariants
     *
     * NotNull: relation != null
     * SetsNotNull: (\forall i; relation.has(i); relation.get(i) != null),
     *     where List.has(i) == 0 <= i < List.size()
     * SetsInExtent: (\forall i; relation.has(i);
     *     relation.get(i) subset of [0 .. relation.size()))
     *
     * Abstraction function
     *
     * AF(this) = set of (a, b) such that relation.get(a).contains(b)
     */

    public IntRelationListOfSets(final int n) throws IllegalArgumentException {
        super(n);
        
        if (n < 0) {
            throw new IllegalArgumentException("n should be greater than 0");
        }
        
        relation = new ArrayList<Set<Integer>>();
        /* Java 7, but this upsets Checkstyle
        relation = new ArrayList<>();
        */
        for (int i = 0; i != n; ++ i) {
            relation.add(new HashSet<Integer>());
        }
    }

 /* Why is it harder to get the following intialization to work?
  * It is probably better to exclude this through a rep invariant.
        relation = new ArrayList<Set<Integer>>();
        final Set empty = new HashSet<Integer>();
        for (int i = 0; i != n; ++ i) {
            relation.add(empty);
        }
  */
    @Override
    public boolean isRepOk() {
        // NotNull
        if (relation == null) {
            throw new IllegalStateException("relation == null");
        }
        for (int i = 0; i != extent(); ++ i) {
            final Set<Integer> set = relation.get(i);
            // SetsNotNull
            if (set == null) {
                throw new IllegalStateException(
                        "relation.get(" + i + ") == null");
            }
            // SetsInExtent
            for (int j : set) {
                if (! (0 <= j && j < extent())) {
                    throw new IllegalStateException(
                            "relation.get(" + i + ") contains " + j);
                }
            }
        }
        return true;
    }

    @Override
    public int extent() {
        return relation.size();
    }

    @Override
    public boolean areRelated(int a, int b) throws IllegalArgumentException {
        if (! (this.isValidPair(a, b))) {
            throw new IllegalArgumentException("a & b is not a valid pair");
        }
        return relation.get(a).contains(b);
    }

    @Override
    public void add(int a, int b) throws IllegalArgumentException {
        if (! (this.isValidPair(a, b))) {
            throw new IllegalArgumentException("a & b is not a valid pair");
        }
        relation.get(a).add(b);
    }

    @Override
    public void remove(int a, int b) throws IllegalArgumentException {
        if (! (this.isValidPair(a, b))) {
            throw new IllegalArgumentException("a & b is not a valid pair");
        }
        relation.get(a).remove(b);
    }

}
