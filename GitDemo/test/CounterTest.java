/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test cases for class Counter.
 * @author Parth Bora (1552619)
 */
public class CounterTest {
    
    public CounterTest() {
    }

    /**
     * Test of getCount method, of class Counter.
     */
    @Test
    public void testGetCount() {
        System.out.println("getCount");
        Counter instance = new Counter();
        long expResult = 0L;
        long result = instance.getCount();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of increment method, of class Counter.
     */
    @Test
    public void testIncrement() {
        System.out.println("increment");
        Counter instance = new Counter();
        instance.increment();
        long expResult = 1L;
        long result = instance.getCount();
        assertEquals("getCount", expResult, result);
    }

    /**
     * Test of reset method, of class Counter.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        Counter instance = new Counter();
        instance.increment();
        instance.reset();
        long expResult = 0L;
        long result = instance.getCount();
        assertEquals("getCount", expResult, result);
    }
    
}
