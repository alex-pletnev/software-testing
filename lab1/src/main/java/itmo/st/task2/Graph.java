package itmo.st.task2;

import lombok.Data;

import java.util.*;

@Data
class Graph {
    private final PriorityQueue<Node> pq;
    private final int[] dist;
    private final List<List<Node>> adj;

    public Graph(int size) {
        this.pq = new PriorityQueue<>(size, Comparator.comparingInt(n -> n.cost));
        this.dist = new int[size];
        Arrays.fill(dist, Integer.MAX_VALUE);
        adj = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int src, int dest, int cost) {
        adj.get(src).add(new Node(dest, cost));
        adj.get(dest).add(new Node(src, cost));
    }

    public void dijkstra(int src) {
        this.dist[src] = 0;
        pq.add(new Node(src, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int u = node.vertex;

            for (Node edge : adj.get(u)) {
                if (dist[u] + edge.cost < dist[edge.vertex]) {
                    dist[edge.vertex] = dist[u] + edge.cost;
                    pq.add(new Node(edge.vertex, dist[edge.vertex]));
                }
            }
        }
    }

    static class Node {
        int vertex;
        int cost;

        Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }

}
