/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * A Counter object keeps track of an integer count,
 * that can be inspected, incremented by one, and
 * reset to zero.  The initial value is zero.
 * 
 * @author Parth Bora (1552619)
 */
public class Counter {
    
    /** The Count. */
    private long count;
    
    // Representation invariant: count >= 0
    // Abstraction function: coint
    
    /*
    * Get the current count.
    */
    public long getCount() {
        return count;
    }
    
    /*
    * Increment the count by one.
    */
    public void increment() {
        count = count + 1;
    }
    
    /*
    * Reset the count to zero..
    */
    public void reset() {
        count = 0;
    }
}
