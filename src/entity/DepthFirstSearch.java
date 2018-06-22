package entity;

import java.util.List;
import java.util.ArrayList;

public class DepthFirstSearch {

    private static Graph graph;
    private static List<String> vertexSet;
    private static List<Boolean> whiteVertexes;
    private static List<Boolean> grayVertexes;
    private static List<Boolean> blackVertexes;
    private static List<TimeTuple> finishTime;
    private static List<TimeTuple> discoveryTime;
    private static List<List<AdjacencyTuple>> adjacencyList;
    private static int time;

    public DepthFirstSearch() {
        
    }

    public static void ExecDepthFirstSearch(Graph graph) {
        vertexSet = graph.getVertexSet();
        adjacencyList = graph.getAdjacencyList();
        whiteVertexes = new ArrayList<Boolean>();
        grayVertexes = new ArrayList<Boolean>();
        blackVertexes = new ArrayList<Boolean>();
        finishTime = new ArrayList<TimeTuple>();
        discoveryTime= new ArrayList<TimeTuple>();
        time = 0;

        for (String vertex : vertexSet) {
            whiteVertexes.add(true);
            grayVertexes.add(false);
            blackVertexes.add(false);
            discoveryTime.add(null);
            finishTime.add(null);
        }

        int cardinality = vertexSet.size();
        for (int vertex=0; vertex<cardinality; ++vertex) {
            discoveryTime.set(0,new TimeTuple(vertexSet.get(0), time));
            if (whiteVertexes.get(vertex)) {
                dfsVisit(vertex);
            }
        }
    }

    private static void dfsVisit(int vertexIndex) {
        whiteVertexes.set(vertexIndex, false);
        grayVertexes.set(vertexIndex, true);
        List<AdjacencyTuple> adjacencyVertexes = adjacencyList.get(vertexIndex);
        System.out.print(vertexSet.get(vertexIndex)+  " - ");
        for (AdjacencyTuple tuple : adjacencyVertexes) {
            for (int i=0;i<vertexSet.size(); i++) {
                if (tuple.getVertex().equals(vertexSet.get(i))) {
                    vertexIndex = i;
                }
            }
            if (whiteVertexes.get(vertexIndex)) {
                ++time;
                System.out.println(tuple.getVertex());
                discoveryTime.set(vertexIndex, new TimeTuple(vertexSet.get(vertexIndex), time));
                dfsVisit(vertexIndex);
            }
        }

        blackVertexes.set(vertexIndex, true);
        
        System.out.println("BOSTA: "+vertexSet.get(vertexIndex));
    }
}