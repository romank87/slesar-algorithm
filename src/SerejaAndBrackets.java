import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.in;

/**
 * Created by roman on 1/5/17.
 */
public class SerejaAndBrackets {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String brackets = br.readLine();
        int n = Integer.valueOf(br.readLine());

        Map<Integer, Integer> queries = new HashMap<Integer, Integer>();
        for (int i = n; i > 0; i--) {
            String[] pair = br.readLine().split(" ");
            queries.put(Integer.valueOf(pair[0]), Integer.valueOf(pair[1]));
            System.out.println("%s,%s", pair[0], pair[1]);
        }




    }
}
