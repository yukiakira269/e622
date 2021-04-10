/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnt.utilities;

/**
 *
 * @author DELL
 */
public class MathUtil {

    public static long getFactorial(int n) {
        if (n < 0 || n > 20) {
            throw new IllegalArgumentException("Illegal Arguemnt");
        }
        if (n == 0 || n == 1) {
            return n;
        }
        return n * getFactorial(n - 1);
    }

    public static void main(String[] args) {
        long res = getFactorial(5);
        System.out.println(res);
    }
}
