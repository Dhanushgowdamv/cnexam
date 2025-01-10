import java.util.Scanner;

public class Ford {
    private int[] D; // Distance array
    private int numVertices;
    public static final int MAX_VALUE = 999; // Representing infinity

    // Constructor
    public Ford(int numVertices) {
        this.numVertices = numVertices;
        D = new int[numVertices + 1];
    }

    // Bellman-Ford Algorithm
    public void BellmanFordEvaluation(int source, int[][] adjacencyMatrix) {
        // Initialize distances to infinity
        for (int node = 1; node <= numVertices; node++) {
            D[node] = MAX_VALUE;
        }
        D[source] = 0; // Distance to source is 0

        // Relax edges |V| - 1 times
        for (int iteration = 1; iteration <= numVertices - 1; iteration++) {
            for (int u = 1; u <= numVertices; u++) {
                for (int v = 1; v <= numVertices; v++) {
                    if (adjacencyMatrix[u][v] != MAX_VALUE) {
                        if (D[v] > D[u] + adjacencyMatrix[u][v]) {
                            D[v] = D[u] + adjacencyMatrix[u][v];
                        }
                    }
                }
            }
        }

        // Check for negative weight cycles
        for (int u = 1; u <= numVertices; u++) {
            for (int v = 1; v <= numVertices; v++) {
                if (adjacencyMatrix[u][v] != MAX_VALUE) {
                    if (D[v] > D[u] + adjacencyMatrix[u][v]) {
                        System.out.println("The Graph contains a negative weight cycle.");
                        return;
                    }
                }
            }
        }

        // Print the distances
        System.out.println("Shortest distances from source vertex " + source + ":");
        for (int vertex = 1; vertex <= numVertices; vertex++) {
            System.out.println("Distance to vertex " + vertex + " is " + (D[vertex] == MAX_VALUE ? "Infinity" : D[vertex]));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input number of vertices
        System.out.println("Enter the number of vertices:");
        int numVertices = scanner.nextInt();

        // Input adjacency matrix
        int[][] adjacencyMatrix = new int[numVertices + 1][numVertices + 1];
        System.out.println("Enter the adjacency matrix (use 0 for no edge):");
        for (int u = 1; u <= numVertices; u++) {
            for (int v = 1; v <= numVertices; v++) {
                adjacencyMatrix[u][v] = scanner.nextInt();
                if (u == v) {
                    adjacencyMatrix[u][v] = 0; // Distance to itself is 0
                }
                if (adjacencyMatrix[u][v] == 0 && u != v) {
                    adjacencyMatrix[u][v] = MAX_VALUE; // Represent no edge with MAX_VALUE
                }
            }
        }

        // Input source vertex
        System.out.println("Enter the source vertex:");
        int source = scanner.nextInt();

        // Execute Bellman-Ford
        Ford bellmanFord = new Ford(numVertices);
        bellmanFord.BellmanFordEvaluation(source, adjacencyMatrix);

        scanner.close();
    }
}
