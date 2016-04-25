package n11726;

import java.util.Scanner;

/**
 * Problem num = 11726
 * memory : 18520kb, time : 76ms, code length : 677b
 */
public class Main {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        Tile tile = new Tile();
        System.out.println(tile.fillTail(n));
    }
}

class Tile {
    int d[] = new int[1001];

    public Tile() {
        d[1] = 1;
        d[2] = 2;
    }

    int fillTail(int n) {
        if (n <= 2)
            return n;

        // memoization
        if (d[n] > 0)
            return d[n];

        for (int i = 3; i <= n; ++i) {
            d[i] = (d[i - 1] + d[i - 2]) % 10007;
        }

        return d[n];
    }
}