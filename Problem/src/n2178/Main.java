package n2178;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * problem num. 2178
 * Memory : 18048kb, time : 72ms, code length : 2131b
 */
public class Main {
    public static void main(String args[]) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        try {
            // n, m 입력
            String line = br.readLine();
            int n = Integer.parseInt(line.split(" ")[0]);
            int m = Integer.parseInt(line.split(" ")[1]);

            // board 입력
            int board[][] = new int[n][m];
            for (int i = 0; i < n; i++) {
                line = br.readLine();
                for (int j = 0; j < m; j++) {
                    board[i][j] = line.charAt(j) - '0';
                }
            }

            // 미로 탈출
            Maze maze = new Maze(board);
            System.out.println(maze.escape(n, m));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Maze {
    int board[][];
    boolean visit[][];

    public Maze(int board[][]) {
        this.board = board;
    }

    public int escape(int n, int m) {
        visit = new boolean[n][m];
        int dx[] = {0, 0, -1, 1};
        int dy[] = {1, -1, 0, 0};

        Queue<Point> queue = new LinkedList<>();

        // 큐에 넣고 방문 처리
        queue.add(new Point(0,0));
        visit[0][0] = true;

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            // 상하좌우 4 방향으로 진행
            for (int i = 0; i < dx.length; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                // 이동 가능한 칸인지 확인 board == 0 은 이동 불가.
                if (0 <= nx && nx < n && 0 <= ny && ny < m && board[nx][ny] > 0) {
                    if (visit[nx][ny]) {
                        board[nx][ny] = Math.min(board[nx][ny], board[p.x][p.y] + 1);
                    }
                    else {
                        board[nx][ny] = board[p.x][p.y] + 1;
                        visit[nx][ny] = true;
                        queue.add(new Point(nx, ny));
                    }
                }
            }
        }

        // 도착지까지의 최소 거리 반환
        return board[n-1][m-1];
    }
}