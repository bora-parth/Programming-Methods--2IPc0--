//# BEGIN SKELETON
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Test cases for auxiliary methods in {@code MathStuff}.
 *
<!--//# BEGIN TODO Name, student id, and date-->
<p><b>Parth Bora, 1552619, 25/11/2021</b></p>
<!--//# END TODO-->
*/
// -----8<----- cut line -----8<-----
public class MathStuffTestAuxiliary {

//# BEGIN TODO Test cases for auxiliary functions
    
    //Test cases for Factorize
    
    private void checkFactorize(int n, ArrayList<MathStuff.Power> expFactors) {
        ArrayList<MathStuff.Power> result = MathStuff.factorize(n);
        System.out.println("Factorise (" + n + ")");
        for (int i = 0; i < expFactors.size(); i++) {
            assertEquals("base", expFactors.get(i).base, result.get(i).base);
            assertEquals("exponent", expFactors.get(i).exponent,
                        result.get(i).exponent);
        }
    }
    
    //boundary case: smallest value; n=2
    @Test
    public void testFactoriseTwo() {
        ArrayList<MathStuff.Power> factors = new ArrayList<MathStuff.Power>();
        factors.add(new MathStuff.Power(2, 1));
        checkFactorize(2, factors);
    }
    
    //boundary case: largest factorable value; n=2147483646
    @Test
    public void testFactoriseMaxValueFactorizable() {
        ArrayList<MathStuff.Power> factors = new ArrayList<MathStuff.Power>();
        factors.add(new MathStuff.Power(2, 1));
        factors.add(new MathStuff.Power(3, 2));
        factors.add(new MathStuff.Power(7, 1));
        factors.add(new MathStuff.Power(11, 1));
        factors.add(new MathStuff.Power(31, 1));
        factors.add(new MathStuff.Power(151, 1));
        factors.add(new MathStuff.Power(331, 1));
        checkFactorize(2147483646, factors);
    }
    
    //boundary case: largest int value; n=2147483647
    @Test
    public void testFactoriseIntMaxValue() {
        ArrayList<MathStuff.Power> factors = new ArrayList<MathStuff.Power>();
        factors.add(new MathStuff.Power(2147483647, 1));
        checkFactorize(2147483647, factors);
    }
    
    //average case: large int value; n=50625
    public void testFactoriseAvg() {
        ArrayList<MathStuff.Power> factors = new ArrayList<MathStuff.Power>();
        factors.add(new MathStuff.Power(3, 4));
        factors.add(new MathStuff.Power(5, 4));
        checkFactorize(50625, factors);
    }
    
    //average case: prime int; n=47
    public void testFactoriseAvgPrime() {
        ArrayList<MathStuff.Power> factors = new ArrayList<MathStuff.Power>();
        factors.add(new MathStuff.Power(47, 1));
        checkFactorize(47, factors);
    }
    
    //Test Cases for ConvertToPower
    
    private void checkConvertToPower(ArrayList<Integer> factors, 
                                    ArrayList<MathStuff.Power> expArray) {
        
        ArrayList<MathStuff.Power> result = MathStuff.convertToPower(factors);
        for (int i = 0; i < expArray.size(); i++) {
            assertEquals("base", expArray.get(i).base, result.get(i).base);
            assertEquals("exponent", expArray.get(i).exponent,
                        result.get(i).exponent);
        }
    }
    
    @Test
    public void testConvertToPowerTwo() {
        ArrayList<MathStuff.Power> expFactors = new ArrayList<MathStuff.Power>();
        expFactors.add(new MathStuff.Power(2, 1));
        
        ArrayList<Integer> factors = new ArrayList<Integer>();
        factors.add(2);
        
        checkConvertToPower(factors, expFactors);
    }
    
    @Test
    public void testConverToPowerSmall() {
        ArrayList<MathStuff.Power> expFactors = new ArrayList<MathStuff.Power>();
        expFactors.add(new MathStuff.Power(2, 4));
        
        ArrayList<Integer> factors = new ArrayList<Integer>();
        factors.add(2);
        factors.add(2);
        factors.add(2);
        factors.add(2);

        
        checkConvertToPower(factors, expFactors);
    }
    
    @Test
    public void testConverToPowerAvg() {
        ArrayList<MathStuff.Power> expFactors = new ArrayList<MathStuff.Power>();
        expFactors.add(new MathStuff.Power(2, 3));
        expFactors.add(new MathStuff.Power(3, 2));
        expFactors.add(new MathStuff.Power(5, 4));
        expFactors.add(new MathStuff.Power(7, 2));
        
        ArrayList<Integer> factors = new ArrayList<Integer>();
        factors.add(2);
        factors.add(2);
        factors.add(2);
        factors.add(3);
        factors.add(3);
        factors.add(5);
        factors.add(5);
        factors.add(5);
        factors.add(5);
        factors.add(7);
        factors.add(7);
        
        checkConvertToPower(factors, expFactors);
    }
    
    //Test Cases for gcd
    
    //boundary case: both numbers 0
    @Test
    public void testGCD0() {
        assertEquals("GCD 0,0", MathStuff.gcd(0, 0), 0);
    }
    
    //boundary case: 0 and another number
    @Test
    public void testGCD() {
        assertEquals("GCD 0,45", MathStuff.gcd(0, 45), 45);
    }
    
    //average case: numbers with gcd > 0
    @Test
    public void testGCD5() {
        assertEquals("GCD 0,45", MathStuff.gcd(25, 45), 5);
    }
    
    //boundary case: max int value
    @Test
    public void testGCDMaxIntValue() {
        assertEquals("GCD 0,45", MathStuff.gcd(2147483647, 45), 1);
    }
    
    //average case
    @Test
    public void testGCDAvg() {
        assertEquals("GCD 0,45", MathStuff.gcd(2147483646, 36), 18);
    }
    
    //Test cases for findGCD
    private void checkFindGCD(ArrayList<MathStuff.Power> factors, int gcd) {
        int result = MathStuff.findGCD(factors);
        assertEquals(result, gcd);
    }
    
    //boundary case: single element
    @Test
    public void testFindGCDSingle() {
        ArrayList<MathStuff.Power> factors = new ArrayList<MathStuff.Power>();
        factors.add(new MathStuff.Power(2, 1));
        
        checkFindGCD(factors, 1);
    }
    
    //boundary case: gcd = 1
    @Test
    public void testFindGCD1() {
        ArrayList<MathStuff.Power> factors = new ArrayList<MathStuff.Power>();
        factors.add(new MathStuff.Power(2, 4));
        factors.add(new MathStuff.Power(3, 7));
        factors.add(new MathStuff.Power(5, 8));
        
        checkFindGCD(factors, 1);
    }
    
    //boundary case: gcd > 1
    @Test
    public void testFindGCDGreater1() {
        ArrayList<MathStuff.Power> factors = new ArrayList<MathStuff.Power>();
        factors.add(new MathStuff.Power(2, 4));
        factors.add(new MathStuff.Power(3, 8));
        factors.add(new MathStuff.Power(5, 16));
        
        checkFindGCD(factors, 4);
    }
//# END TODO

}
//# END SKELETON
