package n1753;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 *  problem num. 1753
 *  Memory : 73596, time : 1076ms, code length : 3546b
 */
public class Main {
    public static void main(String args[]) {
        HashMap<Integer, Byte> graph[] = new HashMap[20001];
        int vertex = 0, edge = 0;
        int start = 0;

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        try {
            String input[] = br.readLine().split(" ");

            vertex = Integer.parseInt(input[0]);
            edge = Integer.parseInt(input[1]);

            start = Integer.parseInt(br.readLine());

            for (int i = 0; i < edge; i++) {
                input = br.readLine().split(" ");

                int begin = Integer.parseInt(input[0]);
                int end = Integer.parseInt(input[1]);
                byte w = Byte.parseByte(input[2]);

                if (graph[begin] == null) {
                    HashMap<Integer, Byte> temp = new HashMap<>();
                    temp.put(end, w);
                    graph[begin] = temp;
                } else {
                    HashMap<Integer, Byte> temp = graph[begin];

                    if (temp.containsKey(end)) {
                        if (temp.get(end) > w) {
                            temp.put(end, w);
                        }
                    } else {
                        temp.put(end, w);
                    }
                }
            }
        } catch (IOException excep) {
            excep.printStackTrace();
        }

        ShortestPath sp = new ShortestPath(graph, vertex);
        sp.findShortestPath(start);
        sp.printDistance();
    }
}

class ShortestPath {
    HashMap<Integer, Byte> graph[];
    int distance[];

    public ShortestPath(HashMap<Integer, Byte> graph[], int vertex) {
        this.graph = graph;
        distance = new int[vertex + 1];

        // 초기화
        for (int i = 1; i < vertex + 1; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
    }

    public void findShortestPath(int start) {
        distance[start] = 0;

        if (graph[start] != null) {
            // 큐를 생성하고 맨 처음 정점을 넣음.
            PriorityQueue<Graph> queue = new PriorityQueue<>(11, new WeightCompare());
            queue.offer(new Graph(start, distance[start]));

            // 큐가 빌 때까지 최단거리 계산
            while (!queue.isEmpty()) {
                Graph g = queue.poll();

                if (graph[g.u] == null)
                    continue;

                for (int v : graph[g.u].keySet()) {
                    byte weight = graph[g.u].get(v);

                    if (distance[v] > distance[g.u] + weight) {
                        distance[v] = distance[g.u] + weight;
                        queue.offer(new Graph(v, distance[v]));
                    }
                }
            }
        }
    }

    public void printDistance() {
        // 출력
        for (int i = 1; i < distance.length; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(distance[i]);
            }
        }
    }
}

class Graph {
    int u;
    int w;

    public Graph(int u, int w) {
        this.u = u;
        this.w = w;
    }
}

class WeightCompare implements Comparator<Graph> {
    @Override
    public int compare(Graph o1, Graph o2) {
        return Integer.compare(o1.w, o2.w);
    }
}