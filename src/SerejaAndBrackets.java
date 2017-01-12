import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.Arrays;

import static java.lang.System.in;

/**
 * Created by roman on 1/5/17.
 */

public class SerejaAndBrackets {
    static class Data {
        Data(int pairs, int leftBracket, int rightBracket) {
            this.leftBracket = leftBracket;
            this.rightBracket = rightBracket;
            this.pairs = pairs;
        }
        int pairs;
        int leftBracket;
        int rightBracket;
    }

    static Data[] tree;
    static String brackets;

    static void buildTree (int idx, Integer begin, Integer end) {
        if (begin == end) return;
        if (end - begin == 1 ) {
            int left = 0, right = 0;
            if (brackets.charAt(begin) == ')') {right += 1; } else {left += 1;}
            tree[idx] = new Data(0, left, right);
            return;
        }

        Integer mid = (begin + end)/2;
        buildTree(2*idx + 1, begin, mid);
        buildTree(2*idx + 2, mid, end);

        Data leftNode = tree[2*idx + 1];
        Data rightNode = tree[2*idx + 2];

        int newPairs = Math.min(leftNode.leftBracket, rightNode.rightBracket);
        int leftBracketOnLeftNode = leftNode.leftBracket - newPairs;
        int rightBracketOnRightNode = rightNode.rightBracket - newPairs;

        tree[idx] = new Data(leftNode.pairs + rightNode.pairs + newPairs,
                            leftBracketOnLeftNode + rightNode.leftBracket,
                            leftNode.rightBracket + rightBracketOnRightNode);
    }

    static Data query0(int id, int begin, int end, int idx_begin, int idx_end) {
        if (begin <= idx_begin && end >= idx_end) {
            return tree[id];
        }

        if (end <= idx_begin || begin >= idx_end || idx_end == idx_begin) {
            return new Data(0,0,0);
        }

        Data leftNode = query0(id*2+1,begin, end, idx_begin, (idx_begin + idx_end)/2);
        Data rightNode = query0(id*2+2,begin, end, (idx_begin + idx_end)/2, idx_end);

        int newPairs = Math.min(leftNode.leftBracket, rightNode.rightBracket);
        int leftBracketOnLeftNode = leftNode.leftBracket - newPairs;
        int rightBracketOnRightNode = rightNode.rightBracket - newPairs;

        return new Data(leftNode.pairs + rightNode.pairs + newPairs,
                leftBracketOnLeftNode + rightNode.leftBracket,
                leftNode.rightBracket + rightBracketOnRightNode);
       }

    static int query (int begin, int end) {
        return 2*query0(0, begin, end, 0, brackets.length()).pairs;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        brackets = br.readLine();
        tree = new Data[brackets.length()*4];
        buildTree(0,0,brackets.length());

        int n = Integer.valueOf(br.readLine());
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < n; i++) {
            String[] pair = br.readLine().split(" ");
            int res = query(Integer.valueOf(pair[0]) - 1, Integer.valueOf(pair[1]));
            out.write("" + res + "\n");
        }
        out.flush();
    }
}
