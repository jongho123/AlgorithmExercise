package n1463;

import java.util.Scanner;

/**
 * Problem num = 1463
 */

public class Main {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        NumberOne makeOne = new NumberOne();
        System.out.println(makeOne.go(n));
    }
}

class NumberOne {
    // input size 10^6, 인덱스를 1부터 쓰기 위해 공간을 1개 더 늘림
    int d[] = new int[1000001];

    int go(int n) {
        // 이미 1일 때, 연산하지 않음.
        if (n == 1) {
            return 0;
        }

        // memoization
        if (d[n] > 0) {
            return d[n];
        }

        for (int i = 2; i <= n; ++i) {
            d[i] = d[i - 1] + 1;
            if (i % 2 == 0) {
                d[i] = Math.min(d[i], d[i / 2] + 1);
            }
            if (i % 3 == 0) {
                d[i] = Math.min(d[i], d[i / 3] + 1);
            }
        }

        return d[n];
    }
}