import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

import static java.lang.System.in;

/**
 * Created by roman on 11/23/16.
 */
public class MagicPowder {

    static void solve2 (long n, long powder, long[] need, long[] have) {
        long min_cookies = 0;
        long max_cookies = (long) (2*Math.pow(10, 9)+1);

        do {
            long cookies = min_cookies + (max_cookies - min_cookies) / 2;
            BigInteger p = BigInteger.valueOf(powder);

            for (int i = 0; i < n; ++i) {
                BigInteger diff = BigInteger.valueOf(have[i]).subtract(BigInteger.valueOf(need[i]).multiply(BigInteger.valueOf(cookies)));
                if (diff.signum() >= 0)
                    continue;

                p = p.add(diff);
            }

            if (p.signum() < 0) {
                max_cookies = cookies;
            } else {
                min_cookies = cookies;
            }
        } while (max_cookies - min_cookies > 1);

        System.out.println(min_cookies);
    }

        static public void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String array[] = br.readLine().split(" ");

            Long n = Long.valueOf(array[0]);
            Long k = Long.valueOf(array[1]);

            long[] array_need = Arrays.stream(br.readLine().split(" ")).mapToLong(i -> Long.valueOf(i)).toArray();
            long[] array_have = Arrays.stream(br.readLine().split(" ")).mapToLong(i -> Long.valueOf(i)).toArray();

            MagicPowder.solve2(n, k, array_need, array_have);
        }
}
