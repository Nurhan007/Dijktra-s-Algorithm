// Given the graph below, implement Dijktra's Shortest Path algoritm to to calculate the distance between 2 nodes.
// Your java program must be able to take 2 arguments from the command line arguments, and return the distance.
// Make a zip of your project and provide the compiled class files as well as source files.

// I'll be running the program as follows:
// java DijkstraShortestPath.class A D

// Create the output in the following format:

// Shortest path between A and D:
// Shortest path: A-B-D
// Distance: 5

// If there is no path, the program should output the following:

// There is no path between A and D.

// Make a run of your project for the following nodes and submit the output in a separate file, "output.txt"
// A P
// B Z
// C S
// D X
// E G
// F Y
// G K
// H M
// I T
// L R

// ----

// Graph:
//        A---3---B
//       /       / \
//      4       2   5
//     /       /     \
//    C---5---D---1---E
//   / \     / \     / \
//  6   2   3   4   2   3
//   |   |   |   |   |   |
//   F-4-G-2-H   I   J-2-K
//    \ /     \ /     \ /
//     5       3       4
//     |       |       |
//     L---2---M---6---N
//    / \     / \     / \
//   4   3   2   5   2   1
//   |   |   |   |   |   |
//   O   P   Q-1-R   S-3-T
//    \ /     \ /     \ /
//     3       4       2
//     |       |       |
//     U---3---V---5---W
//      \     / \     /
//       2   4   1   3
//        \ /     \ /
//         X---2---Y
//          \     /
//           4	  3
//            \ /
// 		    Z

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import java.util.Map.Entry;

public class DijkstraShortestPath {
    private Map<String, Map<String, Integer>> graph;

    public DijkstraShortestPath(Map<String, Map<String, Integer>> graph) {
        this.graph = graph;
    }

    public List<String> shortestPath(String startNode, String endNode) {
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        Set<String> visited = new HashSet<>();

        // Initialize distances
        for (String node : graph.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(startNode, 0);

        while (!visited.contains(endNode)) {
            String currentNode = minDistanceNode(distances, visited);
            visited.add(currentNode);

            if (currentNode.equals(endNode)) {
                break;
            }

            Map<String, Integer> neighbors = graph.get(currentNode);
            for (String neighbor : neighbors.keySet()) {
                int distance = distances.get(currentNode) + neighbors.get(neighbor);
                if (distance < distances.get(neighbor)) {
                    distances.put(neighbor, distance);
                    previous.put(neighbor, currentNode);
                }
            }
        }

        return buildPath(startNode, endNode, previous);
    }

    private String minDistanceNode(Map<String, Integer> distances, Set<String> visited) {
        int minDistance = Integer.MAX_VALUE;
        String minNode = null;

        for (Entry<String, Integer> entry : distances.entrySet()) {
            String node = entry.getKey();
            int distance = entry.getValue();
            if (distance < minDistance && !visited.contains(node)) {
                minDistance = distance;
                minNode = node;
            }
        }

        return minNode;
    }

    private List<String> buildPath(String startNode, String endNode, Map<String, String> previous) {
        LinkedList<String> path = new LinkedList<>();
        String currentNode = endNode;

        while (currentNode != null) {
            path.addFirst(currentNode);
            currentNode = previous.get(currentNode);
        }

        if (path.getFirst().equals(startNode)) {
            return path;
        } else {
            return new LinkedList<>();
        }
    }

    public static void main(String[] args) {
        Map<String, Map<String, Integer>> graph = new HashMap<>();

        // Create the graph
        graph.put("A", Map.of("B", 3, "C", 4));
        graph.put("B", Map.of("A", 3, "D", 2, "E", 5));
        graph.put("C", Map.of("A", 4, "D", 5, "F", 6));
        graph.put("D", Map.of("B", 2, "C", 5, "E", 1, "G", 3));
        graph.put("E", Map.of("B", 5, "D", 1, "G", 2, "J", 3));
        graph.put("F", Map.of("C", 6, "G", 4));
        graph.put("G", Map.of("D", 3, "E", 2, "F", 4, "H", 4));
        graph.put("H", Map.of("G", 4, "I", 5, "M", 2));
        graph.put("I", Map.of("H", 5, "T", 3));
        graph.put("J", Map.of("E", 3, "K", 2));
        graph.put("K", Map.of("G", 4, "J", 2, "N", 4));
        graph.put("L", Map.of("F", 5, "O", 4));
        graph.put("M", Map.of("H", 2, "N", 6, "P", 3));
        graph.put("N", Map.of("K", 4, "M", 6, "Q", 2));
        graph.put("O", Map.of("L", 4, "U", 3));
        graph.put("P", Map.of("M", 3, "V", 4));
        graph.put("Q", Map.of("N", 2, "R", 1));
        graph.put("R", Map.of("Q", 1, "S", 4));
        graph.put("S", Map.of("R", 4, "T", 3, "W", 3));
        graph.put("T", Map.of("I", 3, "S", 3, "W", 2));
        graph.put("U", Map.of("O", 3, "X", 2));
        graph.put("V", Map.of("P", 4, "X", 1));
        graph.put("W", Map.of("S", 3, "T", 2, "Y", 5));
        graph.put("X", Map.of("U", 2, "V", 1, "Z", 4));
        graph.put("Y", Map.of("W", 5, "Z", 3));
        graph.put("Z", Map.of("X", 4, "Y", 3));

        // Read the command line arguments
        if (args.length < 2) {
            System.out.println("Please provide two node arguments.");
            return;
        }

        String startNode = args[0];
        String endNode = args[1];

        // Create the shortest path calculator
        DijkstraShortestPath shortestPath = new DijkstraShortestPath(graph);

        // Calculate the shortest path
        List<String> path = shortestPath.shortestPath(startNode, endNode);

        // Print the result
        System.out.println("Shortest path between " + startNode + " and " + endNode + ":");

        if (path.isEmpty()) {
            System.out.println("There is no path between " + startNode + " and " + endNode + ".");
        } else {
            System.out.print("Shortest path: ");
            for (String node : path) {
                System.out.print(node + "-");
            }
            System.out.println();
            System.out.println("Distance: " + calculateDistance(path, graph));
        }
    }

    private static int calculateDistance(List<String> path, Map<String, Map<String, Integer>> graph) {
        int distance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            String currentNode = path.get(i);
            String nextNode = path.get(i + 1);
            distance += graph.get(currentNode).get(nextNode);
        }
        return distance;
    }
}
