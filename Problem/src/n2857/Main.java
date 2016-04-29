package n2857;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Problem num 2857
 * Memory : 17128, time : 68ms, code length : 892b
 */
public class Main {
    public static void main(String args[]) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        ArrayList<Integer> fbi = new ArrayList<>();
        Collections.sort(fbi);

        try {
            for(int i = 1; i <= 5; i++) {
                if (br.readLine().contains("FBI"))
                    fbi.add(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (fbi.isEmpty()) {
            System.out.println("HE GOT AWAY!");
        }
        else {
            for (int i : fbi) {
                System.out.print(i + " ");
            }
        }
    }
}