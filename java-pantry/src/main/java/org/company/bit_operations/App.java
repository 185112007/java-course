package org.company.bit_operations;

public class App {

    private static final int UPPER = 1;     // 0001
    private static final int SPACE = 2;     // 0010
    private static final int REVERSE = 4;   // 0100

    public static void main(String[] args) {
        andOrXorNot();
        multiplyAndDivide();

        // mask usage
        String s = "qwerty asdfgh zxcvbnm";
        String t = stringTransfor(s, UPPER);  // 0001
        System.out.println(t);
        t = stringTransfor(s, UPPER + SPACE);  // 0001 + 0010 = 0011
        System.out.println(t);
        t = stringTransfor(s, UPPER + SPACE + REVERSE); // 0001 + 0010 + 0100 = 0111
    }

    private static String stringTransfor(String s, int flags) {
        if ((flags & UPPER) != 0){
            s = s.toUpperCase();
        }
        if ((flags & SPACE) != 0){
            s = s.replaceAll("\\s+", "");
        }
        if ((flags & REVERSE) != 0){
            s = new StringBuilder(s).reverse().toString();
        }
        return s;
    }

    private static void multiplyAndDivide() {
        int i = 1;
        while(i<257){
            System.out.println(i);
            i = i << 1;             // multiply by two
        }
        System.out.println();

        i = 1;
        while(i<257){
            System.out.println(i);
            i = i << 2;             // multiply by four
        }

        i = 256;
        while(i>0){
            System.out.println(i);
            i = i >> 1;             // divide by two
        }
    }

    private static void andOrXorNot() {
        byte a = 1;                     // 0001
        byte b = 3;                     // 0011

        // AND
        byte result = (byte)(a & b);    // 0001
        System.out.println(result);

        // OR
        result = (byte) (a | b);        // 0011
        System.out.println(result);

        // XOR
        result = (byte)(a ^ b);         // 0010
        System.out.println(result);

        // NOT
        /*
        a = [0][0][0][1] = 1
             +  0  0  1  = 1
        ~a= [1][1][1][0]
             -  1  1  0 = - (0001 + 1) = - 0010 = -2
         */
        System.out.println(~a);
    }
}
