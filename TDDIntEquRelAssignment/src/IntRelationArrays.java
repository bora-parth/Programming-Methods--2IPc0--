// Class to illustrate Test-Driven Development of an Abstract Data Type.

/**
 * An implementation of {@link IntRelation} using nested arrays.
 * For relations with a small extent this may work faster.
 * However, relations with a large extent use more memory,
 * even when they are sparse (have few related pairs).
 *
 * @author Tom Verhoeff (TU/e)
 */
// -----8<----- cut line -----8<-----
public class IntRelationArrays extends IntRelation {

    /** Representation of the relation. */
    protected final boolean[][] relation;

    /*
     * Representation invariants
     *
     * NotNull: relation != null
     * Extent: relation.length == extent()
     * ElementsNotNull: (\forall i; relation.has(i); relation[i] != null)
     * ElementsSameSize: (\forall i; relation.has(i);
     *     relation[i].length == relation.length)
     *
     * Abstraction function: set of (a, b) such that relation[a][b] holds
     */

    public IntRelationArrays(final int n) {
        super(n);
        relation = new boolean[n][n];
    }

    @Override
    public boolean isRepOk() {
        // NotNull
        if (relation == null) {
            throw new IllegalStateException("relation == null");
        }
        for (int i = 0; i != extent(); ++ i) {
            // ElementsNotNull
            if (relation[i] == null) {
                throw new IllegalStateException(
                        "relation[" + i + "] == null");
            }
            // ElementsSameSize
            if (relation[i].length != extent()) {
                throw new IllegalStateException(
                        "relation[" + i + "].length != extent()");
            }
        }
        return true;
    }

    @Override
    public int extent() {
        return relation.length;
    }

    @Override
    public boolean areRelated(int a, int b) {
        checkValidPair(a, b, "areRelated");
        return relation[a][b];
    }

    @Override
    public void add(int a, int b) {
        checkValidPair(a, b, "add");
        relation[a][b] = true;
    }

    @Override
    public void remove(int a, int b) {
        checkValidPair(a, b, "remove");
        relation[a][b] = false;
    }

}
