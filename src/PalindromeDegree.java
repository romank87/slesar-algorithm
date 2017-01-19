import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.in;

/**
 * Created by roman on 1/18/17.
 */


public class PalindromeDegree {
    static int base = 79;
    static int ch2int (char ch) {return (int)ch - 47;}

    static class RollingHash {
        int value = 0;
        int size = 0;
        String svalue = new String();
        int maxbase = 1;
        int baseUsed = maxbase;


        void push_back (char ch) {
            baseUsed = maxbase;
            value = value + maxbase *ch2int(ch);
            size += 1;
            maxbase*=base;
            svalue = svalue + ch;
        }

        void push_front (char ch) {
            baseUsed = maxbase;
            System.out.printf("base used for push: %d\n", baseUsed);
            value = value*base + ch2int(ch);
            size += 1;

            maxbase *= base;

            svalue = ch + svalue ;
        }

        int removeBack (char ch) {
            System.out.printf("base used for pop: %d\n", baseUsed);

            value -= baseUsed * ch2int(ch);
            size -= 1;
            svalue = svalue.substring(0, svalue.length()-1);
            return value;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String s = br.readLine();

        int b = 79;

        int h1 = 0;
        int h2 = 0;
        int ex = 1;


        int[] memory = new int[s.length()+10];
        int res = 0;
        for (int i =0; i < s.length(); i++) {
            char ch = s.charAt(i);
            h1 = h1*b + ch2int(ch);
            h2 = ex*ch2int(ch) + h2;
            ex *= b;
//            System.out.printf("h1: %d, h2: %d\n", h1,h2);

            if (i == 0) {
                memory[0] = 1;
                res += 1;
                continue;
            }


            if (h1 == h2) {
                memory[i] = memory[(i-1)/2] + 1;
                res += memory[i];
//                System.out.printf("m[%d] = %d\n", (i-1)/2,memory[(i-1)/2]);
//                System.out.printf("m[%d] = %d\n", i,memory[i]);

            } else {
//                memory[i] = memory[i-1];
            }
        }
        System.out.println(res);




//
//
//


////
//
//        RollingHash h = new RollingHash();
//        RollingHash h1 = new RollingHash();
//        RollingHash h2 = new RollingHash();
//
//        h.push_back(s.charAt(0));
//        h1.push_back(s.charAt(0));
//        h2.push_back(s.charAt(0));
//
//        Map<Integer,Integer> m = new HashMap<>();
//        System.out.printf("for %s, h1 value: %d (%s), h2 value: %d(%s)\n",
//                        h.svalue, h1.value, h1.svalue, h2.value, h2.svalue);
//
//        int res = 1;
//        for (int i = 1; i < s.length(); i++) {
//            h.push_back(s.charAt(i));
//
//            if (i == 1 || (i+1) % 2 == 1) {
//                char pushFront = s.charAt(i);
//                char removeBack = s.charAt(i/2);
//                h2.push_front(pushFront);
//                h2.removeBack(removeBack);
//            } else {
//                char pushBack = s.charAt(i/2);
//                char pushFront = s.charAt(i);
//                h1.push_back(pushBack);
//                h2.push_front(pushFront);
//            }
//
//            if (h1.value == h2.value) {
//                int level = 0;
//                if (h1.size == 1 ) {
//                    level = 2;
//                } else {
//                    level = m.getOrDefault(h1.value, 0) + 1;
//                }
//                m.put(h.value, level);
//                res += level;
//            }
//
//            System.out.printf("for %s,\t\t h1 value: %d (%s), h2 value: %d(%s), res: %d\n", h.svalue, h1.value, h1.svalue, h2.value, h2.svalue, res);
//        }
//
//        System.out.println(res);
//
    }
}
