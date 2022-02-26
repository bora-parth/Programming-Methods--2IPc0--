//# BEGIN SKELETON
import java.util.ArrayList;
import java.util.List;

/**
 * Library with static mathematical functions.
 *
<!--//# BEGIN TODO Name, student id, and date-->
<p><b>Parth Bora, 1552619, 24/11/2021</b></p>
<!--//# END TODO-->
*/
// -----8<----- cut line -----8<-----
public abstract class MathStuff {

    /**
     * Returns exponentiation, taking care of overflow.
     *
     * @param a  the base
     * @param b  the exponent
     * @pre {@code 0 <= a && 0 <= b}
     * @return {@code a ^ b} if {@code a ^ b <= Integer.MAX_VALUE}
     *      else {@code Long.MAX_VALUE}
     * @throws IllegalArgumentException  if precondition violated
     */
    public static long power(int a, int b) throws IllegalArgumentException {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("power: negative argument");
        }
        // 0 <= a && 0 <= b
        long x = a; // see invariant
        int k = b; // see invariant
        long result = 1L; // see invariant

        // invariant: 0 <= k <= b && result * x^k == a^b
        while (k != 0) {
            if (k % 2 == 0) { // even exponent
                if (x <= Integer.MAX_VALUE) {
                    x *= x;
                } else {
                    x = Long.MAX_VALUE;
                }
                k /= 2;
            } else { // odd exponent
                if (result <= Integer.MAX_VALUE && x <= Integer.MAX_VALUE) {
                    result *= x;
                } else {
                    result = Long.MAX_VALUE;
                }
                k -= 1;
            }
            // invariant holds again, k has decreased
        }
        // k == 0, hence result == a^b

        if (result > Integer.MAX_VALUE) {
            return Long.MAX_VALUE;
        }
        return result;
    }

    /**
     * Record containing a base and an exponent.
     *
     * @inv {@code 0 <= base && 0 <= exponent}
     */
    public static class Power { // BEGIN RECORD TYPE

        /** The base. */
        public int base;

        /** The exponent. */
        public int exponent;

        /**
         * Constructs a Power with given base and exponent.
         *
         * @param base  the base
         * @param exponent  the exponent
         * @pre {@code 0 <= base && 0 <= exponent}
         * @post {@code \result.base == base && \result.exponent == exponent}
         */
        public Power(int base, int exponent) {
            this.base = base;
            this.exponent = exponent;
        }

    } // END RECORD TYPE

    /**
     * Returns exponentiation.
     *
     * @param p  the base and exponent
     * @pre {@code p != null}
     * @return {@code power(p.base, p.exponent)}
     * @throws IllegalArgumentException  if precondition violated
     */
    public static long power(Power p) throws IllegalArgumentException {
        return power(p.base, p.exponent);
    }

    /**
     * Writes a number as a power with maximal exponent.
     *
     * @param n  the number to 'powerize'
     * @return  power decomposition of {@code n} with maximal exponent
     * @throws IllegalArgumentException  if precondition violated
     * @pre {@code 2 <= n}
     * @post {@code n == power(\result) &&
     *     (\forall int b, int e;
     *      2 <= b && 1 <= e && n == b ^ e;
     *      e <= \result.exponent)}
     */
    public static Power powerize(int n) throws IllegalArgumentException {
//# BEGIN TODO Implementation of powerize
    
        if (n < 2) {
            throw new IllegalArgumentException("Given integer n < 2");
        }
    
        ArrayList<Power> primeFactors = new ArrayList<Power>();
        primeFactors = factorize(n);
    
        int GCD = findGCD(primeFactors);
        int base = 1;
    
        for (Power factor : primeFactors) {
            factor.exponent = factor.exponent / GCD;
            base *= power(factor.base, factor.exponent);
        }
    
        return new Power(base, GCD);
//# END TODO
    }

//# BEGIN TODO Contracts and implementations of auxiliary functions.
    
    /**
     * decomposes a number into its prime factors
     * 
     * @param n
     * @return prime factors of {@code n} 
     * 
     * @pre {@code n >= 2}
     * @post {@code n == (\product i; \result.has(i); i.base^i.exponent) &&
     *         (\forall i; \result.has(i); (\forall n; n != i.base;
     *          i.base % n != 0))}
     */
    public static ArrayList<Power> factorize(int n) {
        
        ArrayList<Integer> factors = new ArrayList<Integer>();
        ArrayList<Power> uniquePrimeFactors = new ArrayList<Power>();
        
        while (n % 2 == 0) {
            n = n/2;
            factors.add(2);
        }
        
        for (int i = 3; power(i, 2) <= n; i += 2) {
            while (n % i == 0) {
                n = n/i;
                factors.add(i);
            }
        }
        
        if (n > 2) {
            factors.add(n);
        } 
        
        uniquePrimeFactors = convertToPower(factors);
        
        return uniquePrimeFactors;

    }
    
    /**
     * 
     * @param factors array list of factors
     * @return array list of power objects for each factor
     * 
     * @pre {@code factors != null}
     * @post {@code (\forall i; factors.has(i); \result.exponent == )}
     */
    public static ArrayList<Power> convertToPower (ArrayList<Integer> factors){
        
        ArrayList<Integer> uniqueFactors = new ArrayList<Integer>();
        ArrayList<Integer> exponent = new ArrayList<Integer>();
        ArrayList<Power> primeFactors = new ArrayList<Power>();
        
        for (int factor : factors) {
            if (!(uniqueFactors.contains(factor))) {
                uniqueFactors.add(factor);
            }
        }
        
        for (int uniqueFactor : uniqueFactors) {
            int count = 0;
            
            for (int factor : factors) {
                if (uniqueFactor == factor) {
                    count += 1;
                }
            }
            
            exponent.add(count);
        }
        
        for (int i = 0; i < exponent.size(); i++) {
            primeFactors.add(new Power(uniqueFactors.get(i), exponent.get(i)));
        }
        
        return primeFactors;
    }
    
    /**
     * 
     * @param a
     * @param b
     * @return gcd of {@code a} & {@code b}
     * 
     * @post {\result == (\max c; a % c == 0 & b % c == 0; c )}
     */
    public static int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        
        return gcd(b % a, a);
    }
 
    /**
     * finds the gcd of the exponent of the given array of power objects
     * 
     * @param primeFactors
     * @return the gcd of the exponent of the given array of power objects
     * 
     * @pre {@code primeFactors != null}
     * @post {@code \result == (\max c; (\forall i; primeFactors.has(i);
     *                          i.exponent % c == 0); c)}
     */
    public static int findGCD(ArrayList<Power> primeFactors) {
        int result = 0;
        
        for (Power factor: primeFactors){
            result = gcd(result, factor.exponent);
 
            if (result == 1) {
                return 1;
            }
        }
 
        return result;
    }
//# END TODO

}
//# END SKELETON
