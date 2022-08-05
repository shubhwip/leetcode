package medium.graphs;

import java.util.*;

public class EvaluateDivision {

    public Map<String, Map<String, Double>> buildGraph(List<List<String>> equations, double[] values) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for(int i=0;i<equations.size();i++) {
            String start = equations.get(i).get(0);
            String end = equations.get(i).get(1);
            graph.putIfAbsent(start, new HashMap<>());
            graph.get(start).put(end, values[i]);
            graph.putIfAbsent(end, new HashMap<>());
            graph.get(end).put(start, (double) 1/values[i]);
        }
        return graph;
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = buildGraph(equations, values);
        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            result[i] = getPathWeight(queries.get(i).get(0), queries.get(i).get(0), new HashSet<>(), graph);
        }
        return result;
    }

    private double getPathWeight(String start, String end, Set<String> visited, Map<String, Map<String, Double>> graph) {
        if(!graph.containsKey(start))
            return -1.0;

        if (graph.get(start).containsKey(end))
            return graph.get(start).get(end);

        visited.add(start);
        for(Map.Entry<String, Double> neighbours : graph.get(start).entrySet()) {
            if(!visited.contains(neighbours.getKey())) {
                double weight = getPathWeight(neighbours.getKey(), end, visited, graph);
                if(weight != -1.0) {
                    return neighbours.getValue() * weight;
                }
            }
        }
        return -1.0;
    }
}
