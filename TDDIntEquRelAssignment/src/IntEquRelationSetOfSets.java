import java.util.Set;
import java.util.HashSet;

/**
 * An implementation of {@link IntEquRelation} using nested sets.
 * For sparse relations with a large extent, this reduces memory usage.
 * However, there is a bit of performance overhead.
 * 
 * @author Parth Bora (1552619)
 * @date 18/12/2021
 */
public class IntEquRelationSetOfSets extends IntEquRelation {
    
    /* Representation of the relation, using a set of sets of integers.
     * Each set represents an equivalence relation.
     */
    protected final Set<Set<Integer>> relation;
    private final int extent;
    
    /*
     * Representation invariants
     *
     * NotNull: relation != null
     * SetsNotNull: (\forall set; relation.contains(set); set != null)
     * Extent: relation.size() <= extent();
     * SetsInExtent: (\forall set; relation.contains(set);
     *               relation.get(set) subset of [0 .. extent()))
     *
     * ReflexiveRelation: (\forall i; 0 <= i < extent(); 
     *                    equClass(i).contains(i))
     *
     * SymmetricRelation: (\forall i, j; equClass(i).contains(j); 
     *                    equClass(j).contains(i))
     *
     * TransitiveRelation: (\forall i, j, k;
     *                     equClass(i).contains(j) && equClass(j).contains(k);
     *                     equClass(i).contains(k) && equClass(k).contains(i);)
     *
     * Abstraction function
     *
     * AF(this) = set of (a, b) such that equClass(a).contains(b)
     */
    
    public IntEquRelationSetOfSets(final int n){
        super(n);
        extent = n;
        relation = new HashSet<Set<Integer>>();
        for (int i = 0; i < extent; i++) {
            Set<Integer> set = new HashSet<>();
            set.add(i);
            relation.add(set);
        }
    }
    
    @Override
    public int extent() {
        return this.extent;
    }

    @Override
    public Set<Integer> equClass(int a) {  
        if ( 0 > a || a >= extent() ) {
            throw new IllegalArgumentException(
                this.getClass().getName() + ".equClass.pre violated: "
                + a + " not in extent");
        }
        
        for (Set<Integer> equSet : relation) {
            if (equSet.contains(a)) {
                return equSet;
            }
        }
       
        return new HashSet<Integer>();
    }
    
    @Override
    public boolean areRelated(int a, int b) {
        checkValidPair(a, b, "areRelated()");
        return equClass(a).contains(b);
    }
    
    @Override
    public void add(int a, int b) {
        checkValidPair(a, b, "add");
        
        Set<Integer> setA = equClass(a);
        Set<Integer> setB = equClass(b);
     
        if (! (setA == setB)) {
            Set<Integer> mergedSet = new HashSet<>();
            mergedSet.addAll(setA);
            mergedSet.addAll(setB); 
            relation.add(mergedSet);
            relation.remove(setA);
            relation.remove(setB);
        }
    }
    
    @Override
    public boolean isRepOk() {
        // NotNull
        if (relation == null) {
            throw new IllegalStateException("relation == null");
        }
        
        for (Set<Integer> set : relation) {
            // SetsNotNull
            if (set == null) {
                throw new IllegalStateException("null element in set");
            }
            
            // SetsInExtent
            for (int i : set) {
                if (! (0 <= i && i < extent())) {
                    throw new IllegalStateException("relation contains " + i);
                }
            }
        }
        
        // Extent
        if (relation.size() > extent) {
            throw new IllegalStateException("relation.size() > extent");
        }
        
        // ReflexiveRelation
        for (int i = 0; i < extent(); ++i) {
            if (! ( equClass(i).contains(i) )) {
                throw new IllegalStateException("Reflexivity Error: equClass("
                        + i + ") doesn't contain " + i);
            }
        }
        
        // SymmetricRelation
        for (int i = 0; i < extent(); ++i) {
            for (int j = 0; j < extent(); ++j) {
                if (equClass(i).contains(j)) {
                    if (! ( equClass(j).contains(i) )) {
                        throw new IllegalStateException("Symmetric Error:"
                               + " equClass(" + i + ") contains " + j + "."
                               + " But equClass(" + j +") doesn't"
                               + "contain " + i );
                    }
                }
            }
        }
        
        // TransitiveRelation
        for (int i = 0; i < extent(); ++i) {
            for (int j = 0; j < extent(); ++j) {
                for (int k = 0; k < extent(); ++k) {
                    if (equClass(i).contains(j) && equClass(j).contains(k)) {
                        if (! ( equClass(i).contains(k) )) {
                            throw new IllegalStateException("Transitivity "
                                + "Error:"
                                + " equClass(" + i+ ") contains " + j + " and"
                                + " equClass("+ j + ") contains " + k + " but "
                                + "equClass(" + i + ") doesn't contain " + k
                                +".");
                        }
                    }
                }
            }
        }
        
        return true;
    }
         
}
