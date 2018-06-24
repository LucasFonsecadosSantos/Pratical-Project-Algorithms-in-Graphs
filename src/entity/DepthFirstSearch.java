package entity;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstSearch {

    private static Graph graph;
    private static List<String> vertexSet;
    private static List<Boolean> whiteVertexes;
    private static List<Boolean> grayVertexes;
    private static List<Boolean> blackVertexes;
    private static List<TimeTuple> finishTime;
    private static List<TimeTuple> discoveryTime;
    private static List<List<AdjacencyTuple>> adjacencyList;
    private static Stack<Integer> parents;
    private static List<Integer> visited;
    private static int time;
    private static int pre;

    public DepthFirstSearch() {
        
    }

    public static void ExecDepthFirstSearch(Graph graph) {
        finishTime = new ArrayList<TimeTuple>();
        discoveryTime= new ArrayList<TimeTuple>();
        whiteVertexes = new ArrayList<Boolean>();
        grayVertexes = new ArrayList<Boolean>();
        blackVertexes = new ArrayList<Boolean>();
        parents = new Stack<Integer>();
        visited = new ArrayList<Integer>();
        adjacencyList = graph.getAdjacencyList();
        vertexSet = graph.getVertexSet();
        List<AdjacencyTuple> adjacencyVertexes;
        int vertexIndex = 0;
        boolean occurrency = false;
        time = 0;

        for (String vertex : vertexSet) {
            whiteVertexes.add(true);
            grayVertexes.add(false);
            blackVertexes.add(false);
            discoveryTime.add(null);
            finishTime.add(null);
        }

        int currentVertex = 0;
        parents.push(currentVertex);
    
        while (!parents.empty()) {
            
            
            currentVertex = parents.peek();
            occurrency = false;
            whiteVertexes.set(currentVertex, false);
            grayVertexes.set(currentVertex, true);
            adjacencyVertexes = adjacencyList.get(currentVertex);
            System.out.print(parents.size() + " " +vertexSet.get(vertexIndex) + " - ");
            
            if (adjacencyVertexes != null && adjacencyVertexes.size() > 0) {
                for (AdjacencyTuple tuple : adjacencyVertexes) {
                    vertexIndex = vertexSet.indexOf(tuple.getVertex());
                    
                    if (whiteVertexes.get(vertexIndex)) {
                        occurrency = true;
                        grayVertexes.set(vertexIndex, true);
                        whiteVertexes.set(vertexIndex, false);
                        time++;
                        discoveryTime.add(new TimeTuple(vertexSet.get(vertexIndex), time));
                        parents.push(vertexIndex);
                    }
                }
            }
            if (!occurrency) {
                blackVertexes.set(vertexIndex, true);
                grayVertexes.set(vertexIndex, false);
                whiteVertexes.set(vertexIndex, false);
                parents.pop();
            }
            System.out.println(vertexSet.get(vertexIndex) + " size: " + parents.size());
        }
    }
        /*
        vertexSet = graph.getVertexSet();
        adjacencyList = graph.getAdjacencyList();
        whiteVertexes = new ArrayList<Boolean>();
        grayVertexes = new ArrayList<Boolean>();
        blackVertexes = new ArrayList<Boolean>();
        finishTime = new ArrayList<TimeTuple>();
        discoveryTime= new ArrayList<TimeTuple>();
        visiteds = new Stack<Integer>();
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
            visiteds.push(0);
            if (whiteVertexes.get(vertex)) {
                dfsVisit(vertex);
            }
        }
        return;
    }

    /*private static void dfsVisit(int vertexIndex) {
        whiteVertexes.set(vertexIndex, false);
        grayVertexes.set(vertexIndex, true);
        List<AdjacencyTuple> adjacencyVertexes;
        visiteds.push(vertexIndex);
        int currentVertex;

    }
    
    private static void dfsVisit(int vertexIndex) {
        whiteVertexes.set(vertexIndex, false);
        List<AdjacencyTuple> adjacencyVertexes = adjacencyList.get(vertexIndex);
        System.out.print(vertexSet.get(vertexIndex)+  " - ");
        grayVertexes.set(vertexIndex, true);

        if (adjacencyVertexes.size() > 0) {
            for (AdjacencyTuple tuple : adjacencyVertexes) {
                for (int i=0;i<vertexSet.size(); i++) {
                    if (tuple.getVertex().equals(vertexSet.get(i))) {
                        vertexIndex = i;
                    }
                }
                if (whiteVertexes.get(vertexIndex)) {
                    ++time;
                    visiteds.push(vertexIndex);
                    System.out.println(tuple.getVertex());
                    discoveryTime.set(vertexIndex, new TimeTuple(vertexSet.get(vertexIndex), time));
                    dfsVisit(vertexIndex);
                }
            }
        }
        blackVertexes.set(vertexIndex, true);
        System.out.println("BOSTA: "+vertexSet.get(vertexIndex));
    }*/
}