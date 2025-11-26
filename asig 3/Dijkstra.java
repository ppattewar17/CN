import java.util.*;

class Dijkstra {
    static class Edge {
        int to, weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class Node implements Comparable<Node> {
        int vertex, dist;
        Node(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }
        public int compareTo(Node other) {
            return Integer.compare(this.dist, other.dist);
        }
    }

    static void dijkstra(List<List<Edge>> graph, int src) {
        int V = graph.size();
        int[] dist = new int[V];
        int[] parent = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        dist[src] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(src, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;

            for (Edge edge : graph.get(u)) {
                int v = edge.to;
                int weight = edge.weight;
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    parent[v] = u;
                    pq.add(new Node(v, dist[v]));
                }
            }
        }

        // Print results
        System.out.println("Vertex\tDistance\tPath");
        for (int i = 0; i < V; i++) {
            System.out.print((char)('A' + src) + " -> " + (char)('A' + i) + "\t" + dist[i] + "\t\t");
            printPath(i, parent);
            System.out.println();
        }
    }

    static void printPath(int vertex, int[] parent) {
        if (vertex == -1) return;
        printPath(parent[vertex], parent);
        System.out.print((char)('A' + vertex) + " ");
    }

    public static void main(String[] args) {
        int V = 7; // number of vertices
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        // Add edges (undirected)
        addEdge(graph, 0, 1, 2);
        addEdge(graph, 0, 2, 5);
        addEdge(graph, 1, 2, 1);
        addEdge(graph, 1, 3, 2);
        addEdge(graph, 2, 3, 3);
        addEdge(graph, 2, 4, 1);
        addEdge(graph, 2, 5, 4);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 3, 6, 3);
        addEdge(graph, 4, 6, 5);
        addEdge(graph, 5, 6, 2);

        dijkstra(graph, 0); // Start from A
    }

    static void addEdge(List<List<Edge>> graph, int u, int v, int w) {
        graph.get(u).add(new Edge(v, w));
        graph.get(v).add(new Edge(u, w)); // undirected
    }
}
