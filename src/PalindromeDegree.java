import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.lang.System.in;

/**
 * Created by roman on 1/18/17.
 */


public class PalindromeDegree {
    static int base = 79;
    static BigInteger prime = BigInteger.probablePrime(31, new Random());

    static class RollingHash {
        BigInteger value = BigInteger.valueOf(0);
        int size = 0;
//        String svalue = new String();

        int ch2int (char ch) {return (int)ch - 47;}

        void push_back (char ch) {
            BigInteger v = BigInteger.valueOf(base).pow(size);
            v = v.multiply(BigInteger.valueOf(ch2int(ch)));
            value = value.add(v);
            value = value.mod(prime);

//
//            value = value + (long)Math.pow(base, size)*ch2int(ch);
            size += 1;
//            svalue = svalue + ch;
        }

        void push_front (char ch) {
            value = value.multiply(BigInteger.valueOf(base));
            value = value.add(BigInteger.valueOf(ch2int(ch)));
            value = value.mod(prime);

            size += 1;
//            svalue = ch + svalue ;
        }

        void removeBack (char ch) {
            BigInteger r = BigInteger.valueOf(base).pow(size-1);
            r = r.multiply(BigInteger.valueOf(ch2int(ch)));
            value = value.subtract(r);
            value = value.mod(prime);

//            value -= Math.pow(base, size-1) * ch2int(ch);
            size -= 1;
//            svalue = svalue.substring(0, svalue.length()-1);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String s = br.readLine();

        RollingHash h = new RollingHash();
        RollingHash h1 = new RollingHash();
        RollingHash h2 = new RollingHash();

        h.push_back(s.charAt(0));
        h1.push_back(s.charAt(0));
        h2.push_back(s.charAt(0));

        Map<BigInteger,Integer> m = new HashMap<>();
        int res = 1;
        for (int i = 1; i < s.length(); i++) {
            h.push_back(s.charAt(i));

            if (i == 1 || (i+1) % 2 == 1) {
                char pushFront = s.charAt(i);
                char removeBack = s.charAt(i/2);
                h2.push_front(pushFront);
                h2.removeBack(removeBack);
            } else {
                char pushBack = s.charAt(i/2);
                char pushFront = s.charAt(i);
                h1.push_back(pushBack);
                h2.push_front(pushFront);
            }

            if (h1.value.equals(h2.value)) {
                int level = 0;
                if (h1.size == 1 ) {
                    level = 2;
                } else {
                    level = m.getOrDefault(h1.value, 0) + 1;
                }
                m.put(h.value, level);
                res += level;
            }

//            System.out.printf("h: %d, prev value: %d for %s, h1 value: %d (%s), h2 value: %d(%s), res: %d\n", h.value, m.getOrDefault(h.value,-1), h.svalue, h1.value, h1.svalue, h2.value, h2.svalue, res);
        }

        System.out.println(res);
    }
}
