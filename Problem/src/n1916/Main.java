package n1916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * problem num 1916
 * Memory : 53660, time : 164ms, code length : 2924B
 */
public class Main {
    public static void main(String args[]) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        int city[][] = null;

        int vertex = 0;
        int edge = 0;

        int departure = 0;
        int destination = 0;

        try {
            vertex = Integer.parseInt(br.readLine());
            edge = Integer.parseInt(br.readLine());

            city = new int[vertex + 1][vertex + 1];
            for (int i = 0; i < city.length; i++) {
                for (int j = 0; j < city[0].length; j++) {
                    city[i][j] = Integer.MAX_VALUE;
                }
            }

            String input[];
            for (int i = 0; i < edge; i++) {
                input = br.readLine().split(" ");

                int u = Integer.parseInt(input[0]);
                int v = Integer.parseInt(input[1]);
                int weight = Integer.parseInt(input[2]);

                city[u][v] = Math.min(city[u][v], weight);
            }

            input = br.readLine().split(" ");

            departure = Integer.parseInt(input[0]);
            destination = Integer.parseInt(input[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        MinimumCostPath mcp = new MinimumCostPath(city, vertex);
        System.out.println(mcp.findMinimumCost(departure, destination));
    }
}

class MinimumCostPath {
    int city[][];
    int cost[];

    public MinimumCostPath(int city[][], int vertex) {
        this.city = city;
        this.cost = new int[vertex + 1];

        for (int i = 0; i < cost.length; i++) {
            cost[i] = Integer.MAX_VALUE;
        }
    }

    public int findMinimumCost(int departure, int destination) {
        PriorityQueue<Graph> queue = new PriorityQueue<>(11);

        cost[departure] = 0;
        queue.offer(new Graph(departure, cost[departure]));
        while (!queue.isEmpty()) {
            Graph g = queue.poll();

            for (int i = 1; i < city[g.u].length; i++) {
                if (city[g.u][i] == Integer.MAX_VALUE)
                    continue;

                int newCost = cost[g.u] + city[g.u][i];
                if (cost[i] > newCost) {
                    cost[i] = newCost;
                    queue.offer(new Graph(i, newCost));
                }
            }
        }

        return cost[destination];
    }
}

class Graph implements Comparable<Graph> {
    int u;
    int w;

    public Graph(int u, int w) {
        this.u = u;
        this.w = w;
    }

    @Override
    public int compareTo(Graph o) {
        return Integer.compare(w, o.w);
    }
}