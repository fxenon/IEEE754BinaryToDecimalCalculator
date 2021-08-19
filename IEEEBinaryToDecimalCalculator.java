//********************************************************************************************************************\\
//--------------------------------------IEEE-754 binary to decimal convertor------------------------------------------\\
//********************************************************************************************************************\\

package com.company;

import java.util.Scanner;

class BinaryToDecimal {
    static double convertExponent(String st) {
        int len = st.length();
        String exponent = "";
//        taking out exponent bits to calculate its value
        if (len == 32) {
            exponent = st.substring(1, 9);
        }
        if (len == 64) {
            exponent = st.substring(1, 12);
        }
        double exp = 0;
        StringBuilder stValue = new StringBuilder();
        for (int i = 0; i < exponent.length(); i++) {
            stValue.insert(0, exponent.charAt(i));
        }
        for (int i = 0; i < stValue.length(); i++) {
            if (stValue.charAt(i) == '1') {
                exp = exp + 1 * (Math.pow(2, i));
            }
        }
//        subtracting respective value of bias for 32-bits(127) & 64-bits(1023)
        if (len == 32) {
            exp = (int) (exp - 127);
        }
        if (len == 64) {
            exp = (int) (exp - 1023);
        }
        System.out.println("exponent : " + exp);
        return exp;
    }

    static double convertFraction(String st) {
        String fraction = "";
        int length = st.length();
        if (length == 32) {
            fraction = st.substring(9);
        }
        if (length == 64) {
            fraction = st.substring(12);
        }
        double frac = 0;
        int pow;
        for (int i = 0; i < fraction.length(); i++) {
            if (fraction.charAt(i) == '1') {
                pow = i + 1;
                frac = frac + 1 * (1 / Math.pow(2, pow));
            }
        }
        frac = 1 + frac;
        System.out.println("fraction : " + frac);
        return frac;
    }
}

public class Basics {
    public static void main(String[] args) {
        Scanner number = new Scanner(System.in);
        System.out.print("enter 32-bit or 64-bits floating point number: ");
        String num = number.next();
        int numOfBits = num.length();
        double finalFraction;

        if (numOfBits == 32 || numOfBits == 64) {
            double fracValue = BinaryToDecimal.convertFraction(num);
            double expValue = BinaryToDecimal.convertExponent(num);
            if (num.charAt(0) == '1') {
                finalFraction = fracValue - (2 * fracValue);
            } else {
                finalFraction = fracValue;
            }
//            Resultant = FractionPart x Base^Exponent
            double result = finalFraction * (Math.pow(2, expValue));
            System.out.println("resultant decimal number: " + result);
        } else {
            System.out.println("number of bits are not equal to 32 or 64! enter a 32-bits or 64-bits number");
        }
    }
}


