import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.in;

/**
 * Created by roman on 11/29/16.
 */
public class PaintingFance {

    static int solve2 (int start, int end, int[] array) {
        if (end - start == 1) {
            return 1;
        }

        if (array[start] >= (end - start)) {
            return solve2(start+1, end, array) + 1;
        }

        for (int i = start; i < end; i++) {
            array[i] -= 1;
        }

        boolean isPrevZero = true;
        int newStart = 0;
        int newEnd = 0;

        int solution = 0;
        for (int i = start; i < end; i++) {
            if (array[i] != 0 && isPrevZero) {
                newStart = i;
                isPrevZero = false;
                if (i == end - 1) {
                    solution += Math.min(solve2(newStart, end, array), end-newStart);
                }
            } else if (array[i] == 0 && !isPrevZero)   {
                newEnd = i;
                isPrevZero = true;
                solution += Math.min(solve2(newStart, newEnd,array), newEnd-newStart);
            } else if  (i == end - 1 && !isPrevZero) {
                newEnd = end;
                solution += Math.min(solve2(newStart, newEnd,array), newEnd - newStart);
            }
        }
        return solution+1;
    }



    static public void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int n = Integer.valueOf(br.readLine());
        int[] data = Arrays.stream(br.readLine().split(" ")).mapToInt(i -> Integer.valueOf(i)).toArray();

        System.out.println(Math.min(PaintingFance.solve2(0, data.length, data),n));

    }
}
