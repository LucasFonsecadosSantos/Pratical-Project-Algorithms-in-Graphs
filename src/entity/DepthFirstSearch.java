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

    private static void initializeDataStructures() {
        for (String vertex : vertexSet) {
            whiteVertexes.add(true);
            grayVertexes.add(false);
            blackVertexes.add(false);
            discoveryTime.add(null);
            finishTime.add(null);
        }
    }

    private static void instantiateObjects(Graph graph) {
        finishTime = new ArrayList<TimeTuple>();
        discoveryTime= new ArrayList<TimeTuple>();
        whiteVertexes = new ArrayList<Boolean>();
        grayVertexes = new ArrayList<Boolean>();
        blackVertexes = new ArrayList<Boolean>();
        parents = new Stack<Integer>();
        visited = new ArrayList<Integer>();
        adjacencyList = graph.getAdjacencyList();
        vertexSet = graph.getVertexSet();
    }
    public static void ExecDepthFirstSearch(Graph graph) {
        List<AdjacencyTuple> adjacencyVertexes;
        int vertexIndex = 0;
        int currentVertex = 0;
        boolean occurrency = false;
        time = 0;
        instantiateObjects(graph);
        initializeDataStructures();

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
                    vertexIndex = graph.mappingVertexToIndex(tuple.getVertex());
                    
                    if (whiteVertexes.get(vertexIndex) && !grayVertexes.get(vertexIndex)) {
                        occurrency = true;
                        grayVertexes.set(vertexIndex, true);
                        whiteVertexes.set(vertexIndex, false);
                        time++;
                        discoveryTime.add(new TimeTuple(vertexSet.get(vertexIndex), time));
                        System.out.println(vertexSet.get(vertexIndex));
                        parents.push(vertexIndex);
                    }
                }
            }
            if (!occurrency) {
                blackVertexes.set(vertexIndex, true);
                grayVertexes.set(vertexIndex, false);
                whiteVertexes.set(vertexIndex, false);
                System.out.println("black: "+vertexSet.get(vertexIndex));
                parents.pop();
            }
            System.out.println(vertexSet.get(vertexIndex) + " size: " + parents.size() + "  time: " + time);
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