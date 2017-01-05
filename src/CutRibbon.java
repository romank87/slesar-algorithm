import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.*;

/**
 * Created by roman on 11/23/16.
 */
public class CutRibbon {

        int a,b,c;

        int[] cache  = new int[4001];

        CutRibbon(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        int split_helper(int size) {
            if (size == 0) {
                return 0;
            } else  if (size < 0) {
                return Integer.MIN_VALUE;
            }

            if (cache[size] != 0) {
                return cache[size] + 1;
            }

            int max = Math.max(Math.max(split_helper(size - a), split_helper(size - b)), split_helper(size - c));
            cache[size] = max;
            return max + 1;
        }

        void solve (int ribbonSize) {
            System.out.println(split_helper(ribbonSize));
        }

        static public void main(String[] args) throws IOException {
            String line = new BufferedReader(new InputStreamReader(in)).readLine();
            String array[] = line.split(" ");

            Integer sum = Integer.parseInt(array[0]);
            Integer a = Integer.parseInt(array[1]);
            Integer b = Integer.parseInt(array[2]);
            Integer c = Integer.parseInt(array[3]);

            new CutRibbon(a,b,c).solve(sum);
        }
}
