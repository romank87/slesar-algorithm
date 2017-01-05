import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

/**
 * Created by roman on 12/6/16.
 */
public class TwoDots {
    static String[] matrix;
    enum eMove {
        LEFT,
        RIGHT,
        DOWN,
        UP
    }

    static int n;
    static int m;

    static char[][] map ;

    static boolean findCycle(char ch, int x, int y, eMove move) {
        if ((x == m) || (x < 0) || (y == n) || (y < 0) || (ch != matrix[y].charAt(x))) {
            return false;
        }

        if (map[y][x] == ch) {
            return true;
        }

        map[y][x] = ch;
        switch (move) {
            case RIGHT: return findCycle(ch, x+1,y, eMove.RIGHT) || findCycle(ch, x,y+1,eMove.DOWN) || findCycle(ch, x, y - 1,eMove.UP);
            case LEFT: return findCycle(ch, x-1, y, eMove.LEFT) || findCycle(ch, x,y+1,eMove.DOWN) || findCycle(ch, x, y - 1,eMove.UP);
            case UP: return findCycle(ch, x-1, y, eMove.LEFT)|| findCycle(ch, x+1,y, eMove.RIGHT) ||  findCycle(ch, x, y - 1,eMove.UP);
            case DOWN: return findCycle(ch, x-1, y, eMove.LEFT) || findCycle(ch, x+1,y, eMove.RIGHT) || findCycle(ch, x,y+1,eMove.DOWN);
        }
        return false;
    }

    static void solve() {
        for(int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                char ch = matrix[y].charAt(x);
                if (map[y][x] == ch) {
                    continue;
                }
                map[y][x] = ch;

                if (findCycle(ch, x-1, y, eMove.LEFT) || findCycle(ch, x+1,y, eMove.RIGHT) || findCycle(ch, x,y+1,eMove.DOWN) || findCycle(ch, x, y - 1,eMove.UP)) {
                    System.out.println("Yes");
                    return;
                }

            }
        }

        System.out.println("No");
    }

    static public void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String array[] = br.readLine().split(" ");
        n = Integer.valueOf(array[0]);
        m = Integer.valueOf(array[1]);
        map = new char[n][m];

        matrix = new String[n];
        for (int i =0; i < n; i++){
            matrix[i] = br.readLine();
        }

        solve();
    }
}
