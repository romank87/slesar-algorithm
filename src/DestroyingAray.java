import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import static java.lang.System.in;

/**
 * Created by roman on 12/11/16.
 */
public class DestroyingAray {
    static int n;
    static int[] uf;

    static int getRoot(int idx) {
        if (idx != uf[idx]) {
            uf[idx] = uf[uf[idx]];
            return getRoot(uf[idx]);
        }
        return idx;
    }

    static void union (int a, int b) {
        int root_a = getRoot(a);
        int root_b = getRoot(b);

        uf[root_a] = root_b;
    }


    static void solve(int[] data, int[] order) {
        LinkedList<Long> result = new LinkedList<>();
        int[] visited = new int[n];
        long[] sum = new long[n];
        long max_sum = 0;

        for (int i=n-1;i>=0; i--) {
            int idx = order[i]-1;
            result.addFirst(max_sum);

            if ((idx == n-1 || visited[idx+1] == 0 ) && (idx == 0 || visited[idx-1] == 0)) {
                sum[idx] = data[idx];
                max_sum = Math.max(sum[idx],max_sum);
            } else if (idx != n-1 && visited[idx + 1] != 0 && idx != 0 && visited[idx - 1] != 0) {
                int right_root = getRoot(idx+1);
                int left_root = getRoot(idx-1);
                sum[left_root] += sum[right_root] + data[idx];
                union(idx, left_root);
                union(right_root, left_root);

                max_sum = Math.max(sum[left_root], max_sum);
            } else if (idx != n-1 && visited[idx + 1] != 0) {
                    int right_root = getRoot(idx + 1);
                    sum[right_root] += data[idx];
                    union(idx, right_root);

                    max_sum = Math.max(sum[right_root], max_sum);
            } else if (idx != 0 && visited[idx - 1] != 0) {
                    int left_root = getRoot(idx - 1);
                    sum[left_root] += data[idx];
                    union(idx, left_root);

                    max_sum = Math.max(sum[left_root], max_sum);
            }

            visited[idx] = 1;
        }
        for (Long i: result) {
            System.out.println(i);
        }
    }

    static public void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        n = Integer.valueOf(br.readLine());
        int[] data = Arrays.stream(br.readLine().split(" ")).mapToInt(i -> Integer.valueOf(i)).toArray();
        int[] order = Arrays.stream(br.readLine().split(" ")).mapToInt(i -> Integer.valueOf(i)).toArray();

        uf = new int[n];
        for (int i =0; i <n; i++)
            uf[i] = i;

        solve(data, order);
    }
}
