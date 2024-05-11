import java.util.*;

public class Dijkstra {
    private static final int INF = 999;

    private int[] dist;
    private int[][] cost;

    public Dijkstra(int n) {
        dist = new int[n + 1];
        cost = new int[n + 1][n + 1];
    }

    public void costosMatriz(Scanner in, int nodes) {
    for (int i = 1; i <= nodes; i++) {
        System.out.println("\nIngresa los pesos para el nodo " + i + ":");
        for (int j = 1; j <= nodes; j++) {
            cost[i][j] = in.nextInt();
            if (cost[i][j] == 0 && i != j) {
                cost[i][j] = INF;
            }
        }
    }
}

    public void costoDistancia(int nodes, int source) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        boolean[] visited = new boolean[nodes + 1];

        Arrays.fill(dist, INF);
        dist[source] = 0;

        priorityQueue.offer(new Node(source, 0));

        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();
            int currentNodeIndex = currentNode.index;

            if (visited[currentNodeIndex]) continue;

            visited[currentNodeIndex] = true;

            for (int i = 1; i <= nodes; i++) {
                if (!visited[i] && cost[currentNodeIndex][i] != INF) {
                    int newDistance = dist[currentNodeIndex] + cost[currentNodeIndex][i];
                    if (newDistance < dist[i]) {
                        dist[i] = newDistance;
                        priorityQueue.offer(new Node(i, newDistance));
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("\nIngresa la cantidad de nodos: ");
        int nodes = in.nextInt();
        Dijkstra d = new Dijkstra(nodes);
        d.costosMatriz(in, nodes);

        System.out.println("\nIngresa el Origen: ");
        int source = in.nextInt();

        d.costoDistancia(nodes, source);

        System.out.println("\nEl camino más corto desde " + source + " hacia los demás es:");
        for (int i = 1; i <= nodes; i++) {
            if (i != source) {
                System.out.println("\nOrigen: " + source + "\tDestino: " + i + "\tCoste mínimo: " + d.dist[i]);
            }
        }

        System.out.println("\n\n\n");

        in.close();
    }

    static class Node {
        int index;
        int distance;

        Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }
    }
}
