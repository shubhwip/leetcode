package algorithms.graph;

import java.util.*;

public class DFS {

    public static List<Character> dfsIterative(Map<Character, List<Character>> graph) {
        List<Character> traversal = new ArrayList<>();
        Stack<Character> stack = new Stack<>();
        return traversal;
    }

    public static void main(String[] args) {
        Map<Character, List<Character>> graph = new HashMap<>();
        graph.put('a', Arrays.asList('b', 'c'));
        graph.put('b', Arrays.asList('d'));
        graph.put('c', Arrays.asList('e'));
        graph.put('d', Arrays.asList('f'));
        graph.put('e', new ArrayList<>());
        graph.put('f', new ArrayList<>());
        System.out.println(dfsIterative(graph));
    }

}
