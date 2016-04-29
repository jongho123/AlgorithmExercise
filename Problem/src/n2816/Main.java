package n2816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Problem num 2816
 * Memory : 17052, time : 64ms, code length : 2196b
 */
public class Main {
    public static void main(String args[]) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        ArrayList<String> ch = new ArrayList<>();
        try {
            int n = Integer.parseInt(br.readLine());
            int k1 = 0;
            int k2 = 0;

            for (int i = 0; i < n; i++) {
                String line = br.readLine();
                ch.add(line);
                if (line.equals("KBS1"))
                    k1 = i;
                else if (line.equals("KBS2"))
                    k2 = i;
            }

            DigitalTV tv = new DigitalTV(ch, k1, k2);
            tv.moveChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class DigitalTV {
    ArrayList<String> channel;
    int k1, k2;

    public DigitalTV(ArrayList<String> channel, int indexK1, int indexK2) {
        this.channel = channel;
        k1 = indexK1;
        k2 = indexK2;
    }

    public void moveChannel() {
        int numChannel = 0;

        // k1이 첫 번째 원소에 있지 않으면 k1을 찾음.
        if (k1 != 0) {
            while (numChannel < k1)
                numChannel = pressButton1(numChannel);
        }

        // k1을 첫번째 원소로 이동시킴.
        while (k1 != 0) {
            numChannel = pressButton4(numChannel);
        }

        // k2가 두 번째 원소에 있지 않으면 k2를 찾음.
        if (k2 != 1) {
            while (numChannel < k2)
                numChannel = pressButton1(numChannel);
        }

        // k2를 두 번째 원소로 이동시킴.
        while (k2 != 1) {
            numChannel = pressButton4(numChannel);
        }
    }

    private int pressButton1(int n) {
        System.out.print("1");
        return n + 1;
    }

    private int pressButton4(int n) {
        swapChannel(n - 1, n);
        System.out.print("4");

        if (n == k1) --k1;
        else if (n == k2) --k2;

        if (k1 == k2) ++k2;

        return n - 1;
    }

    private void swapChannel(int n, int m) {
        String ch = channel.get(n);
        channel.set(n, channel.get(m));
        channel.set(m, ch);
    }
}