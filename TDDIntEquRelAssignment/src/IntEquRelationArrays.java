
import java.util.HashSet;
import java.util.Set;

// Class to illustrate Test-Driven Development of an Abstract Data Type.
// Name: Parth Bora, Student No: 1552619, Date: 18/12/2021

/**
 * An implementation of {@link IntEquRelation} using nested arrays,
 * and computing equivalence class sets on the fly.
 * Relations with a large extent use more memory,
 * even when they are sparse (have few related pairs).
 *
 * @author Loek Cleophas / Tom Verhoeff (TU/e)
 */
public class IntEquRelationArrays extends IntEquRelation {

    /* Representation of the relation, using IntRelationArrays
     * implementation of IntRelation ADT
     */
    protected final IntRelationArrays relation;

    /*
     * Representation invariants
     *
     * NotNull: relation != null
     * RelationRepInvariant: see {@link IntRelationArrays}
     * ReflexiveRelation: (\forall i; relation.has(i); relation[i][i])
     * SymmetricRelation: (\forall i, j; relation.has(i) && relation.has(j);
     *      relation[i][j] == relation[j][i])
     * TransitiveRelation: (\forall i, j, k; relation.has(i) && relation.has(j)
     *      && relation.has(k);
     *      relation[i][j] && relation[j][k] => relation[i][k])
     *
     * Abstraction function
     *
     * AF(this) = set of (a, b) such that relation[a][b] holds
     */

    public IntEquRelationArrays(final int n) {
        super(n);
        relation = new IntRelationArrays(n);
        for (int i = 0; i < n; ++i) {
            relation.add(i, i);
        }
    }

    @Override
    public boolean isRepOk() {
        // NotNull
        if (relation == null) {
            throw new IllegalStateException("relation == null");
        }
        // RelationRepInvariant
        if (!(relation.isRepOk())) {
            throw new IllegalStateException("relation.isRepOk() == false");
        }
        // ReflexiveRelation
        for (int i = 0; i < extent(); ++i) {
            if (!(relation.areRelated(i, i))) {
                throw new IllegalStateException(
                        "relation.areRelated(" + i + "," + i + ") == false");
            }
        }
        // SymmetricRelation
        for (int i = 0; i < extent(); ++i) {
            for (int j = 0; j < extent(); ++j) {
                if (relation.areRelated(i, j) != relation.areRelated(j, i)) {
                    throw new IllegalStateException(
                        "relation.areRelated(" + i + "," + j
                                + ") != relation.areRelated(" + j + "," + i
                                + ")");
                }
            }
        }
        // TransitiveRelation
        for (int i = 0; i < extent(); ++i) {
            for (int j = 0; j < extent(); ++j) {
                for (int k = 0; k < extent(); ++k) {
                    if (relation.areRelated(i, j) && relation.areRelated(j, k)
                            && !(relation.areRelated(i, k))) {
                        throw new IllegalStateException(
                            "relation.areRelated(" + i + "," + j
                                    + ") && relation.areRelated(" + j + "," + k
                                    + ") && !(relation.areRelated(" + i + ","
                                    + k + "))");
                    }
                }
            }
        }

        return true;
    }

    @Override
    public int extent() {
        return relation.extent();
    }

    @Override
    public boolean areRelated(int a, int b) {
        return relation.areRelated(a, b);
    }

    @Override
    public void add(int a, int b) {
        relation.add(a, a);
        relation.add(b, b);
        for (Integer i : equClass(a)) {
            for (Integer j : equClass(b)) {
                relation.add(i, j);
                relation.add(j, i);
            }
        }
    }

    @Override
    public Set<Integer> equClass(int a) {
        if( 0 > a || a >= extent() ) {
            throw new IllegalArgumentException(
                this.getClass().getName() + ".equClass.pre violated: "
                + a + " not in extent");
        }
        Set<Integer> related = new HashSet<>();
        for (int i = 0; i < extent(); ++ i) {
            if (areRelated(a, i)) {
                related.add(i);
            }
        }
        return related;
    }

}
