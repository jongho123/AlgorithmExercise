package n4158;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Problem num : 4158
 * Memory : 76048kb, time : 944ms, code length : 1821b
 *
 * using c++11 => memory 9340kb, time : 956ms, code length : 730b
 */
public class Main {
    public static void main(String args[]) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        int n, m;
        while (true) {
            try {
                String line = br.readLine();
                String numCD[] = line.split(" ");
                n = Integer.parseInt(numCD[0]);
                m = Integer.parseInt(numCD[1]);

                if (n == 0 && m == 0) {
                    break;
                }

                int sang[] = new int[n];
                int sun[] = new int[m];

                // 상근이 CD 입력
                for (int i = 0; i < n; ++i) {
                    sang[i] = Integer.parseInt(br.readLine());
                }

                // 선영이 CD 입력
                for (int i = 0; i < m; ++i) {
                    sun[i] = Integer.parseInt(br.readLine());
                }

                CDManager cdManager = new CDManager(sang, sun);
                System.out.println(cdManager.countOverlapCD());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class CDManager {
    int sang[];
    int sun[];

    public CDManager(int sang[], int sun[]) {
        this.sang = sang;
        this.sun = sun;
    }

    public int countOverlapCD() {
        int res = 0; // 같은 번호의 CD 개수

        int i = 0, j = 0;

        // 이미 오름차순 정렬이므로 같은 CD 번호를 가진 CD 개수만 찾으면 됨.
        while(i < sang.length && j < sun.length) {
            if (sang[i] == sun[j]) {
                ++res;
                ++i;
                ++j;
            }
            else if (sang[i] < sun[j]) {
                ++i;
            }
            else {
                ++j;
            }
        }

        return res;
    }
}